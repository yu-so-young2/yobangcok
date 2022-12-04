<template>
  <b-container>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button class="write_button" @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>

    <b-row class="mb-1">
      <b-col>
        <b-table-simple hover responsive fixed>
          <b-thead head-variant="None" class="head">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>

          <tbody v-for="article in articles" :key="article.articleno" v-bind="article" class="list">
            <b-tr>
              <b-td>{{ article.no }}</b-td>
              <b-td class="title">
                <router-link :to="{ name: 'boardview', params: { category: article.category, articleno: article.no } }">{{
                  article.title
                }}</router-link>
              </b-td>
              <b-td>{{ article.view }}</b-td>
              <b-td>{{ article.nickname }}</b-td>
              <b-td>{{ article.createdTime | dateFormat }}</b-td>
            </b-tr>
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

import moment from "moment";
const boardStore = "boardStore";

export default {
  name: "BoardListItem",
  props: {},
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
  },
  computed: {
    ...mapState(boardStore, ["articles"]),
  },
};
</script>

<style>
.title a {
  color: rgb(31, 31, 31);
}

.title a :hover {
  color: rgb(94, 94, 94);
}

.head {
  color: rgb(31, 31, 31);
  font-family: "Helvetica";
  font-size: 15px;
}

.list {
  color: rgb(31, 31, 31);
  font-family: "Helvetica";
  font-size: 15px;
}

.write_button {
  background-color: #41cc93;
  color: white;
  margin-bottom: 10px;
  border: none;
}

.write_button:hover {
  background-color: #5edfa9;
  color: white;
  margin-bottom: 10px;
  border: none;
}
</style>
