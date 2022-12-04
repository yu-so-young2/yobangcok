<template>
  <b-container class="bv-example-row mt-3">
    <div class="section">
      <h4>내 정보 수정</h4>
    </div>
    <div class="modify_div">
      <p class="id">아이디<input type="text" v-model="newUserInfo.id" disabled /></p>
      <p class="password">비밀번호<input type="password" v-model="newUserInfo.password" /></p>
      <p class="name">이름<input type="text" v-model="newUserInfo.name" /></p>
      <p class="nickname">닉네임<input type="text" v-model="newUserInfo.nickname" /></p>
      <p class="email">이메일<input type="text" v-model="newUserInfo.mail" /></p>
      <p class="birth">생년월일<input type="text" v-model="newUserInfo.birth" placeholder="YYYY-MM-DD" /></p>
      <p class="addr">주소<input type="text" v-model="newUserInfo.addr" /></p>
      <b-button type="button" class="m-1 modify_button" @click="onUpdate">수정</b-button>
      <b-button type="button" class="m-1 delete_button" @click="onDelete">회원탈퇴</b-button>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  name: "UserRegister",
  data() {
    return {
      newUserInfo: {
        id: "",
        password: "",
        name: "",
        nickname: "",
        mail: "",
        birth: "",
        addr: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo", "users"]),
  },
  created() {
    this.newUserInfo.id = this.userInfo.id;
    this.newUserInfo.password = "";
    this.newUserInfo.name = this.userInfo.name;
    this.newUserInfo.nickname = this.userInfo.nickname;
    this.newUserInfo.mail = this.userInfo.mail;
    this.newUserInfo.birth = this.userInfo.birth;
    this.newUserInfo.addr = this.userInfo.addr;
  },
  methods: {
    ...mapActions(memberStore, ["userUpdate", "userDelete"]),
    onDelete() {
      this.userDelete(this.userInfo.id);
      this.$router.push({ name: "main" });
    },
    onUpdate() {
      if (
        this.newUserInfo.id === "" ||
        this.newUserInfo.password === "" ||
        this.newUserInfo.name === "" ||
        this.newUserInfo.mail === "" ||
        this.newUserInfo.nickname === "" ||
        this.newUserInfo.addr === "" ||
        this.newUserInfo.birth === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        id: this.newUserInfo.id,
        password: this.newUserInfo.password,
        name: this.newUserInfo.name,
        mail: this.newUserInfo.mail,
        birth: this.newUserInfo.birth,
        nickname: this.newUserInfo.nickname,
        addr: this.newUserInfo.addr,
      };

      this.userUpdate(user);
      this.$router.push({ name: "mypage" });
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
/* modify */

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
.modify_div {
  margin: auto;
  background: #ffffff;
  border: 1px solid #ededed;
  width: 506px;
  height: 480px;
  padding-top: 40px;
  padding-bottom: 40px;
  padding-left: 57px;
  padding-right: 57px;
}

.modify_div .id {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 15px;
}
.modify_div .id input {
  width: 176px;
  height: 32px;
  background: #f8f8f8;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}

.modify_div .password {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .password input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}

.modify_div .name {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .name input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
.modify_div .nickname {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .nickname input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.modify_div .email {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .email input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.modify_div .birth {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .birth input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}
.modify_div .addr {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.modify_div .addr input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
</style>
