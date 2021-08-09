<template>
  <el-container class="main-wrapper">
    <main-header
      :height="`70px`"
      :token="token"
      @openPasswordCheckDialog="onOpenPasswordCheckDialog"
      @openLoginDialog="onOpenLoginDialog"
      @openSignupDialog="onOpenSignupDialog"
      @openGenreDialog="onOpenGenreDialog"
      @openIdPasswordDialog="onOpenIdPasswordDialog"
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
  <id-password-dialog
    :open="idPasswordDialogOpen"
    :userInfo="userInfo"
    :token="token"
    @closeIdPasswordDialog="onCloseIdPasswordDialog"
  />
  <genre-dialog
    :open="genreDialogOpen"
    :token="token"
    :genreList="genreList"
    @closeGenreDialog="onCloseGenreDialog"/>
  <signup-dialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"/>
  <password-check-dialog
    :open="passwordCheckDialogOpen"
    :token="token"
    @closePasswordCheckDialog="onClosePasswordCheckDialog"
    @openProfileDialog="onOpenProfileDialog"/>
  <password-dialog
    :open="passwordDialogOpen"
    :token="token"
    :userInfo="userInfo"
    @closePasswordDialog="onClosePasswordDialog"/>
  <profile-dialog
    :open="profileDialogOpen"
    :userInfo="userInfo"
    :token="token"
    @openPasswordDialog="onOpenPasswordDialog"
    @closeProfileDialog="onCloseProfileDialog"
    @openDeleteConfirmDialog="onOpenDeleteConfirmDialog"
    @click-logout="clickLogout"/>
  <delete-confirm-dialog
    :open="deleteConfirmDialogOpen"
    :userInfo="userInfo"
    :token="token"
    @closeDeleteConfirmDialog="onCloseDeleteConfirmDialog"
  />

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
import PasswordDialog from './components/password-dialog.vue'
import RingLoader from 'vue-spinner/src/RingLoader.vue'
import GenreDialog from './components/genre-dialog.vue'
import PasswordCheckDialog from './components/password-check-dialog.vue'
import DeleteConfirmDialog from './components/delete-confirm-dialog.vue'
import IdPasswordDialog from './components/id-password-dialog.vue'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
    LoginDialog,
    SignupDialog,
    ProfileDialog,
    PasswordDialog,
    GenreDialog,
    PasswordCheckDialog,
    DeleteConfirmDialog,
    IdPasswordDialog,
    RingLoader,
  },
  data () {
    return {
      loginDialogOpen: false,
      signupDialogOpen: false,
      profileDialogOpen: false,
      genreDialogOpen:false,
      isLoding: false, //스피너
      token: localStorage.getItem('jwt'), // jwt 토큰
      genreList:[],
      userInfo: '',
      passwordDialogOpen: false,
      passwordCheckDialogOpen: false,
      deleteConfirmDialogOpen: false,
      idPasswordDialogOpen: false,
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
    onOpenGenreDialog() {
      this.genreDialogOpen = true
    },
    onCloseGenreDialog() {
      this.genreDialogOpen = false
    },
    onOpenProfileDialog(result){
      this.profileDialogOpen = true
      this.userInfo = result
    },
    onCloseProfileDialog(){
      this.profileDialogOpen=false
    },
    onOpenPasswordCheckDialog(){
      this.passwordCheckDialogOpen = true
      console.log(this.passwordCheckDialogOpen)
    },
    onClosePasswordCheckDialog(){
      this.passwordCheckDialogOpen = false
    },
    onClosePasswordDialog(){
      this.passwordDialogOpen=false
    },
    onOpenPasswordDialog(){
      this.passwordDialogOpen=true
    },
    onOpenDeleteConfirmDialog(){
      this.deleteConfirmDialogOpen=true
    },
    onCloseDeleteConfirmDialog(){
      this.deleteConfirmDialogOpen=false
    },
    onOpenIdPasswordDialog(){
      this.idPasswordDialogOpen=true
    },
    onCloseIdPasswordDialog(){
      this.idPasswordDialogOpen=false
    },
    // 로딩 스피너 작동하게 하는 함수
    startLoding(){
      this.isLoding= true
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
