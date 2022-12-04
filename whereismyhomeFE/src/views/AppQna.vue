<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">1:1 문의</h4>
    </div>
    <!-- 페이지 -->
    <router-view></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppQna",
};
</script>

<style scoped>
.container {
  padding: 0 0;
  margin: 0 0;
  max-width: unset;
}
.top {
  background-color: #41cc93;
  padding: 0 0;
  height: 90px;
}

.top .title {
  padding-top: 30px;
  color: rgb(255, 255, 255);
  text-shadow: 2px 3px 3px #2b8660;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: 700;
  font-size: 20px;
}

a {
  color: rgb(255, 255, 255);
  padding: unset;
  align-content: center;
}
</style>
