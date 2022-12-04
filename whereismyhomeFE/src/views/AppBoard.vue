<template>
  <b-container>
    <!-- 페이지 상단 헤더 -->
    <div class="top">
      <h4 class="title">커뮤니티</h4>
      <div class="sub">
        <router-link :to="{ name: 'boardlist', params: { category: 1 } }" class="m-3 link">자유게시판</router-link>
        <router-link :to="{ name: 'boardlist', params: { category: 2 } }" class="m-3 link">정보게시판</router-link>
        <router-link :to="{ name: 'boardlist', params: { category: 3 } }" class="m-3 link">질문게시판</router-link>
        <!-- <router-link :to="{ name: 'boardlist', params: { category: 4 } }" class="m-3 link">내 구독</router-link> -->

        <!-- <router-link :to="{ name: 'board1' }" class="m-3 link">자유게시판</router-link>
        <router-link :to="{ name: 'board2' }" class="m-3 link">정보게시판</router-link>
        <router-link :to="{ name: 'board3' }" class="m-3 link">질문게시판</router-link>
        <router-link :to="{ name: 'board4' }" class="m-3 link">내 구독</router-link> -->
      </div>
    </div>
    <router-view></router-view>
  </b-container>
</template>
<script>
export default {
  name: "AppBoard",
  components: {},
  data() {
    return {};
  },
  computed: {},
  methods: {},
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
  height: 130px;
}

.top .title {
  padding-top: 30px;
  color: rgb(255, 255, 255);
  text-shadow: 2px 3px 3px #2b8660;
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
