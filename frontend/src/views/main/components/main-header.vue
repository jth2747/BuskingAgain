<template>
  <el-row
    v-if="token"
    class="main-header"
    :gutter="20"
    :style="{ 'height': height }">
    <div class="hide-on-small">
      <span @click="drawer = true" type="primary" style="margin: 16px;  cursor : pointer;">
        <i class="el-icon-menu" style="margin-top:10px; font-size: 20px; color: #FFFFFF"></i>
      </span>
      <el-drawer
        title="I am the title"
        v-model="drawer"
        :direction="direction"
        :before-close="handleClose"
        :with-header="false"
        >
        <div></div>
        <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>
        <div class="mobile-sidebar">
          <el-menu
            :default-active="String(state.activeIndex)"
            active-text-color="#ffd04b"
            class="el-menu-vertical-demo"
            @select="menuSelect">
            <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
              <i v-if="item.icon" :class="['ic', item.icon]"/>
              <span>{{ item.title }}</span>
            </el-menu-item>
          </el-menu>
        </div>
      </el-drawer>
      <div class="tool-wrapper">
        <!-- <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div> -->
        <div class="button-wrapper">
          <el-popover placement="bottom" title="버스킹 생성" trigger="hover">
            <template #reference>
              <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF"  @click="clickBusking" v-if="this.token" class="el-icon-plus"></span>
            </template>
          </el-popover>
          <el-popover placement="bottom" title="선호 장르 선택" trigger="hover">
            <template #reference>
              <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF"  @click="clickGenre" v-if="this.token" class="el-icon-star-on"></span>
            </template>
          </el-popover>
          <el-popover placement="bottom" title="회원 정보" trigger="hover">
            <template #reference>
              <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF"  @click="clickProfile" v-if="this.token" class="el-icon-user"></span>
            </template>
          </el-popover>
          <el-popover placement="bottom" title="로그아웃" trigger="hover">
            <template #reference>
              <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF"  @click="clickLogout" v-if="this.token" class="el-icon-unlock"></span>
            </template>
          </el-popover>
        </div>
      </div>
    </div>


    <div class="hide-on-big">
      <!-- <div class="menu-icon-wrapper" @click="changeCollapse"><i class="el-icon-menu"></i></div> -->
      <span @click="drawer = true" type="primary" style="margin: 16px;  cursor : pointer;">
        <i class="el-icon-menu" style="margin-top:10px; font-size: 20px"></i>
      </span>
      <el-drawer
        title="I am the title"
        v-model="drawer"
        :direction="direction"
        :before-close="handleClose"
        :with-header="false"
        >
        <div></div>
        <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>
        <div class="mobile-sidebar">
          <div class="text-center">
            <el-popover placement="bottom" title="버스킹 생성" trigger="hover">
              <template #reference>
                <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px;"  @click="clickBusking" v-if="this.token" class="el-icon-plus"></span>
              </template>
            </el-popover>
            <el-popover placement="bottom" title="선호 장르 선택" trigger="hover">
              <template #reference>
                <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px"  @click="clickGenre" v-if="this.token" class="el-icon-star-on"></span>
              </template>
            </el-popover>
            <el-popover placement="bottom" title="회원 정보" trigger="hover">
              <template #reference>
                <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px"  @click="clickProfile" v-if="this.token" class="el-icon-user"></span>
              </template>
            </el-popover>
            <el-popover placement="bottom" title="로그아웃" trigger="hover">
              <template #reference>
                <span type="medium" style="cursor:pointer; margin-right: 20px; font-size: 20px"  @click="clickLogout" v-if="this.token" class="el-icon-unlock"></span>
              </template>
            </el-popover>
          </div>
          <el-menu
            :default-active="String(state.activeIndex)"
            active-text-color="#ffd04b"
            class="el-menu-vertical-demo"
            @select="menuSelect">
            <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
              <i v-if="item.icon" :class="['ic', item.icon]"/>
              <span>{{ item.title }}</span>
            </el-menu-item>
          </el-menu>
        </div>
      </el-drawer>
      <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>
    </div>
  </el-row>


  <el-row
    v-else
    class="main-header"
    :gutter="10"
    :style="{ 'height': height }">
    <div class="hide-on-small">
      <!-- <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div> -->
      <span @click="drawer = true" type="primary" style="margin: 16px;  cursor : pointer;">
        <i class="el-icon-menu" style="margin-top:10px; font-size: 20px"></i>
      </span>
      <el-drawer
        title="I am the title"
        v-model="drawer"
        :direction="direction"
        :before-close="handleClose"
        :with-header="false"
        >
        <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>
        <div class="mobile-sidebar">
          <el-menu
            :default-active="String(state.activeIndex)"
            active-text-color="#ffd04b"
            class="el-menu-vertical-demo"
            @select="menuSelect">
            <div v-if="token">
              <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
                <i v-if="item.icon" :class="['ic', item.icon]"/>
                <span>{{ item.title }}</span>
              </el-menu-item>
            </div>
            <div v-else>
              <el-menu-item @click="clickHome">
                <i :class="['ic', state.menuItems[0].icon]"/>
                {{ state.menuItems[0].title}}
              </el-menu-item>
            </div>
          </el-menu>
        </div>
      </el-drawer>
      <div class="tool-wrapper">
        <div class="button-wrapper">
          <el-popover placement="bottom" title="회원가입" trigger="hover">
            <template #reference>
              <span style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF" @click="clickSignup" class="el-icon-circle-plus-outline"></span>
            </template>
          </el-popover>
          <el-popover placement="bottom" title="로그인" trigger="hover">
            <template #reference>
              <span style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF" @click="clickLogin" class="el-icon-lock"></span>
            </template>
          </el-popover>
          <el-popover placement="bottom" title="Id/Password 찾기" trigger="hover">
            <template #reference>
              <span style="cursor:pointer; margin-right: 20px; font-size: 20px; color: #FFFFFF" @click="findIdPassword" class="el-icon-key"></span>
            </template>
          </el-popover>
        </div>
        <div class="mobile-sidebar-backdrop" @click="changeCollapse"></div>
      </div>
    </div>



    <div class="hide-on-big">
      <span @click="drawer = true" type="primary" style="margin: 16px;  cursor : pointer;">
        <i class="el-icon-menu" style="margin-top:10px; font-size: 20px"></i>
      </span>
      <el-drawer
        title="I am the title"
        v-model="drawer"
        :direction="direction"
        :before-close="handleClose"
        :with-header="false"
        >
        <div></div>
        <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>

        <div class="mobile-sidebar">
          <div class ="text-center">
            <el-popover placement="bottom" title="회원가입" trigger="hover">
              <template #reference>
                <span style="cursor:pointer; margin-right: 20px; font-size: 20px" @click="clickSignup" class="el-icon-circle-plus-outline"></span>
              </template>
            </el-popover>
            <el-popover placement="bottom" title="로그인" trigger="hover">
              <template #reference>
                <span style="cursor:pointer; margin-right: 20px; font-size: 20px" @click="clickLogin" class="el-icon-lock"></span>
              </template>
            </el-popover>
            <el-popover placement="bottom" title="Id/Password 찾기; font-size: 20px" trigger="hover">
              <template #reference>
                <span style="cursor:pointer; margin-right: 20px" @click="findIdPassword" class="el-icon-key"></span>
              </template>
            </el-popover>
          </div>
          <el-menu
              :default-active="String(state.activeIndex)"
              active-text-color="#ffd04b"
              class="el-menu-vertical-demo"
              @select="menuSelect">
              <div v-if="token">
                <el-menu-item v-for="(item, index) in state.menuItems" :key="index" :index="index.toString()">
                  <i v-if="item.icon" :class="['ic', item.icon]"/>
                  <span>{{ item.title }}</span>
                </el-menu-item>
              </div>
              <div v-else>
                <el-menu-item @click="clickHome">
                  <i :class="['ic', state.menuItems[0].icon]"/>
                  {{ state.menuItems[0].title}}
                </el-menu-item>
              </div>
            </el-menu>
        </div>

      </el-drawer>
      <div class="logo-wrapper" @click="clickLogo"><div class="ic ic-logo"/></div>


    </div>
  </el-row>

  <busking-dialog
        :open="buskingDialogOpen"
        :token="token"
        @closeBuskingDialog="onCloseBuskingDialog"/>

</template>
<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import $axios from "axios"
import BuskingDialog from '../components/busking-dialog.vue'


const SERVER_URL = "http://localhost:8080";


export default {
  name: 'main-header',
  props: {
    height: {
      type: String,
      default: '70px'
    },
    token: {
      type: String,
      dataule: ''
    },
  },

  components: {
    BuskingDialog
  },

  data() {
    return {
      buskingDialogOpen:false,
      token: localStorage.getItem('jwt'), // jwt 토큰
      image: 'https://www.ssafy.com/swp/images/sns_img.png',
      value: '',
      drawer: false,
      direction: 'ltr',
    }
  },

  methods:{
    clickBusking(){
      console.log("inin");
      this.buskingDialogOpen=true
    },
    onCloseBuskingDialog(){
      this.buskingDialogOpen=false
    },
    // handleClose(done) {
    //     this.$confirm('Are you sure you want to close this?')
    //       .then(_ => {
    //         done();
    //       })
    //       .catch(_ => {});
    //   }
  },

  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      isCollapse: true,
      menuItems: computed(() => {
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        let menuArray = []
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {}
          menuObject.icon = MenuItems[keys[i]].icon
          menuObject.title = MenuItems[keys[i]].name
          menuArray.push(menuObject)
        }
        return menuArray
      }),
      activeIndex: computed(() => store.getters['root/getActiveMenuIndex'])
    })

    if (state.activeIndex === -1) {
      state.activeIndex = 0
      store.commit('root/setMenuActive', 0)
    }

    const menuSelect = function (param) {
      store.commit('root/setMenuActive', param)
      const MenuItems = store.getters['root/getMenus']
      let keys = Object.keys(MenuItems)
      router.push({
        name: keys[param]
      })
    }

    const clickLogo = () => {
      store.commit('root/setMenuActive', 0)
      const MenuItems = store.getters['root/getMenus']
      let keys = Object.keys(MenuItems)
      router.push({
        name: keys[0]
      })
    }

    const clickLogin = () => {
      emit('openLoginDialog')
    }

    const clickSignup = () => {
      emit('openSignupDialog')
    }

    const clickGenre = () => {
      emit('openGenreDialog')
    }



    // const clickProfile = function() {
    //   // const token = localStorage.getItem('jwt')
    //   store.dispatch('root/getUser', { token: props.token })
    //   .then(function (result) {
    //     console.log(result.data)
    //     emit('openProfileDialog', result)
    //   })
    //   .catch(function (err){
    //     alert(err)
    //   })
    // }

    const submit = function() {
      console.log("엔터입력")
      console.log(state.searchValue)
      store.dispatch('root/findRoomList',{
        title: state.searchValue
      })
      .then(function(result){
        console.log(result.data)
        // .push(result.data)
        // console.log(buskingList[0][0].title)
      })
    }

    const clickProfile = function() {
      emit('openPasswordCheckDialog')
    }


    const changeCollapse = () => {
      state.isCollapse = !state.isCollapse
    }

    const clickLogout = function () {
      emit('click-logout')
    }
    const findIdPassword = function () {
      emit('openIdPasswordDialog')
    }


    return { state, menuSelect,clickLogo, clickLogin, clickSignup, changeCollapse, clickProfile, clickLogout, clickGenre, findIdPassword }
  }
}
</script>
<style>
  .logo{
    width: 70px;
    height: 50px;
    background-image: url('../../../assets/images/logo4.png');
  }
  .text-center{
    margin-left: 50px;
  }
  .main-header {
    padding: 10px 20px;
  }
  /*Mobile, Tablet*/
  .menu-icon-wrapper {
    display: inline-block;
    vertical-align: top;
    position: relative;
    top: 14px;
  }

  .main-header .hide-on-big .logo-wrapper {
    display: inline-block;
    margin: 0 calc(50% - 51px)
  }

  .main-header .hide-on-big .logo-wrapper .ic.ic-logo {
    width: 70px;
    height: 50px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/logo4.png');
  }
  .mobile-sidebar-wrapper {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }
  .mobile-sidebar-wrapper .mobile-sidebar {
    width: 240px; height: calc(100vh - 1px);
    display: inline-block;
    background-color: white;
    padding: 0 10px;
    vertical-align: top;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-tool-wrapper {
    padding-bottom: 20px;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-btn {
    display: block;
    margin: 0 auto;
    margin-top: 25px;
    height: 30px;
    width: 100%;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .mobile-sidebar-btn.login-btn {
    color: white;
  }
  .mobile-sidebar-wrapper .mobile-sidebar .logo-wrapper {
    display: block
  }
  .mobile-sidebar-wrapper .mobile-sidebar .logo-wrapper .ic.ic-logo {
    width: 70px;
    height: 50px;
    margin: 0 auto;
    margin-top: 30px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/logo4.png');
  }
  .mobile-sidebar-wrapper .mobile-sidebar-backdrop {
    width: calc(100% - 260px); height: calc(100vh - 1px);
    background-color: black;
    display: inline-block;
    opacity: 0.3;
  }
  .mobile-sidebar-wrapper .el-menu{
    margin-top: 0;
    padding-left: 0;
    height: calc(100% - 235px);
  }
  .mobile-sidebar-wrapper .el-menu .el-menu-item {
    cursor: pointer;
  }
  .mobile-sidebar-wrapper .el-menu .el-menu-item .ic {
    margin-right: 5px;
  }

  /*Desktop - Need to add Class if Need*/
  .main-header .hide-on-small .logo-wrapper {
    cursor: pointer;
    display: inline-block;
  }
  .main-header .hide-on-small .logo-wrapper .ic.ic-logo {
    width: 250px;
    height: 80px;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/logo4.png');
  }
  .main-header .hide-on-small .tool-wrapper {
    width: 60%;
    float: right;
  }
  .main-header .hide-on-small .tool-wrapper .button-wrapper {
    /* width: 45%; */
    float: right;
  }
  .main-header .hide-on-small .tool-wrapper .button-wrapper .el-button {
    width: 30%;
    height: 50px;
    cursor: pointer;
    margin-right: 1%;
  }
  .main-header .hide-on-small .tool-wrapper .search-field {
    width: 50%;
    height: 50px;
    max-width: 400px;
    margin-right: 2%;
    display: inline-block;
    background-color: white;
  }
  .main-header .hide-on-small .tool-wrapper .search-field .el-input {
    width: 100%;
    height: 100%;
  }
  .main-header .hide-on-small .tool-wrapper .search-field .el-input .el-input__inner {
    width: 80%;
    height: 50px;
    margin-right: 0%;
  }
  .main-header .hide-on-small .tool-wrapper .search-field .el-input .el-input__prefix {
    top: 5px;
  }

</style>
