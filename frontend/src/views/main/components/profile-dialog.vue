<template>
  <el-dialog custom-class="profile-dialog" title="회원정보" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="profileForm" :label-position="state.form.align">
      <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.email" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.name"></el-input>
      </el-form-item>
      <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.userId" :disabled="true"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success"  @click="passwordChange">비밀번호 변경</el-button>
        <el-button type="warning" icon="el-icon-edit" @click="updateUser">정보수정</el-button>
        <el-button type="danger" icon="el-icon-delete" @click="deleteConfirm">회원탈퇴</el-button>
        <!-- <el-button type="danger" icon="el-icon-delete" @click="deleteUser">회원탈퇴</el-button> -->
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
// import axios from "axios";
// const SERVER_URL = "http://localhost:8080";
// import { mapGetters } from "vuex";
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import swal from 'sweetalert'

export default {
  name: 'profile-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    },
    userInfo: {
      type: Object,
    },
    token: {
      type: String
    },
  },


  setup(props, { emit }) {
    const store = useStore()
    const profileForm = ref(null)

    const state = reactive({
      form: {
        //genre: props.userInfo.data.genre,
        email: props.userInfo.data.email,
        name: props.userInfo.data.name,
        userId: props.userInfo.data.userId,
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const updateUser = function() {
      if(vaildinput()){
        store.dispatch('root/updateUser', {
          token: props.token,
          email: state.form.email,
          name: state.form.name,
          userId: state.form.userId,
        })
        .then(function () {
          location.reload()
          swal("Succes!", "회원정보가 수정되었습니다.", "success")
        })
        .catch(function (err) {
          swal("Fail!", `${err}`, "error")
        })
      }
      else{
        swal("Fail!", "입력을 확인해주세요", "error")
      }
    }

    const vaildinput = function(){
      var name = state.form.name;
      //var genre = state.form.genre;

      if(name == "" ){
        return false;
      }
      return true;
    }
    const passwordChange = function() {
      emit('openPasswordDialog')
    }

    const deleteConfirm = function() {
      emit('openDeleteConfirmDialog')
    }

    const handleClose = function () {
      state.form.email = props.userInfo.data.email
      state.form.name = props.userInfo.data.name
      state.form.userId = props.userInfo.data.userId
      emit('closeProfileDialog')
      location.reload()
    }

    return { profileForm, state, updateUser, vaildinput, passwordChange, handleClose, deleteConfirm }
  }
}
</script>
