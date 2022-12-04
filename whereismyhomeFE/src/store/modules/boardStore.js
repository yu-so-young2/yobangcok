import { getCommentList, listArticle } from "@/api/board";
//, writeArticle, getArticle, modifyArticle, deleteArticle

const boardStore = {
  namespaced: true,
  state: {
    categoryName: "",
    articles: [],
    article: {
      no: 0,
      category: 0,
      title: "",
      content: "",
      userId: "",
      nickname: "",
      createdTime: "",
      view: 0,
      good: 0,
      bad: 0,
    },
    comment: [],
  },
  getters: {},
  mutations: {
    SET_CATEGORYNAME(state, data) {
      //현재 게시판의 카테고리 번호 기억
      let names = ["자유게시판", "정보게시판", "질문게시판", "내 구독"];
      state.categoryName = names[data - 1];
    },
    SET_ARTICLES(state, data) {
      //현재 게시판의 게시글 불러오기
      state.articles = data;
    },
    SET_COMMENT(state, data) {
      state.comment = data;
    },
  },
  actions: {
    async getListArticle({ commit }, category) {
      console.log(category + "의 게시글 불러오기");
      await listArticle(
        category,
        ({ data }) => {
          commit("SET_CATEGORYNAME", category);
          commit("SET_ARTICLES", data);
          console.log("카테고리 이름, 게시글 불러오기 끝");
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getComments({ commit }, boardno) {
      await getCommentList(
        boardno,
        ({ data }) => {
          commit("SET_COMMENT", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
