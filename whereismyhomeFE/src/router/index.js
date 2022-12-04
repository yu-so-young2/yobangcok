import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import AppDeal from "@/views/AppDeal";
import AppSell from "@/views/AppSell";
import AppBoard from "@/views/AppBoard";
import AppUser from "@/views/AppUser";

import store from "@/store";

Vue.use(VueRouter);

const onlyUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  console.log("로그인 처리 전", checkUserInfo);

  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/deal",
    name: "deal",
    component: AppDeal,
    redirect: "/deal/housedeal",
    children: [
      {
        path: "/deal/housedeal",
        name: "housedeal",
        component: () => import("@/components/house/HouseDeal"),
      },
      {
        path: "/deal/housesale",
        name: "housesale",
        component: () => import("@/components/house/HouseSale"),
      },
      {
        path: "/deal/myfavorite",
        name: "myfavorite",
        beforeEnter: onlyUser,
        component: () => import("@/components/house/HouseFavorite"),
      },
      {
        path: "/deal/housesale/:no",
        name: "saleList",
        component: () => import("@/components/house/HouseSell"),
      },
    ],
  },
  {
    path: "/sell",
    name: "sell",
    component: AppSell,
    redirect: "/sell/regist",
    children: [
      {
        path: "/sell/regist",
        name: "regist",
        beforeEnter: onlyUser,
        component: () => import("@/components/house/HouseRegist"),
      },
      {
        path: "/sell/mysell",
        name: "mysell",
        component: () => import("@/components/house/HouseRegisted"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: AppBoard,
    redirect: "/board/1",
    children: [
      // {
      //   path: "/board/board1",
      //   name: "board1",
      //   component: () => import("@/components/board/BoardList1"),
      // },
      // {
      //   path: "/board/board2",
      //   name: "board2",
      //   component: () => import("@/components/board/BoardList2"),
      // },
      // {
      //   path: "/board/board3",
      //   name: "board3",
      //   component: () => import("@/components/board/BoardList3"),
      // },
      // {
      //   path: "/board/board4",
      //   name: "board4",
      //   component: () => import("@/components/board/BoardList4"),
      // },

      // board CRUD 관련
      {
        path: "/board/:category",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyUser,
        component: () => import("@/components/board/item/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/item/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/item/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        // beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/item/BoardDelete"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: AppUser,
    children: [
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        component: () => import("@/components/user/UserMyPage"),
      },
      {
        path: "mypageModify",
        name: "mypageModify",
        component: () => import("@/components/user/UserMyPageModify"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
