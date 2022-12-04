<template>
  <b-container>
    <div class="section">
      <h4>로그인</h4>
    </div>

    <div class="login_form_div">
      <div class="alert_msg">
        <p show v-if="isLoginError" class="ml-4 fail">아이디 또는 비밀번호를 확인하세요.</p>
      </div>
      <div class="login_form_div_2">
        <div style="float: left">
          <p><input type="text" v-model="user.id" placeholder="아이디" /></p>
          <p>
            <input type="password" v-model="user.password" placeholder="비밀번호" @keyup.enter="confirm" />
          </p>
        </div>
        <div>
          <b-button type="button" class="login_button" @click="confirm">로그인</b-button>
        </div>
      </div>
      <br />

      <div class="gotojoin">
        <p>
          <router-link :to="{ name: 'join' }">아직 회원이 아니신가요? -> 회원가입 하러가기</router-link>
        </p>
      </div>
      <div>
        <a id="custom0login-btn" @click="kakaoLoginBtn()">
          <img :src="require('@/assets/kakao_login.png')" width="300px" />
        </a>
      </div>
    </div>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      console.log(this.isLogin);

      await this.userConfirm(this.user);
      // console.log("1. confirm() token >> " + token);
      if (!this.isLoginError && this.isLogin) {
        if (this.$route.params.beforePage != null) {
          this.$router.push({ name: `${this.$route.params.beforePage}` });
        } else {
          this.$router.push({ name: "sell" });
        }
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },

    //카카오 로그인 연동
    kakaoLoginBtn: function () {
      if (!window.Kakao.isInitialized()) {
        window.Kakao.init("49dfc748e30cdbabe337b15a1b8e0644"); // Kakao Developers에서 요약 정보 -> JavaScript 키
      }

      if (window.Kakao.Auth.getAccessToken()) {
        console.log(1);
        window.Kakao.API.request({
          url: "/v1/user/unlink",
          success: function (response) {
            console.log(response);
          },
          fail: function (error) {
            console.log(error);
          },
        });
        window.Kakao.Auth.setAccessToken(undefined);
        console.log(2);
      }

      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: "/v2/user/me",
            data: {
              property_keys: ["kakao_account.email"],
            },
            success: async function (response) {
              console.log(1, response);
            },
            fail: function (error) {
              console.log(error);
            },
          });
        },
        fail: function (error) {
          console.log(error);
        },
      });
    },
  },
};
</script>

<style scoped>
.section {
  margin: 30px;
  font-family: "Monda";
}

.login_form_div {
  margin: auto;
  background: #ffffff;
  border: 1px solid #ededed;
  width: 506px;
  height: 230px;
  padding-top: 40px;
  padding-bottom: 40px;
  padding-left: 57px;
  padding-right: 57px;
}

.login_button {
  background-color: #ed8774;
  border: none;
  height: 83px;
  width: 100px;
}
.login_button:hover {
  background-color: #ec9a8c;
  border: none;
}

/* ////////////// */

.login_form_div p {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 18px;
  line-height: 29px;
  color: #242424;
  display: flex;
  margin-bottom: 5px;
}
.login_form_div input {
  width: 220px;
  height: 40px;
  background: #ffffff;
  border: 1px solid #eeeeee;
  margin-left: 33px;
  padding-left: 10px;
  font-size: 15px;
}

.alert_msg {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;

  line-height: 18px;
  display: flex;
  margin-right: 0px;
  margin-top: 0px;
  margin-left: 14px;
  margin-top: 0px;
}
.alert_msg .fail {
  font-size: 13px;
  color: #ff2121;
}

.login_form_div .gotojoin p {
  font-family: "Monda";
  font-style: normal;
  font-weight: 400;
  font-size: 13px;
  color: #242424;
  margin-left: 80px;
  align-items: right;
  text-align: right;
}
</style>
