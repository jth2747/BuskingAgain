<template>
  <el-dialog custom-class="user-dialog" title="회원정보" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="department" label="소속" :label-width="state.formLabelWidth" >
        <el-form-item v-model="user.department" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="position" label="직책" :label-width="state.formLabelWidth" >
        <el-form-item v-model="user.position" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth" >
        <el-form-item v-model="user.name" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="uid" label="아이디" :label-width="state.formLabelWidth" >
        <el-form-item v-model="user.uid" autocomplete="off"></el-form-item>
      </el-form-item>
      <el-form-item prop="upwd" label="비밀번호" :label-width="state.formLabelWidth">
        <el-form-item v-model="user.upwd" autocomplete="off" show-password></el-form-item>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="warning"  @click="clickSignup">정보수정</el-button>
        <el-button type="danger" @click="clickSignup">회원탈퇴</el-button>
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
export default {
  data() {
    return {
      update: false,
    };
  },
  computed: {
    ...mapGetters(["userDialog", "getAccessToken", "user", "join"]),
  },
  mounted() {
    if (this.join) {
      this.$refs.joinform.reset();
    }
  },
  methods: {
    closeUserDialog() {
      this.$refs.joinform.reset();
      this.$store.commit("changeUserDialog");
    },
    closeJoinDialog() {
      this.$refs.joinform.reset();
      this.$store.commit("JOIN");
    },
    updateSetting() {
      this.update = true;
    },
    updateUser() {
      console.log(this.user + " " + this.getAccessToken);
      axios.defaults.headers.common["auth-token"] = this.getAccessToken;
      axios
        .put(`${SERVER_URL}/user/update`, {
          department: this.user.department,
          position: this.user.position,
          name: this.user.name,
          id: this.user.id,
          pwd: this.user.pwd,
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
        .delete(`${SERVER_URL}/user/delete/${this.user.id}`)
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
};
</script>
