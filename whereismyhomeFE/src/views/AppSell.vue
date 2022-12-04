<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">방 내놓기</h4>
      <div class="sub">
        <router-link :to="{ name: 'regist' }" class="m-3 link">매물 등록</router-link>
        <router-link :to="{ name: 'mysell' }" class="m-3 link">내 매물 관리</router-link>
      </div>
    </div>

    <!-- 페이지 -->
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppSell",
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
.top .sub :hover {
  text-decoration-line: underline;
  color: rgb(255, 255, 255);
}
a {
  color: rgb(255, 255, 255);
  padding: unset;
  align-content: center;
}
.router-link-exact-active {
  color: #ffffff;
  text-decoration: underline;
}
</style>
