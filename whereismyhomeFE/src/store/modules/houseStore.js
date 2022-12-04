
import { sidoList, gugunList, houseList,aptSearchList,aptSaleList,aptSaleList1,
  dongSearchList,dongAutosugestionList ,aptAutosugestionList,
  aptDealList,aptSaleInsert,aptSaleByUserIdList,favoriteGroupList
  ,favoriteAdd,favoriteList,favoriteGroupInsert} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    houses: [],
    housesSearch: [],
    housesSearchText: "",
    housesSearchCode: "",
    housesSearchCatecory: "",
    housesDealList: [],
    housesSealList: [],
    housesUserSaleList: [],
    house: null,
    isHouseInsert: false,
    favoriteGroupList: [],
    favoriteList: [],
    housesDealListChart: null,
  },
  getters: {},
  mutations: {
    SET_FAVORITE_LIST_EMP(state){
      state.favoriteList = []
    },
    SET_EMP(){
  
    },
    SET_FAVORITE_LIST(state,data){
      state.favoriteList.push(data) 
    },
    SET_FAVORITE_GROUP_LIST(state, data) {
      state.favoriteGroupList = data;
    },
    SET_USER_SALE_LIST(state, data) {
      state.housesUserSaleList = data;
    },
    SET_INSERT_LIST(state, data) {
      state.isHouseInsert = data;
    },
    SET_HOUSES_DEAL_LIST(state, data) {
      state.housesDealList = data;
    },
    SET_HOUSES_SEAL_LIST(state, data) {
      state.housesSealList = data;
    },
    SET_HOUSESSEARCH_LIST(state, data) {
      state.housesSearch = data;
    },
    SET_HOUSE_SEARCH_TEXT(state, data) {
      state.housesSearchCode = data[0];
      state.housesSearchText = data[1];
      state.housesSearchCatecory = data[2];
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
  },
  actions: {
    async dongSearch({ commit }, dongCode) {
      const params = { dongCode: dongCode[0] };
      commit("SET_HOUSE_SEARCH_TEXT", dongCode);
      await dongSearchList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async aptSearch({ commit }, dongtext) {
      // console.log(dongtext)
      const params = { apartmentName: dongtext[1], dongCode: dongtext[0] };
      commit("SET_HOUSE_SEARCH_TEXT", dongtext);
      await aptSearchList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async dongAutosugestion({ commit }, content) {
      console.log(content);
      const params = { content: content };

      await dongAutosugestionList(
        params,
        ({ data }) => {
          // console.log(data)
          commit("SET_HOUSESSEARCH_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async aptAutosugestion({ commit }, content) {
      // console.log(content)
      const params = { content: content };
      await aptAutosugestionList(
        params,
        ({ data }) => {
          // console.log(data)
          commit("SET_HOUSESSEARCH_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_HOUSESSEARCH_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, gugunCode) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202207",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
    getHouseDealList: ({ commit }, aptCode) => {
      const params = {
        aptCode: aptCode,
      };
      aptDealList(
        params,
        ({ data }) => {
          commit("SET_HOUSES_DEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseSaleList: ({ commit }, aptCode) => {
      const params = {
        aptCode: aptCode,
      };
      aptSaleList(
        params,
        ({ data }) => {
          console.log("?");
          commit("SET_HOUSES_SEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    setAptSale({ commit }, data) {
      const params = {
        apt_code: data[0],
        user_id: data[1],
        sale_content: data[2],
        sale_amount: data[3],
        apt_name: data[4],
        apt_dong: data[5],
        apt_ho: data[6],
      };
      aptSaleInsert(
        params,
        async () => {
          await commit("SET_INSERT_LIST", true);
        },
        async (error) => {
          await commit("SET_INSERT_LIST", false);
          console.log(error);
        }
      );
    },
    async getAptSaleByUserIdList({ commit }, userid) {
      const params = { user_id: userid };
      console.log(JSON.stringify(params));
      await aptSaleByUserIdList(
        params,
        (data) => {
          commit("SET_USER_SALE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getFavoriteGroupList({ commit }, userid) {
      await favoriteGroupList(
        userid,
        ({ data }) => {
          console.log(data);
          commit("SET_FAVORITE_GROUP_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async setFavorite({ commit }, data) {
      const params = {
        aptSaleNo: data[0],
        favoritegroup: data[1],
      };
      console.log(params);
      await favoriteAdd(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_EMP", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getFavoriteList({ commit }, favoriteGroupList1) {
      console.log(favoriteGroupList1)
      commit("SET_FAVORITE_LIST_EMP");
      favoriteGroupList1.forEach(element => {
        console.log(element.groupNo)
        favoriteList(
          element.groupNo,
          ({data}) => {
            var favorite_list =[]
            console.log(data)
            data.forEach(element1 => {
              console.log(element1)
              aptSaleList1(
                element1.aptSaleNo,
                ({ data }) => {
                  console.log(data)
                  favorite_list.push(data)
                  // commit("SET_HOUSES_SEAL_LIST", data);
                },
                (error) => {
                  console.log(error);
                }
                );
                console.log(favorite_list)
              });
              commit("SET_FAVORITE_LIST", [{"groupNo":element.groupName},favorite_list]);
            },
          (error) => {
            console.log(error);
          }
        );
      });
    },
    async setFavoriteGroup({commit},data){
      const params = {
        userId: data[1],
        groupName: data[0],
      };
      await favoriteGroupInsert(
        params,
        () => {
          console.log("성공")
          commit("SET_EMP", params);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
