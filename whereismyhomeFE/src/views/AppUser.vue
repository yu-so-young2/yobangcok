<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">회원</h4>
    </div>

    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style scoped>
.container {
  padding: 0 0;
  margin: 0 0;
  max-width: unset;
}
.top {
  background-color: #ed8774;
  padding: 0 0;
  height: 90px;
}

.top .title {
  padding-top: 30px;
  color: rgb(255, 255, 255);
  text-shadow: 2px 3px 3px #a44e3e;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 700;
  font-size: 20px;
}
</style>
