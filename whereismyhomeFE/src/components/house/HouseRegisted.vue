<template>
  <b-container>
    <h3 v-if="this.supHousesUserSaleList.length !== 0" class="house_registed_title">내 매물 관리</h3>
    <h3 v-else-if="this.supHousesUserSaleList.length === 0" class="house_registed_title">올린 매물이 없습니다.</h3>
    <ul class="house_registed_ul">
      <li v-for="(item, index) in this.supHousesUserSaleList" v-bind:key="index" class="house_registed_list">
        <p class="apt_name">{{ item.apt_name }}</p>
        <p class="apt_dong_ho">{{ item.apt_dong }}동 {{ item.apt_ho }}호</p>
        <p class="apt_amount">{{ item.sale_amount }}만원</p>
      </li>
    </ul>
  </b-container>
</template>
<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseRegisted",
  data() {
    return {
      supHousesUserSaleList: "",
    };
  },
  created() {
    this.onTest();
  },
  watch: {
    housesUserSaleList: function () {
      this.supHousesUserSaleList = this.housesUserSaleList.data;
    },
    userInfo: function () {
      if (this.userInfo == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.moveList();
      }
    },
  },
  computed: {
    ...mapState(houseStore, ["housesUserSaleList"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getAptSaleByUserIdList"]),
    async onTest() {
      // console.log(this.userInfo.id);
      await this.getAptSaleByUserIdList(this.userInfo.id);
    },
    moveList() {
      this.$router.push({ name: "main" });
    },
  },
};
</script>
<style>
.house_registed_title {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 30px;
  line-height: 49px;
  color: #161616;
  margin-top: 44px;
}
.house_registed_ul {
  list-style: none;
  width: 88%;
  margin: auto;
}
.house_registed_list {
  float: left;
  background: #ffffff;
  padding: 20px 10px;
  box-shadow: 2px 2px 20px 5px rgb(0 0 0 / 10%);
  border-radius: 15px;
  margin: 30px;
  width: 313px;
  text-align: initial;
  font-family: "Arimo";
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 17px;
  color: #393939;
}
.apt_name {
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
.apt_amount,
.apt_dong_ho {
  display: flex;
  margin-left: 19px;
  margin-bottom: 5px;
  font-family: "Arimo";
  font-style: normal;
  font-weight: 400;
  font-size: 15px;
  line-height: 17px;

  color: #393939;
}
</style>
