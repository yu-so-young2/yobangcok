<template>
  <b-container class="bv-example-row mt-3">
    <!-- 상단 이름 -->
    <b-row>
      <b-col>
        <br />
        <!-- 라우터 카테고리 변수 변화감지 위한 코드 -->
        <p style="display: none">
          {{ this.$route.params.category }}
        </p>
        <h4>{{ this.categoryName }}</h4>
      </b-col>
    </b-row>

    <!-- 페이지 내용 -->
    <board-list-item></board-list-item>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import BoardListItem from "./item/BoardListItem";

const boardStore = "boardStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {};
  },
  created() {
    this.getListArticle(this.category, this.articles);
  },
  updated() {
    this.getListArticle(this.category, this.articles);
  },
  methods: {
    ...mapActions(boardStore, ["getListArticle"]),
  },

  computed: {
    ...mapState(boardStore, ["categoryName"]),
    category() {
      return this.$route.params.category;
    },
  },
  filters: {},
};
</script>

<style scope></style>
