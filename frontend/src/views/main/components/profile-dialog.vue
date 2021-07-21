<template>
  <el-dialog custom-class="profile-dialog" title="회원정보" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="genre" label="장르" :label-width="state.formLabelWidth" >
        <el-form-item v-model="genre" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
        <el-form-item v-model="email" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth" >
        <el-form-item v-model="name" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth" >
        <el-form-item v-model="userId" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-form-item v-model="password" autocomplete="off" show-password></el-form-item>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="warning"  @click="updateUser()">정보수정</el-button>
        <el-button type="danger" @click="deleteUser()">회원탈퇴</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.user-dialog {
  width: 400px !important;
  height: 550px;
}
.user-dialog .el-dialog__headerbtn {
  float: right;
}
.user-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.user-dialog .el-form-item {
  margin-bottom: 20px;
}
.user-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.user-dialog .el-input__suffix {
  display: none;
}
.user-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.user-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import axios from "axios";
const SERVER_URL = "http://localhost:8080";
import { mapGetters } from "vuex";
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'profile-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  data(){
    return {
      update: false,
    };
  },
  computed: {
    ...mapGetters(["profileDialog", "getAccessToken", "user"]),
  },

   methods: {
    updateSetting() {
      this.update = true;
    },
    updateUser() {
      console.log(this.user + " " + this.getAccessToken);
      axios.defaults.headers.common["auth-token"] = this.getAccessToken;
      axios
        .patch(`${SERVER_URL}/users/${this.user.userId}`, {
          genre: this.user.genre,
          email: this.user.email,
          name: this.user.name,
          userId: this.user.userId,
          password: this.user.password,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.update = false;
        });
    },
    deleteUser() {
      axios.defaults.headers.common["auth-token"] = this.getAccessToken;
      axios
        .delete(`${SERVER_URL}/users/${this.user.userId}`)
        .then(({ data }) => {
          let msg = "탈퇴 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "탈퇴가 완료되었습니다.";
          }
          alert(msg);
          this.$store.dispatch("logout");
          this.closeUserDialog();
        });
    },

  },

  setup(props, { emit }) {
    const store = useStore()
    const profileForm = ref(null)

    const state = reactive({
      form: {
        genre:"",
        email:"",
        name:"",
        userId:"",
        password:"",
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {

    })

    const handleClose = function () {
      emit('closeProfileDialog')
    }

    return { profileForm, state, handleClose }
  }
}
</script>
