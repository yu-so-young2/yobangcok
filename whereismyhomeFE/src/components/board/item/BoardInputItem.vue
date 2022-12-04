<template>
  <b-container class="bv-example-row mt-3">
    <!-- 게시판 이름 -->
    <b-row>
      <b-col>
        <br />
        <h4>글 작성</h4>
      </b-col>
    </b-row>

    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group id="userid-group" label="카테고리:" label-for="category">
            <b-form-select v-model="article.category">
              <b-form-select-option :value="0">카테고리를 선택해주세요</b-form-select-option>
              <b-form-select-option value="1">자유게시판</b-form-select-option>
              <b-form-select-option value="2">정보게시판</b-form-select-option>
              <b-form-select-option value="3">질문게시판</b-form-select-option>
            </b-form-select>
          </b-form-group>

          <b-form-group id="subject-group" label="제목:" label-for="subject">
            <b-form-input id="subject" v-model="article.title" type="text" required placeholder="제목 입력..."></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea id="content" v-model="article.content" placeholder="내용 입력..." rows="10" max-rows="15"></b-form-textarea>
          </b-form-group>

          <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
          <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        no: 0,
        category: 0,
        title: "",
        content: "",
        userId: "",
      },
      options: [
        { value: null, text: "카테고리를 선택해주세요" },
        { value: 1, text: "자유게시판" },
        { value: 2, text: "질문게시판" },
        { value: 3, text: "정보게시판" },
      ],

      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      let param = this.$route.params.articleno;
      getArticle(
        param,
        ({ data }) => {
          // this.article.articleno = data.article.articleno;
          // this.article.userid = data.article.userid;
          // this.article.subject = data.article.subject;
          // this.article.content = data.article.content;
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      this.article.category == 0 && ((msg = "카테고리 입력해주세요"), (err = false));
      err && !this.article.title && ((msg = "제목 입력해주세요"), (err = false));
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      let param = {
        userId: this.userInfo.id,
        nickname: this.userInfo.nickname,
        category: this.article.category,
        title: this.article.title,
        content: this.article.content,
      };
      console.log(this.userInfo.id);
      console.log(JSON.stringify(param));
      writeArticle(
        JSON.stringify(param),
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        articleno: this.article.articleno,
        userid: this.article.userid,
        subject: this.article.subject,
        content: this.article.content,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "boardlist", params: `${this.article.category}` });
    },
  },
  watch: {
    userInfo: function () {
      if (this.userInfo == null) {
        alert("로그인이 필요한 서비스입니다.");
        this.moveList();
      }
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}

.mb-1 .head {
  font-size: 15px;
}

.mb-1 {
  font-size: 15px;
}
</style>
