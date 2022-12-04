<template>
  <b-container class="bv-example-row mt-3">
    <div class="section">
      <h4>회원가입</h4>
    </div>
    <div class="regist_form_div">
      <p class="id">
        아이디<b-input type="text" v-model="id" />
        <button @click="onIdDuplicateCheck()" class="idcheck_button">아이디 중복검사</button>
      </p>
      <div class="alert_msg">
        <p show v-if="isIdVaild == false" class="fail">{{ msg }}</p>
        <p show v-else-if="isIdVaild == true" class="access">사용할 수 있는 아이디입니다.</p>
      </div>
      <p class="password">비밀번호<b-input type="password" v-model="password" /></p>
      <p class="name">이름<b-input type="text" v-model="name" /></p>
      <p class="nickname">닉네임<b-input type="text" v-model="nickname" /></p>
      <p class="email">이메일<b-input type="text" v-model="email" /></p>
      <p class="birth">생년월일<b-input type="text" v-model="birth" placeholder="YYYY-MM-DD" /></p>
      <p class="addr">주소<b-input type="text" v-model="addr" @keyup="onSearchText"></b-input></p>
      <div v-if="addr !== '' && addr !== addrlist">
          <ul class="searchList">
            <li v-for="(item, index) in this.housesSearch" v-bind:key="index" @click="onListSearch(item.totalName)">
              {{ item.totalName }}
            </li>
          </ul>
        </div>
      <b-button type="button" class="m-1 regist_button" @click="onRegist">회원가입</b-button>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const houseStore = "houseStore";

export default {
  name: "UserRegister",
  data() {
    return {
      addrlist:"",
      id: "",
      password: "",
      name: "",
      nickname: "",
      email: "",
      birth: "",
      addr: "",
      isIdVaild: null,
      checkedId: null,
      msg: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["users", "isIdDuplicated"]),
    ...mapState(houseStore, ["housesSearch"]),
  },
  methods: {
    ...mapActions(memberStore, ["regist", "idDuplicateCheck"]),
    ...mapActions(houseStore,["dongAutosugestion"]),
    onRegist() {
      if (
        this.id === "" ||
        this.password === "" ||
        this.name === "" ||
        this.email === "" ||
        this.nickname === "" ||
        this.address === "" ||
        this.birth === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      if (!this.isIdVaild || this.id != this.checkedId) {
        alert("아이디 중복검사가 필요합니다");
        return;
      }

      let user = {
        id: this.id,
        password: this.password,
        name: this.name,
        nickname: this.nickname,
        birth: this.birth,
        addr: this.addr,
        mail: this.email,
      };

      this.regist(user);
      if (this.$route.path != "/") this.$router.push({ name: "login" });
    },

    async onIdDuplicateCheck() {
      console.log("아이디검사 " + this.id);
      await this.idDuplicateCheck(this.id);
      if (this.id.length < 5) {
        this.msg = "아이디는 5자 이상이어야 합니다.";
        this.isIdVaild = false;
      } else if (this.isIdDuplicated) {
        this.msg = "이미 존재하는 아이디입니다.";
        this.isIdVaild = false;
      } else {
        this.checkedId = this.id;
        this.isIdVaild = true;
      }
    },
    onListSearch(context) {
      // console.log(context,dongCode);
      this.addr = context;
      this.addrlist = context;
      this.onSearchText();
    },
    async onSearchText() {
      await this.dongAutosugestion(this.addr);
    },
  },
};
</script>

<style scoped>
.section {
  margin: 30px;
  font-family: "Monda";
}

.regist_form_div {
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

.regist_button {
  background-color: #ed8774;
  border: none;
}
.regist_button:hover {
  background-color: #ec9a8c;
  border: none;
}
.idcheck_button {
  padding-top: 4px;
  margin-bottom: 3px;
  margin-left: 10px;
  background-color: #868686;
  border: none;
  height: 30px;
  font-size: 10;

  display: inline-flex;
  flex-direction: column;
  justify-content: flex-start;
}

/* /////////////// */

.regist_form_div .id {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 15px;
}
.regist_form_div .id input {
  width: 176px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}

.regist_form_div .id button {
  width: 122px;
  background: #d9d9d9;
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  line-height: 21px;
  align-items: center;
  color: #242424;
}

.alert_msg {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 18px;
  display: flex;
  margin-left: 83px;
  margin-top: 0px;
}
.alert_msg .fail {
  color: #ff2121;
}
.alert_msg .access {
  color: #114617;
}

.regist_form_div .password {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .password input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}

.regist_form_div .name {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .name input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
.regist_form_div .nickname {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .nickname input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.regist_form_div .email {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .email input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}
.regist_form_div .birth {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .birth input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 20px;
  padding-left: 10px;
  font-size: 15px;
}
.regist_form_div .addr {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 20px;
}
.regist_form_div .addr input {
  width: 309px;
  height: 32px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 47px;
  padding-left: 10px;
  font-size: 15px;
}
.searchList{
  position: absolute;
  width: 306px;
    list-style: none;
    margin-top: -21px;
    padding-left: 0px;
    margin-left: 81px;
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
