<template>
  <div id="app">
    <the-header-navbar></the-header-navbar>
    <transition name="routing-fade">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
  },
  data() {
    return {
      transitionName: "",
    };
  },
  watch: {
    $route(to, from) {
      if (to.meta.page == null || from.meta.page == null) {
        this.transitionName = "fade";
      } else {
        this.transitionName = to.meta.page > from.meta.page ? "next" : "prev";
      }
      console.log(this.transitionName);
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100%;
  padding-bottom: 100px;
}

a.link:hover {
  color: #4f4f4f;
  text-decoration-line: none;
}

/* 전환효과 (Fade) */
.routing-fade-enter-active {
  transition: opacity 1s;
}
.routing-fade-enter,
.routing-fade-leave-to {
  opacity: 0;
}
</style>
