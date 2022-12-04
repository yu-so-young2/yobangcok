import { login, findById, createUser, updateUser, deleteUser, idDuplecateCheck } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    users: [], //가입시 추가
    isLogin: false,
    isLoginError: false, //로그인 실패(아이디 비밀번호 확인)
    userInfo: null, //로그인 유저()
    isValidToken: false,
    isIdDuplicated: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    CREATE_USER: function (state, user) {
      state.users.push(user);
    },
    SET_IS_LOGIN: (state, isLogin) => {
      console.log(1, isLogin);
      console.log(2, state.isLogin);
      state.isLogin = isLogin;
      console.log(3, state.isLogin);
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      console.log("SET login Error " + isLoginError);
      state.isLoginError = isLoginError;
      console.log("SET login Error " + isLoginError);
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    UPDATE_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
    SET_ID_DUPLICATED: (state, isIdDuplicated) => {
      state.isIdDuplicated = isIdDuplicated;
    },
  },
  actions: {
    async idDuplicateCheck({ commit }, id) {
      console.log("idDuplicateCheck");
      await idDuplecateCheck(
        id,
        () => {
          //없음
          commit("SET_ID_DUPLICATED", false);
        },
        (error) => {
          //있음
          commit("SET_ID_DUPLICATED", true);
          console.log(error);
        }
      );
    },

    async userDelete({ commit }, id) {
      console.log("userDelete(" + id + ")");
      await deleteUser(
        id,
        () => {
          commit("SET_IS_LOGIN", false);
          commit("SET_USER_INFO", null);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async regist({ commit }, user) {
      console.log("regist(" + user + ")");
      await createUser(
        user,
        () => {
          console.log(user);
          commit("CREATE_USER", user);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userUpdate({ commit }, user) {
      console.log("userUpdate(" + user + ")");
      await updateUser(
        user,
        () => {
          console.log(user);
          commit("UPDATE_USER_INFO", user);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async userConfirm({ commit }, user) {
      console.log("userConfirm(" + user + ")");
      await login(
        user,
        ({ data }) => {
          //로그인 성공
          // console.log("login success token created!!!! >> ", accessToken, refreshToken);
          console.log("로그인성공");
          commit("SET_USER_INFO", data);
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
        },
        (error) => {
          //로그인 실패
          console.log(error);
          console.log("로그인실패");
          commit("SET_IS_LOGIN", false);
          commit("SET_USER_INFO", null);
          commit("SET_IS_LOGIN_ERROR", true);
        }
      );
    },
    async getUserInfo({ commit }, userid) {
      console.log("getUserInfo(" + userid + ")");
      // console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        userid,
        ({ data }) => {
          commit("SET_USER_INFO", data);
        },
        async (error) => {
          console.log(error);
        }
      );
    },
    async userLogout({ commit }) {
      console.log("userLogout(" + ")");
      await commit("SET_IS_LOGIN", false);
      await commit("SET_USER_INFO", null);
      await commit("SET_IS_LOGIN_ERROR", false);
    },
  },
};

export default memberStore;
