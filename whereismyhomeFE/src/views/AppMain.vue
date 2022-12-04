<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">어떤 아파트를 찾고 계신가요?</h4>
      <div class="searchDiv">
        <button type="button" class="apt_btm_image" id="img_btn" :style="aptSearchStyle" @click="onAptButton"></button>
        <button type="button" class="dong_btm_image" id="img_btn" :style="dongSearchStyle" @click="onDongButton"></button>
        <b-input type="text" class="searchNav" v-model="searchtext" @keyup="onSearchText" @keyup.enter="onSearchEnter"></b-input>
        <input type="button" class="search-btn" @click="onSearchButton" />
        <div v-if="searchtext !== '' && searchtext !== searchtextlist">
          <ul class="searchList" v-if="searchCatecory === 'dong'">
            <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.totalName, item.dongCode)">
              {{ item.totalName }}
            </li>
          </ul>
          <ul class="searchList" v-if="searchCatecory === 'apt'">
            <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.apartmentName, item.dongCode)">
              {{ item.apartmentName }}
            </li>
          </ul>
        </div>
      </div>
    </div>

    
  </b-container>
</template>

<script>
import ChannelService from '../components/ChannelTalk.js';
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";
export default {
  name: "AppMain",
  data() {
    return {
      searchCatecory: "apt",
      searchtext: "",
      searchtextlist: "",
      searchcode: "",
      aptSearchIcon: require("@/assets/aptSearchIconT.png"),
      dongSearchIcon: require("@/assets/dongSearchIconF.png"),
      channelSettings:{
          "pluginKey": ChannelService.key(), //위에 플러그인 키를 불러오는거임
          "hideChannelButtonOnBoot": false, //채널톡에서 기본 제공하는 버튼 안쓰니까 숨김처리 하는 것
          "mobileMessengerMode": "iframe",
          "zIndex": 90,
          "language": "ko",
          "profile": {                
            "memFlag" : "true" //이건 로그인 사용자면 true, 미로그인 사용자면 false로 지정한다.  변수로 지정하면 편하다. 설명하기 위해서 스트링으로 이렇게 쓴 것이다.  나중에 서포트봇 이용할 때 활용할려고 만든 것임, 서포트봇 활용 안할꺼면 해당 라인 삭제, 필수 아님
          }
        },
    };
  },
  created() {
    ChannelService.boot(this.channelSettings,this.getChannelTalkBadge);
    
  },
  computed: {
    ...mapState(houseStore, ["housesSearch", "housesSearchText"]),
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
    ...mapActions(houseStore, ["dongSearch", "aptSearch", "dongAutosugestion", "aptAutosugestion"]),

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

    onListSearch(context, dongCode) {
      // console.log(context,dongCode);
      this.searchtext = context;
      this.searchcode = dongCode;
      this.searchtextlist = context;
      this.onSearchText();
    },
    // 검색 버튼 클릭 이벤트
    async onSearchButton() {
      if (this.searchCatecory === "apt") {
        await this.aptSearch([this.searchcode, this.searchtext, "apt"]);
        console.log(this.housesSearchText);
        this.$router.push({ name: "deal" });
      } else {
        await this.dongSearch([this.searchcode, this.searchtext, "dong"]);
        console.log(this.searchtext);
        this.$router.push({ name: "deal" });
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
    async onSearchText() {
      if (this.searchCatecory === "apt") {
        await this.aptAutosugestion(this.searchtext);
      } else {
        await this.dongAutosugestion(this.searchtext);
      }
    },
    setChannelTalkShow() {
      if(!sessionStorage.getItem("channelTalkOpened") || sessionStorage.getItem("channelTalkOpened") !== 'true'){
        //기존 채팅이 없다면
        window.ChannelIO('openChat', '', ''); //채팅방을 하나 생성하고    
        sessionStorage.setItem('channelTalkOpened','true'); //채팅방 하나 만들었다고 브라우저 세션 끝날 때까지 유지되게 세션 스토리지에 넣음
      }else{
        //기존 채팅 중이였다면 계속 채팅방 개설하지 않게 기존 채팅 보여줌
        window.ChannelIO('show');
      }
    },
  },
  
};
</script>

<style scoped>
.container {
  padding: 0 0;
  margin: 0 0;
  max-width: unset;
}
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}

.top {
  background-color: #006ba3;
  padding: 0 0;
  height: 400px;
}

.top .title {
  padding-top: 100px;
  color: rgb(255, 255, 255);
  text-shadow: 2px 3px 3px #00476e;
  font-family: Helvetica, sans-serif;
  font-weight: 700;
  font-size: 20px;
}

.s {
  color: rgb(255, 255, 255);
}
.searchDiv {
  width: 80%;
  max-width: 532px;
  /* display: inline-block; */
  margin: auto;
  margin-top: 30px;
  border: 0px;
}

.apt_btm_image {
  display: block;
  margin-left: 26px;
  float: left;
  /* background: url( "@/assets/aptSearchIconT.png" ); */
  background-size: 79px;
  width: 79px;
  height: 28px;
  border: none;
}
.dong_btm_image {
  display: block;
  /* background: url( "@/assets/dongSearchIconF.png" ) no-repeat; */

  width: 79px;
  height: 28px;
  border: none;
}
.searchNav {
  padding-left: 21px;
  mix-blend-mode: normal;
  box-shadow: 0px 4px 7px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  vertical-align: middle;
  display: inline-block;
  margin-right: -50px;
  margin-top: -11px;
}
.search-btn {
  border: none;

  background: url("@/assets/돋보기.png") 0% 0% / 28px;
  width: 28px;
  height: 27px;
}
.searchList {
  list-style: none;
  margin-top: -16px;
  padding-left: 0px;
  padding-top: 15px;
  margin-left: 23px;
  background: white;
  border-bottom-right-radius: 20px;
  border-bottom-left-radius: 20px;
  box-shadow: 0px 4px 7px rgb(0 0 0 / 25%);
}
.searchList li {
  display: flex;
  margin-left: 5px;
  padding: 5px;
}
.searchList li:hover {
  background-color: rgb(192, 211, 211);
  color: #fff;
}
</style>
