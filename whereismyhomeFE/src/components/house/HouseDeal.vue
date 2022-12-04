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
      <p class="side_bar_title">{{ dealtitle }}<span class="side_bar_title_span"> 거래내역 검색 결과</span></p>
      <p v-if="this.housesDealList.length == 0" class="msg">등록된 거래내역이 없습니다.</p>

      <!-- chart -->
      <house-deal-chart v-if="this.housesDealList.length != 0"></house-deal-chart>

      <ul class="dealList" style="list-style: none">
        <li v-for="(item, index) in this.housesDealList" v-bind:key="index" style="width: 313px">
          <div class="houseDeal_div">
            <h3>거래가 {{ item.dealAmount }}만원</h3>
            <p>면적 : {{ item.area }}m²</p>
            <p>층수 : {{ item.floor }} 층</p>
            <p>거래일자 : {{ item.dealYear }}년 {{ item.dealMonth }}월 {{ item.dealDay }}일</p>
          </div>
        </li>
      </ul>
    </div>
  </b-container>
</template>
<script>
import { mapActions, mapState } from "vuex";
import HouseDealChart from "./HouseDealChart.vue";

const houseStore = "houseStore";

export default {
  name: "HouseDeal",
  data() {
    return {
      searchtextlist: "",
      isShow: false,
      searchtext: "",
      searchcode: "",
      searchCatecory: "apt",
      aptSearchIcon: require("@/assets/aptSearchIconT.png"),
      dongSearchIcon: require("@/assets/dongSearchIconF.png"),
      dealtitle: "",
    };
  },
  components: {
    HouseDealChart,
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
    ...mapState(houseStore, ["housesSearch", "houses", "housesSearchText", "housesSearchCatecory", "housesSearchCode", "housesDealList"]),
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
    ...mapActions(houseStore, ["dongSearch", "aptSearch", "dongAutosugestion", "aptAutosugestion", "getHouseDealList"]),
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
        marker.setMap(this.map);
      }
      this.map.setCenter(positions[0].latlng);
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
        this.getHouseDealList(dongcode);
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
  font-size: 17px;
  line-height: 23px;
  /* identical to box height */

  color: #393939;
}
.side_bar_title_span {
  margin-left: 11px;
  font-family: "Helvetica";
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
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
