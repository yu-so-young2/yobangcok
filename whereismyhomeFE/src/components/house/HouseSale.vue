<template>
  <b-container>
    <div id="map" style="width: 100%; height: 730px"></div>
    <div class="searchDiv1">
      <button type="button" class="apt_btm_image1" id="img_btn" :style="aptSearchStyle" @click="onAptButton"></button>
      <button type="button" class="dong_btm_image1" id="img_btn" :style="dongSearchStyle" @click="onDongButton"></button>
      <b-input type="text" class="searchNav1" v-model="searchtext" @keyup="onSearchText" @keyup.enter="onSearchEnter"></b-input>
      <input type="button" class="search-btn1" @click="onSearchButton" />
      <div v-if="searchtext !== '' && searchtext !== searchtextlist">
        <!-- <div> -->
        <ul class="searchList1" v-if="searchCatecory === 'dong'">
          <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.totalName, item.dongCode)">
            {{ item.totalName }}
          </li>
        </ul>
        <ul class="searchList1" v-if="searchCatecory === 'apt'">
          <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.apartmentName, item.dongCode)">
            {{ item.apartmentName }}
          </li>
        </ul>
      </div>
    </div>
    <div class="side_bar_deal" v-show="isShow">
      <!-- <button @click="isShow=false">닫기</button> -->
      <p class="side_bar_title">{{ dealtitle }}<span class="side_bar_title_span"> 실시간 매물 검색 결과</span></p>
      <p v-if="this.housesSealList.length == 0" class="msg">등록된 매물이 없습니다.</p>
      <ul class="dealList" style="list-style: none">
        <!-- 팝업 나오는 구간 -->
        <div id="pop_info_1" class="pop_wrap" v-show="favorGroupBool">
          <div class="pop_inner">
            <div class="favorit_group_head">그룹 선택</div>
            <div class="favorit_group_body" >
              <div @click="oninsertFavoriteGroupPopup" class="favorit_group_div">
                <p>새 그룹 추가</p> <!-- 기능 추가해야함.-->
              </div>
              <ul  style="list-style: none;">
                <li v-for="(item, index) in this.favoriteGroupList" v-bind:key="index">
                  <div @click="oninsertFavorite(item.groupNo)" class="favorit_group_div"  >
                    <p>그룹 명: {{ item.groupName }}</p>
                    <p>즐겨찾기 개수: {{ item.count }} 개수</p>
                  </div>
                </li>
              </ul>
            </div>
            <b-button type="button" class="btn_close" @click="onPopupdelete('')">닫기</b-button>
          </div>
        </div>
        <!-- 그룹 추가 팝업 나오는 구간 -->
        <div id="pop_info_2" class="pop_wrap" v-show="favorGroupInsertBool">
          <div class="pop_inner">
            <div class="favorit_group_head">그룹 선택</div>
            <div class="favorit_group_body" >
              <div class="favorit_group_div">
              </div>
              <ul style="list-style: none;">
                <li>
                  <div class="favorit_group_div" style="padding: 10px;">
                    <p>그룹 이름: <b-input type="text" v-model="favoriteName" /></p>
                  </div>
                </li>
              </ul>
            </div>
            <b-button type="button" class="btn_close" @click="oninsertFavoriteGroup()" style="margin-right: 17px;">저장</b-button>
            <b-button type="button" class="btn_close" @click="onPopupdelete('')">닫기</b-button>
          </div>
        </div>
        <li v-for="(item, index) in this.housesSealList" v-bind:key="index" style="width: 313px">
          <div class="houseDeal_div">
            <input type="button" class="img-button" @click="onfavorGroup(item.no)">
            <h3>거래가 {{ item.sale_amount }}만원</h3>
            <p>{{ item.apt_dong }}동 {{ item.apt_ho }}호 </p>
            <p>판매자 : {{ item.user_id }}</p>
            <p>판매 내용 : {{ item.sale_content }}</p>
          </div>
        </li>
      </ul>
    </div>
  </b-container>
</template>
<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseSale",
  data() {
    return {
      favoriteName:"",
      favorGroupBool:false,
      favorGroupInsertBool:false,
      searchtextlist:"",
      isShow: false,
      searchtext: "",
      searchcode: "",
      searchCatecory: "apt",
      aptSearchIcon: require("@/assets/aptSearchIconT.png"),
      dongSearchIcon: require("@/assets/dongSearchIconF.png"),
      dealtitle: "",
      houseNo:"",
    };
  },
  created() {
    this.searchCatecory = this.housesSearchCatecory;
    this.searchtext = this.housesSearchText;
    this.searchcode = this.housesSearchCode;
    if (this.searchtext !== "" && this.searchcode !== "") {
      this.onSearchButton();
    }
  },
  watch: {
    houses: function () {
      this.searchtext = this.housesSearchText;
      this.searchCatecory = this.housesSearchCatecory;
      this.searchcode = this.housesSearchCode;
      if (this.searchCatecory === "dong") {
        this.aptSearchIcon = require("@/assets/aptSearchIconF.png");
        this.dongSearchIcon = require("@/assets/dongSearchIconT.png");
      } else {
        this.aptSearchIcon = require("@/assets/aptSearchIconT.png");
        this.dongSearchIcon = require("@/assets/dongSearchIconF.png");
      }
      this.initMap();
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8fb706450403c2c25c88f027d9756fb6";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["housesSearch", "houses", "housesSearchText", "housesSearchCatecory", "housesSearchCode", "housesSealList","favoriteGroupList"]),
    ...mapState(memberStore, ["userInfo"]),
    aptSearchStyle() {
      return {
        background: "url(" + this.aptSearchIcon + ") 0% 0% / 79px",
      };
    },
    dongSearchStyle() {
      return {
        background: "url(" + this.dongSearchIcon + ") 0% 0% / 79px",
      };
    },
  },
  methods: {
    ...mapActions(houseStore, ["dongSearch", "aptSearch", "dongAutosugestion", "aptAutosugestion", "getHouseSaleList","getFavoriteGroupList","setFavorite","setFavoriteGroup"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // var position =  new kakao.maps.LatLng(33.450701, 126.570667);

      // var marker = new kakao.maps.Marker({
      //   position: position,
      //   clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // });
      // marker.setMap(this.map);

      // 마커를 표시할 위치와 title 객체 배열입니다
      var positions = [];

      this.houses.forEach((element) => {
        let temp = {
          title: element.apartmentName,
          content: "<div class='marker_div'>" + element.apartmentName + "</div>",
          latlng: new kakao.maps.LatLng(element.lat, element.lng),
          dongcode: element.aptCode,
        };
        positions.push(temp);
      });
      console.log(positions);

      // 마커 이미지의 이미지 주소입니다
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        var dongcode = positions[i].dongcode;
        var infowindow = new kakao.maps.InfoWindow({
          content: positions[i].content, // 인포윈도우에 표시할 내용
        });
        kakao.maps.event.addListener(marker, "mouseover", this.makeOverListener(this.map, marker, infowindow));
        kakao.maps.event.addListener(marker, "mouseout", this.makeOutListener(infowindow));
        kakao.maps.event.addListener(marker, "click", this.makeClickListener(marker, dongcode));
      }
      this.map.setCenter(positions[0].latlng);
      marker.setMap(this.map);
    },
    makeOverListener(map, marker, infowindow) {
      return () => {
        infowindow.open(map, marker);
      };
    },
    // 마커 클릭 이벤트
    makeClickListener(marker, dongcode) {
      return () => {
        console.log(marker);
        console.log(dongcode);
        this.isShow = true;
        this.dealtitle = marker.Gb;
        this.getHouseSaleList(dongcode);
        // this.onTest();
        // alert("클릭");
      };
    },
    makeOutListener(infowindow) {
      return () => {
        infowindow.close();
      };
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce((bounds, latlng) => bounds.extend(latlng), new kakao.maps.LatLngBounds());

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
    onAptButton() {
      this.searchCatecory = "apt";
      this.aptSearchIcon = require("@/assets/aptSearchIconT.png");
      this.dongSearchIcon = require("@/assets/dongSearchIconF.png");
      this.searchtext = "";
    },
    onDongButton() {
      this.searchCatecory = "dong";
      this.aptSearchIcon = require("@/assets/aptSearchIconF.png");
      this.dongSearchIcon = require("@/assets/dongSearchIconT.png");
      this.searchtext = "";
    },
    // 검색 버튼 클릭 이벤트
    async onSearchButton() {
      console.log("?");
      if (this.searchCatecory === "apt") {
        await this.aptSearch([this.searchcode, this.searchtext, "apt"]);
        console.log(this.housesSearchText);
      } else {
        await this.dongSearch([this.searchcode, this.searchtext, "dong"]);
        console.log(this.searchtext);
      }
    },
    // 검색창 엔터시 가장 상단의 검색어로 검색
    async onSearchEnter() {
      if (this.searchCatecory === "apt") {
        this.searchcode = this.housesSearch[0].dongCode;
        this.searchtext = this.housesSearch[0].apartmentName;
        await this.aptSearch([this.searchcode, this.searchtext, "apt"]);
        console.log(this.housesSearchText);
        this.$router.push({ name: "deal" });
      } else {
        this.searchcode = this.housesSearch[0].dongCode;
        this.searchtext = this.housesSearch[0].totalName;

        await this.dongSearch([this.searchcode, this.searchtext, "dong"]);
        console.log(this.searchtext);
        this.$router.push({ name: "deal" });
      }
    },
    onListSearch(context, dongCode) {
      // console.log(context,dongCode);
      this.searchtext = context;
      this.searchcode = dongCode;
      this.searchtextlist = context;
      this.onSearchText();
    },
    async onSearchText() {
      if (this.searchCatecory === "apt") {
        await this.aptAutosugestion(this.searchtext);
      } else {
        await this.dongAutosugestion(this.searchtext);
      }
    },
    async onfavorGroup(houseNo){
      this.favorGroupBool = !this.favorGroupBool
      this.houseNo = houseNo
      await this.getFavoriteGroupList(this.userInfo.id)
      // 
    },
    async oninsertFavorite(groupNo){
      console.log(groupNo,this.houseNo)
      await this.setFavorite([this.houseNo,groupNo])
      alert("즐겨찾기에 추가 되었습니다.")
      this.onPopupdelete("")
      // 
    },
    async oninsertFavoriteGroupPopup(){
      this.favorGroupBool = !this.favorGroupBool
      this.favorGroupInsertBool =!this.favorGroupInsertBool
      // await this.setFavorite([this.houseNo,groupNo])
      // 
    },
    async onPopupdelete(houseNo){
      this.favorGroupBool = false
      this.favorGroupInsertBool =false
      this.houseNo = houseNo
      // await this.getFavoriteGroupList(this.userInfo.id)
      // 
    },
    async oninsertFavoriteGroup(){
      this.favorGroupBool = false
      this.favorGroupInsertBool =false
      console.log(this.favoriteName,this.userInfo.id) 
      await this.setFavoriteGroup([this.favoriteName,this.userInfo.id])
      alert("그룹이 추가 되었습니다.")
      this.onPopupdelete("")
      // 
    },

  },
};
</script>
<style>
.searchDiv1 {
  position: absolute;
  width: 325px;
  height: 74px;
  left: 22px;
  top: 237px;
  z-index: 1;
}
.searchNav1 {
  padding-left: 21px;
  position: absolute;
  top: 28px;
  mix-blend-mode: normal;
  box-shadow: 0px 4px 7px rgb(0 0 0 / 25%);
  border-radius: 20px;
}
.apt_btm_image1 {
  position: absolute;
  left: 19px;
  /* background: url( "@/assets/aptSearchIconT.png" ); */
  width: 79px;
  height: 28px;
  border: none;
}
.dong_btm_image1 {
  position: absolute;
  left: 98px;
  display: block;
  /* background: url( "@/assets/dongSearchIconF.png" ) no-repeat; */
  width: 79px;
  height: 28px;
  border: none;
}
.search-btn1 {
  position: absolute;
  right: 13px;
  top: 31px;
  border: none;
  background: url("@/assets/돋보기.png") 0% 0% / 28px;
  width: 28px;
  height: 27px;
}
.searchList1 {
  list-style: none;
  margin-top: 65px;
  padding-left: 0px;
  padding-top: 2px;
  margin-left: 10px;
  margin-right: 14px;
  background: #ffffffdb;
  border-bottom-right-radius: 20px;
  border-bottom-left-radius: 20px;
  box-shadow: 0px 4px 7px rgb(0 0 0 / 25%);
}
.searchList1 li {
  display: flex;
  margin-left: 5px;
  padding: 5px;
}
.searchList1 li:hover {
  background-color: rgb(192, 211, 211);
  color: #fff;
}
.side_bar_title {
  display: flex;
  margin-left: 44px;
  margin-top: 30px;
  margin-bottom: 30px;
  font-family: "Helvetica";
  font-style: normal;
  font-weight: 700;
  font-size: 18px;
  line-height: 23px;
  /* identical to box height */

  color: #393939;
}
.side_bar_title_span {
  margin-left: 11px;
  font-family: "Helvetica";
  font-style: normal;
  font-weight: 400;
  font-size: 17px;
  line-height: 23px;
  margin-top: 1px;
  /* identical to box height */

  color: #393939;
}
.side_bar_deal {
  position: absolute;
  right: 0px;
  width: 395px;
  top: 190px;
  z-index: 1;
  background: white;
  height: 730px;
  overflow: auto;
}
.houseDeal_div {
  background: #ffffff;
  padding: 20px 10px;
  /* padding-top: 12px;
  padding-bottom: 8px; */
  box-shadow: 2px 2px 20px 5px rgba(0, 0, 0, 0.1);
  border-radius: 15px;
  margin-bottom: 27px;
}
.houseDeal_div h3 {
  display: flex;
  margin-left: 19px;
  margin-bottom: 5px;
  font-family: "Arimo";
  font-style: normal;
  font-weight: 700;
  font-size: 17px;
  line-height: 29px;

  color: #393939;
}
.houseDeal_div p {
  display: flex;
  margin-left: 19px;
  margin-bottom: 1.66px;
  font-family: "Arimo";
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 17px;

  color: #393939;
}

.side_bar_deal .msg {
  font-size: 17px;
  text-align: left;
  margin-left: 43px;
}
.favor_botton{
  
}
.pop_wrap{position:fixed; top:0; left:0; right:0; bottom:0; background:rgba(0,0,0,.5); font-size:0; text-align:center;z-index: 1;}
.pop_wrap:after{display:inline-block; height:100%; vertical-align:middle; content:'';}
.pop_wrap .pop_inner{display:inline-block; padding:20px 30px; background:#fff; width:400px; vertical-align:middle; font-size:15px;border-radius: 10px;}

.favorit_group_body ul {
    list-style: none;
    padding: 0px;
};
.favorit_group_div{
  cursor: pointer;

}
.favorit_group_div p{
  margin: 0px;
}
.img-button{
position: absolute;
  right: 63px;
  border: none;
  width: 29px;
  height: 32px;
  background: url( "@/assets/favorite.png" ) 0% 0% / 32px;
}
.favorit_group_head{
  font-family: 'Arimo';
font-style: normal;
font-weight: 700;
font-size: 20px;
line-height: 23px;
}
.favorit_group_div{
  border-top-width: 1px;
  margin: 10px;
  cursor: pointer;
  box-shadow: 2px 2px 20px 5px rgb(0 0 0 / 10%);
    border-radius: 15px;
    margin-bottom: 7px;
    text-align: left;
    padding-left: 19px;
}

.favorit_group_div :hover {
  color: #4f4f4f;
  text-decoration-line: none;
}
.marker_div{
  color: #141313;
  border-radius: 15px;
  width: 150px;
  font-family: 'Arimo';
font-style: normal;
font-weight: 400;
font-size: 15px;
  padding: 10px;
}
</style>
