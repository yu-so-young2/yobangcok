import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

function dongSearchList(params, success, fail) {
  house.get(`/housesearch/dong`,{ params: params } ).then(success).catch(fail);
}
function aptSearchList(params, success, fail) {
  house.get(`/housesearch/apt`,{ params: params } ).then(success).catch(fail);
}
function dongAutosugestionList(params, success, fail) {
  house.get(`/housesearch/autosugestion/dong`,{ params: params } ).then(success).catch(fail);
}
function aptAutosugestionList(params, success, fail) {
  house.get(`/housesearch/autosugestion/apt`,{ params: params } ).then(success).catch(fail);
}
function aptDealList(params, success, fail) {
  house.get(`/housedeal/apt`,{ params: params } ).then(success).catch(fail);
}
function aptSaleList(params, success, fail) {
  house.get(`/housesale`,{ params: params } ).then(success).catch(fail);
}
function aptSaleList1(params, success, fail) {
  house.get(`/housesale/${params}`).then(success).catch(fail);
}
function aptSaleInsert(params, success, fail) {
  house.post(`/housesale/regist`,JSON.stringify(params) ).then(success).catch(fail);
}
// 유저가 등록한 매물 정보 가져오기
function aptSaleByUserIdList(params, success, fail) {
  house.get(`/housesale/user`,{ params: params }).then(success).catch(fail);
}

function favoriteGroupList(userid, success, fail) {
  house.get(`/users/${userid}/favorite/group`).then(success).catch(fail);
}

function favoriteAdd(params, success, fail) {
  house.post(`/users/favorite`,JSON.stringify(params) ).then(success).catch(fail);
}

function favoriteList(params, success, fail) {
  house.get(`/users/favorite?favoritegroup=${params}`).then(success).catch(fail);
}
function favoriteGroupInsert(params, success, fail){
  house.post(`/users/favorite/group`,JSON.stringify(params)).then(success).catch(fail);
}


export { sidoList, gugunList, houseList ,dongSearchList,
        dongAutosugestionList,aptAutosugestionList,aptSaleList,aptSaleList1,
        aptSearchList,aptDealList,aptSaleInsert,aptSaleByUserIdList,
        favoriteGroupList,favoriteAdd,favoriteList,favoriteGroupInsert};
