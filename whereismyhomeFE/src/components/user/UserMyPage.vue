<template>
  <b-container class="mt-4" v-if="userInfo">
    <div class="section">
      <h4>내 정보</h4>
    </div>
    <div class="mypage_div">
      <p class="id">아이디<input type="text" v-model="userInfo.id" disabled /></p>
      <p class="name">이름<input type="text" v-model="userInfo.name" disabled /></p>
      <p class="nickname">닉네임<input type="text" v-model="userInfo.nickname" disabled /></p>
      <p class="email">이메일<input type="text" v-model="userInfo.mail" disabled /></p>
      <p class="birth">생년월일<input type="text" v-model="userInfo.birth" placeholder="YYYY-MM-DD" disabled /></p>
      <p class="addr">주소<input type="text" v-model="userInfo.addr" disabled /></p>
      <b-button type="button" class="m-1 modify_button" @click="onModify">수정</b-button>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  data() {
    return {};
  },
  created() {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    onModify() {
      this.$router.push({ name: "mypageModify" });
    },
  },
};
</script>

<style>
.section {
  margin: 30px;
  font-family: "Monda";
}
.modify_button {
  background-color: #ed8774;
  border: none;
}
.modify_button:hover {
  background-color: #ec9a8c;
  border: none;
}
.delete_button {
  background-color: #767676;
  border: none;
}
.delete_button:hover {
  background-color: #919191;
  border: none;
}
.mypage_div {
  margin: auto;
  background: #ffffff;
  border: 1px solid #ededed;
  width: 506px;
  height: 430px;
  padding-top: 40px;
  padding-bottom: 40px;
  padding-left: 57px;
  padding-right: 57px;
}

/* //////////// */

.mypage_div .id {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 15px;
}
.mypage_div .id input {
  width: 176px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}

.mypage_div .password {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .password input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}

.mypage_div .name {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .name input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
.mypage_div .nickname {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .nickname input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.mypage_div .email {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .email input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.mypage_div .birth {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .birth input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}
.mypage_div .addr {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.mypage_div .addr input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 0px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
</style>
