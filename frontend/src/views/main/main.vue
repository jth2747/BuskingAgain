<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      :token="token"
      @openLoginDialog="onOpenLoginDialog"
      @openSignupDialog="onOpenSignupDialog"
      @openProfileDialog="onOpenProfileDialog"
      @click-logout="clickLogout"/>
    <el-container class="main-container">
      <el-aside class="hide-on-small" width="240px">
        <main-sidebar
          :width="`240px`"
          :isLoding="isLoding"
          :token="token"
          />
      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
    <main-footer :height="`110px`"/>
  </el-container>
  <login-dialog
    :open="loginDialogOpen"
    :isLoding="isLoding"
    @closeLoginDialog="onCloseLoginDialog"
    @start-loding="startLoding"
    />
  <signup-dialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"/>
  <profile-dialog
    :open="profileDialogOpen"
    :userInfo="userInfo"
    :token="token"
    @closeProfileDialog="onCloseProfileDialog"
    @click-logout="clickLogout"/>
</template>
<style>
  @import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
  @import './main.css';
  @import '../../common/css/common.css';
  @import '../../common/css/element-plus.css';

</style>
<script>
import LoginDialog from './components/login-dialog'
import SignupDialog from './components/signup-dialog'
import MainHeader from './components/main-header'
import MainSidebar from './components/main-sidebar'
import MainFooter from './components/main-footer'
import ProfileDialog from './components/profile-dialog.vue'
import RingLoader from 'vue-spinner/src/RingLoader.vue'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog,
    SignupDialog,
    ProfileDialog,
    RingLoader,
  },
  data () {
    return {
      loginDialogOpen: false,
      signupDialogOpen: false,
      profileDialogOpen: false,
      isLoding: false, //스피너
      token: localStorage.getItem('jwt'), // jwt 토큰
      userInfo: '',
    }
  },
  methods: {
    onOpenLoginDialog () {
      this.loginDialogOpen = true
    },
    onCloseLoginDialog () {
      this.loginDialogOpen = false
    },
    onOpenSignupDialog() {
      this.signupDialogOpen = true
    },
    onCloseSignupDialog() {
      this.signupDialogOpen = false
    },
    onOpenProfileDialog(result){
      this.profileDialogOpen = true
      this.userInfo = result
    },
    onCloseProfileDialog(){
      this.profileDialogOpen=false
    },
    // 로딩 스피너 작동하게 하는 함수
    startLoding(){
      this.isLoding=true
      setTimeout(() => {
        this.isLoding = false
      }, 500)
    },
    // 로그아웃 버튼을 누르면 토큰 사라짐
    clickLogout(){
      localStorage.removeItem('jwt')
      location.reload()
    },
  }
}
</script>
