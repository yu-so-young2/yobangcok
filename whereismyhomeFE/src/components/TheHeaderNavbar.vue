<template>
  <div>
    <b-navbar toggleable="lg" type="light" variant="">
      <!-- logo -->
      <b-navbar-brand href="#">
        <router-link :to="{ name: 'main' }">
          <b-img :src="require('@/assets/ssafy_logo.png')" id="logo" class="d-inline-block align-top" alt="logo"></b-img>
        </router-link>
      </b-navbar-brand>

      <!-- Right Align -->
      <b-collapse id="nav-collapse" is-nav>
        <!-- nav-item -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="#" class="nav-item">
            <router-link :to="{ name: 'main' }" class="m-3 link">메인</router-link>
            <router-link :to="{ name: 'deal' }" class="m-3 link">방 구하기</router-link>
            <router-link :to="{ name: 'sell' }" class="m-3 link">방 내놓기</router-link>
            <router-link :to="{ name: 'board' }" class="m-3 link">커뮤니티</router-link>
          </b-nav-item>
        </b-navbar-nav>

        <!-- user -->
        <div class="user">
          <!-- after login -->
          <b-navbar-nav v-if="userInfo">
            <b-nav-item class="align-self-center">
              <router-link :to="{ name: 'mypage' }" class="link align-self-center">
                <b-avatar variant="dark" v-text="userInfo.id.charAt(0).toUpperCase()" size="1.7rem"></b-avatar>
                {{ userInfo.nickname }}({{ userInfo.id }})님
              </router-link>
            </b-nav-item>
            <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
          </b-navbar-nav>

          <!-- before login -->
          <b-navbar-nav v-else>
            <b-nav-item href="#" class="before-login">
              <router-link :to="{ name: 'join' }" class="m-2 link">회원가입</router-link>
              <router-link :to="{ name: 'login', params: { beforePage: `${this.$route.name}` } }" class="m-2 link">로그인</router-link>
            </b-nav-item>
          </b-navbar-nav>
        </div>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(memberStore, ["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    // 로그아웃 실행
    onClickLogout() {
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });

      console.log(this.userInfo.id);

      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)

      this.userLogout();

      if (this.$route.path.includes("/user")) this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
.navbar {
  padding: 10px 60px;
}

.navbar #logo {
  width: 75px;
  height: 40px;
}

a {
  color: rgb(45, 45, 45);
  padding: unset;
  align-content: center;
}

.nav-item {
  font-weight: 600;
  font-size: 13px;
}

.before-login {
  font-size: 12px;
  font-weight: 350;
  color: rgb(80, 80, 80);
}

.user {
  font-size: 12px;
  margin-left: 40px;
}

.user .nav-link {
  font-size: 12px;
}
</style>
