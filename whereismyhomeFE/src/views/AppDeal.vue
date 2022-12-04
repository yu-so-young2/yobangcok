<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">방 구하기</h4>
      <div class="sub">
        <router-link :to="{ name: 'housedeal' }" class="m-3 link">실거래내역</router-link>
        <router-link :to="{ name: 'housesale' }" class="m-3 link">실시간 매물</router-link>
        <router-link :to="{ name: 'myfavorite' }" class="m-3 link">내 즐겨찾기</router-link>
      </div>
    </div>

    <!-- 페이지 -->
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppDeal",
};
</script>

<style scoped>
.container {
  padding: 0 0;
  margin: 0 0;
  max-width: unset;
}
.top {
  background-color: #006ba3;
  padding: 0 0;
  height: 130px;
}

.top .title {
  padding-top: 30px;
  color: rgb(255, 255, 255);
  text-shadow: 2px 3px 3px #00456a;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 700;
  font-size: 20px;
}
.top .sub {
  padding-top: 15px;
  color: rgb(255, 255, 255);
  /* text-shadow: 2px 3px 3px #2b8660; */
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 550;
  font-size: 15px;
}
a {
  color: rgb(255, 255, 255);
  padding: unset;
  align-content: center;
}
.top .sub :hover {
  text-decoration-line: underline;
  color: rgb(255, 255, 255);
}

.router-link-exact-active {
  color: #ffffff;
  text-decoration: underline;
}
</style>
