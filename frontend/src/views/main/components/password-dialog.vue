<template>
  <el-dialog custom-class="passowrd-dialog" title="비밀번호 변경" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        <span v-if="state.form.password.length === 0"></span>
        <span v-else-if="state.form.password.length < 9">최소 9글자를 입력해야 합니다.</span>
        <span v-else-if="state.form.password.length > 16">최대 16자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="passwordConfirm" label="비밀번호 확인" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.passwordConfirm" autocomplete="off" show-password></el-input>
        <span v-if="state.form.passwordConfirm.length === state.form.password">입력한 비밀번호와 일치하지 않습니다.</span>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="passwordChange">비밀번호 변경</el-button>
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

import { mapGetters } from "vuex";
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import swal from 'sweetalert'

export default {
  name: 'password-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    },
    token: {
      type: String
    },
    userInfo: {
      type: Object
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    const passwordForm = ref(null)

    const state = reactive({
      form: {
        password: '',
        passwordConfirm: '',
        align: 'left',
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const passwordChange = function() {
      if (validpwd()) {
        store.dispatch('root/passwordChange', {
          token: props.token,
        password : state.form.password,
        userId : props.userInfo.data.userId,
        })
        .then(function () {
          emit('closePasswordDialog')
          swal("Succes!", "비밀번호가 변경되었습니다.", "success")
        })
        .catch(function (err) {
          swal("Fail!", "Error", "error")
        })
      }
      else {
        swal("Fail!", "비밀번호를 다시 확인해주세요.", "error")
      }
    }

    const validpwd = function (){
      var pw = state.form.password;
      var pwck = state.form.passwordConfirm;
      var num = pw.search(/[0-9]/g);
      var eng = pw.search(/[a-z]/ig);
      var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

      if(pw.length < 9 || pw.length > 16){
        swal("Fail!", "9자리 ~ 16자리 이내로 입력해주세요.", "error")
        return false;
        }
      if(pw.search(/₩s/) != -1){
        swal("Fail!", "비밀전호는 공백없이 입력해주세요.", "error")
        return false;
        }
      if(num < 0 || eng < 0 || spe < 0 ){
        swal("Fail!", "영문, 숫자, 특수문자를 혼합하여 입력해주세요.", "error")
        return false;
        }
      if(pw != pwck){
        swal("Fail!", "비밀번호가 일치하기 않습니다.", "error")
        return false;
      }
      return true;
    }

    const handleClose = function () {
      emit('closePasswordDialog')
    }

    return { passwordForm, state, passwordChange, validpwd, handleClose }
  }
}
</script>
