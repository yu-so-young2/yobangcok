<template>
  <b-container >
    <div class="favoritediv">
      <div class="favoriteGroupdiv" v-for="(item, index) in this.favoriteList" :key="index">
          <h4 class="favoriteGrouph4" v-if="item[1].length!==0">{{item[0].groupNo}}</h4>
          <div style="
    width: 80%;
    margin: auto;
">
          <div class="favoriteGroup" v-for="(item1, index1) in item[1]" :key="index1">
            <!-- {{item1}} -->
            <h2>{{item1.apt_name}}</h2>
            <p>{{item1.sale_amount}}만원</p>
        </div>
        </div>
      </div>
    </div>
  </b-container>
</template>
<script>
import { mapActions, mapState } from 'vuex';
const houseStore = "houseStore";
const memberStore = "memberStore";
export default {
  name: "HouseFavorite",
  data() {
    return {
      
    }
  },
  mounted() {
    this.onFavorite()
  },
  computed: {
    ...mapState(houseStore, ["favoriteGroupList","favoriteList"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(houseStore, ["getFavoriteList","getFavoriteGroupList"]),
    async onFavorite(){
      await this.getFavoriteGroupList(this.userInfo.id)
      await this.getFavoriteList(this.favoriteGroupList)
    },
    async onfavorGroup(houseNo){
      this.favorGroupBool = !this.favorGroupBool
      this.houseNo = houseNo
      await this.getFavoriteGroupList(this.userInfo.id)
      // 
    },
  },
  

};
</script>
<style>
.favoriteGrouph4{
font-family: 'Arimo';
font-style: normal;
font-weight: 700;
font-size: 20px;
line-height: 23px;
/* identical to box height */
display: flex;
    margin-left: 228px;

color: #000000;
}
.favoritediv{
  margin-top: 100px;
}
.favoriteGroupdiv{
display: grid;
}
.favoriteGroup{
  background: #FFFFFF;
  box-shadow: 2px 2px 20px 5px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  width: 313px;
  height: 109px;
  float: left;
  margin: 40px;
  padding: 21px;
  text-align: left;
}
.favoriteGroup h2{
      font-family: "Arimo";
    font-style: normal;
    font-weight: 700;
    font-size: 17px;
    line-height: 29px;
    color: #393939;
}
</style>
