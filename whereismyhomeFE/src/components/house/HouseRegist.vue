<template>
  <b-container>
    <h1 class="sale_h1">매물 등록</h1>
    <div class="sale_div">
      <p class="sale_apt_name">아파트 명<b-input type="text" v-model="searchtext" @keyup="onSearchText"></b-input></p>
      <div class="apt_class" v-if="searchtext !== '' && searchtext !== searchtextlist">
        <ul class="searchList">
          <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.apartmentName, item.dongCode)">
            {{ item.apartmentName }}
          </li>
        </ul>
      </div>
      <p class="sale_apt_dong">동<b-input v-model="aptDong" /></p>
      <p class="sale_apt_dong">호<b-input v-model="aptHo" /></p>
      <p class="sale_amount_name">가격<b-input v-model="aptAmount" /></p>
      <p class="sale_content_p">내용</p>
      <b-textarea class="sale_content_input" v-model="apttext" style="height: 223px" />
      <br />
      <button class="sale_button" @click="onClickInsert">등록</button>
    </div>
  </b-container>
</template>
<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseRegist",
  data() {
    return {
      searchtextlist: "",
      searchtext: "",
      searchcode: "",
      aptCode: "",
      aptAmount: "",
      apttext: "",
      aptDong: "",
      aptHo: "",
    };
  },
  watch: {
    houses: function () {
      this.aptCode = this.houses[0].aptCode;
    },
    isHouseInsert: function () {
      if (this.isHouseInsert === false) {
        alert("잘못 입력되었습니다.");
      } else {
        alert("매물이 등록되었습니다.");
        this.isHouseInsert = false;
        this.$router.push({ name: "main" });
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["housesSearch", "housesSearchText", "houses", "isHouseInsert"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["aptAutosugestion", "aptSearch", "setAptSale"]),
    onListSearch(context, dongCode) {
      console.log(context, dongCode);
      this.searchtext = context;
      this.searchcode = dongCode;
      this.searchtextlist = context;
      this.onSearchText();
    },

    async onSearchText() {
      await this.aptAutosugestion(this.searchtext);
      await this.aptSearch([this.searchcode, this.searchtext, "apt"]);
    },
    async onClickInsert() {
      console.log(this.aptCode, this.userInfo.id, this.apttext, this.aptAmount);
      await this.setAptSale([this.aptCode, this.userInfo.id, this.apttext, this.aptAmount, this.searchtext, this.aptDong, this.aptHo]);
    },
  },
};
</script>

<style>
.sale_h1 {
  font-family: "Noto Sans KR", sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 30px;
  line-height: 49px;
  margin-top: 44px;
  /* identical to box height */

  color: #161616;
}
.sale_div {
  width: 802px;
  height: 650px;
  margin: auto;
  box-sizing: border-box;
  border: 1px solid #f7f7f7;
}
.sale_apt_name {
  font-family: "Noto Sans KR", sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 29px;

  color: #242424;
  display: flex;
  margin-left: 62px;
  margin-top: 60px;
}
.sale_amount_name,
.sale_apt_dong {
  font-family: "Noto Sans KR", sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 29px;

  color: #242424;
  display: flex;
  margin-left: 62px;
}
.sale_amount_name input {
  margin-left: 92px;
  width: 540px;
  border: 1px solid #eeeeee;
}
.sale_apt_name input {
  margin-left: 54px;
  width: 540px;
  border: 1px solid #eeeeee;
}
.sale_apt_dong input {
  margin-left: 108px;
  width: 540px;
  border: 1px solid #eeeeee;
}

.sale_content_p {
  font-family: "Noto Sans KR", sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 29px;

  color: #242424;
}
.sale_content_input {
  margin: auto;
  box-sizing: border-box;
  width: 665px;
  height: 223px;
  border: 1px solid #eeeeee;
}
.sale_button {
  width: 84px;
  height: 44px;
  border: 0;
  outline: 0;
  background: #006ba3;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.15);
  border-radius: 10px;
  margin-top: 8px;

  font-family: "Noto Sans KR", sans-serif;
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 29px;

  color: #ffffff;
  padding-top: 0px;
  padding-bottom: 2px;
}
.apt_class {
  display: flex;
  margin-left: 148px;
}
.searchList {
  position: absolute;
  width: 536px;
  list-style: none;
  margin-top: -15px;
  padding-left: 0px;
  margin-left: 41px;
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
