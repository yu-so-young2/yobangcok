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

    <b-container>
      <b-row>
        <b-col class="text-left">
          <b-button class="backlist_button" @click="moveList"> &lt; 목록 돌아가기</b-button>
        </b-col>
        <b-col class="text-right" v-if="this.userInfo != null && this.userInfo.id == article.userId">
          <!-- <b-button class="button mr-2" size="sm" @click="moveModifyArticle">글수정</b-button> -->
          <b-button class="button" size="sm" @click="ondeleteArticle">글삭제</b-button>
        </b-col>
      </b-row>

      <!-- 게시글 내용 -->
      <div class="post">
        <b-row>
          <b-col>
            <b-card class="postcard">
              <b-card
                :header-html="`<h6>
              ${article.title}</h6><div><h6 style='color:#454545;font-size:13px;'>${article.nickname}(${article.userId})   | ${article.createdTime} | 조회수 ${article.view}</h6></div>`"
                class="mb-2 text-left"
                border-variant="light"
                no-body
              />
              <b-card-body>
                <div class="text-left post_content" v-html="message"></div>
                <div class="vote text-center">
                  <b-button class="button good" size="sm" @click="onVoteGood">좋아요 {{ article.good }}</b-button>
                  <b-button class="button ml-4 bad" size="sm" @click="onVoteBad">싫어요 {{ article.bad }}</b-button>
                </div>
              </b-card-body>
            </b-card>
          </b-col>
        </b-row>
      </div>
    </b-container>

    <!-- 댓글 내용 -->
    <div class="comment">
      <b-container>
        <b-row>
          <b-col class="text-left">
            <span class="new_comment_title" style="font-size: 15px">새 댓글</span>
          </b-col>
          <b-col class="text-right">
            <b-button class="new_comment_button" size="sm" @click="onCommentRegist">등록</b-button>
          </b-col>
        </b-row>
        <b-row>
          <textarea v-show="this.userInfo" class="comment_input" v-model="commentInput"></textarea>
          <textarea v-show="this.userInfo == null" disabled class="comment_input">로그인한 회원만 작성할 수 있습니다.</textarea>
        </b-row>

        <div>
          <b-row>
            <b-col class="text-left">
              <p class="comment_title">댓글</p>
            </b-col>
          </b-row>
          <p v-show="this.comment == null || this.comment.length == 0">등록된 댓글이 없습니다.</p>

          <b-card v-for="(item, index) in this.comment" :key="index" class="mb-2 text-left comment_item">
            <b-card-body class="text-left comment_item_body">
              <p class="comment_info">
                <span>{{ item.nickname }}({{ item.userId }})</span>
                <span class="ml-2">{{ item.createdTime | dateFormat }}</span>
                <span v-if="item.userId === asdasd" class="ml-2" @click="onCommentDelete(item.no)">삭제</span>
              </p>
              <div class="comment_content">{{ item.content }}</div>
            </b-card-body>
          </b-card>
        </div>
      </b-container>
    </div>
  </b-container>
</template>

<script>
import moment from "moment";
//
// getCommentList,
import { deleteArticle, getArticle, deleteComment, postComment, voteGood, voteBad } from "@/api/board";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
const boardStore = "boardStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      commentInput: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(boardStore, ["categoryName", "comment"]),
    category() {
      return this.$route.params.category;
    },
    asdasd() {
      return this.userInfo.id;
    },
  },
  created() {
    this.func1();
    this.asd();
  },
  watch: {},
  methods: {
    ...mapActions(boardStore, ["getComments"]),
    onCommentDelete(no) {
      deleteComment(
        no,
        () => {
          console.log("댓글 삭제 성공");
        },
        (error) => {
          console.log(error);
        }
      );
    },

    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    ondeleteArticle() {
      let param = this.$route.params.articleno;
      if (confirm("정말로 삭제?")) {
        deleteArticle(
          param,
          () => {
            console.log("삭제성공");
          },
          (error) => {
            console.log(error);
          }
        );
      }

      this.$router.push({
        name: "boardlist",
        params: { category: this.article.category },
      });
    },

    moveList() {
      this.$router.push({ name: "boardlist", params: { category: `${this.category}` } });
    },
    async func1() {
      let param = this.$route.params.articleno;
      console.log(10, param);
      await getArticle(
        param,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );

      console.log("게시글 불러오기 성공");
      console.log(3, this.article);
    },
    async asd() {
      let articleno = this.$route.params.articleno;
      await this.getComments(articleno);
      console.log("댓글 불러오기 성공");

      console.log(4, this.comment);
    },

    // 여기 impl
    onVoteBad() {
      voteBad(
        this.article.no,
        () => {
          //페이지 다시 생성,,
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onVoteGood() {
      voteGood(
        this.article.no,
        () => {
          //페이지 다시 생성,,
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onCommentRegist() {
      let comment = {
        userId: this.userInfo.id,
        nickname: this.userInfo.nickname,
        content: this.commentInput,
      };
      console.log("댓글써요 " + this.commentInput);
      postComment(
        comment,
        this.article.no,
        () => {
          //페이지 다시 생성,,
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm");
    },
  },
};
</script>

<style>
.container {
  font-family: "Helvetica";
}

.backlist_button {
  background-color: #ffffff;
  color: rgb(38, 38, 38);
  border: none;
  margin-bottom: 10px;
  font-weight: 500;
}

.post .card-header {
  padding-top: 20px;
  margin-bottom: 0;
  background-color: rgba(158, 66, 66, 0.03);
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}

.post .card-body {
  background-color: #fefdfd;
  padding: 0 0;
  padding-bottom: 20px;
}
.post_content {
  margin: 20px 20px;
}
.comment_title {
  margin-left: 10px;
  margin-top: 20px;
  font-family: "Helvetica";
  font-weight: 500;
  font-size: 18px;
  margin-bottom: 5px;
}
.comment .card-body {
  padding: 0px 0px;
}

.comment_item {
  color: rgb(63, 0, 0);
  background-color: #faf9f9;
  border: none;
  padding: 0px 0px;
}
.comment_input {
  border-color: #a6a6a6;
  background-color: #ffffff;
  color: rgb(19, 19, 19);
  font-size: 16px;
  height: 60px;
  /* margin-left: 16px;
  margin-right: 16px; */
  margin: 5px 16px;

  width: -webkit-fill-available;
  padding: 15px 15px;
  font-family: "Helvetica";
}
.comment_item .comment_info {
  font-size: 13px;
  color: #a6a6a6;
  margin-left: 26px;
  margin-top: 20px;
  margin-bottom: 0px;
}

.comment_item .comment_content {
  color: rgb(19, 19, 19);
  font-size: 16px;
  margin-left: 26px;
  margin-top: 15px;
  margin-bottom: 28px;
}

.new_comment_title {
  margin-left: 10px;
}

.new_comment_button {
  padding: 0.25rem 0.5rem;
  font-size: 13px;
  line-height: 1.3;
  border-radius: 0.2rem;
}

.comment {
  margin-top: 30px;
}

.vote button {
  margin-top: 20px;
  background-color: rgb(245, 245, 245);
  border-color: rgb(230, 230, 230);
  color: #454545;
}

.vote .good:hover {
  background-color: rgb(121, 154, 106);
  border-color: rgb(121, 154, 106);
  color: #ffffff;
}

.vote .bad:hover {
  background-color: #c68888;
  border-color: #c68888;
  color: #ffffff;
}
</style>
