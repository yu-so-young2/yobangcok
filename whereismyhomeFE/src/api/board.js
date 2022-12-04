import { apiInstance } from "./index.js";

const api = apiInstance();

async function listArticle(category, success, fail) {
  await api.get(`/board/${category}/all`).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  await api.post(`/board/regist`, article).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  await api.get(`/board/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  await api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

// 좋아요/싫어요 관련
async function voteGood(articleno, success, fail) {
  await api.put(`/board/good/${articleno}`).then(success).catch(fail);
}
async function voteBad(articleno, success, fail) {
  await api.put(`/board/bad/${articleno}`).then(success).catch(fail);
}

// 댓글 관련

async function getCommentList(articleno, success, fail) {
  await api.get(`/board/comment/${articleno}`).then(success).catch(fail);
}
async function postComment(comment, articleno, success, fail) {
  await api.post(`/board/comment/${articleno}`, comment).then(success).catch(fail);
}
async function deleteComment(commentno, success, fail) {
  await api.delete(`/board/comment/${commentno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle, voteGood, voteBad, getCommentList, postComment, deleteComment };
