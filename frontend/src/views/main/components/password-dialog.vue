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
      if (state.form.password == state.form.passwordConfirm && state.form.password.length >= 9 && state.form.password.length <= 16) {
        store.dispatch('root/passwordChange', {
          token: props.token,
        password : state.form.password,
        userId : props.userInfo.data.userId,
        })
        .then(function () {
          emit('closePasswordDialog')
          alert('비밀번호가 변경되었습니다.')
        })
        .catch(function (err) {
          alert(err)
        })
      }
      else {
        alert('비밀번호를 다시 확인해주세요')
      }
    }

    const handleClose = function () {
      emit('closePasswordDialog')
    }

    return { passwordForm, state, passwordChange, handleClose }
  }
}
</script>
