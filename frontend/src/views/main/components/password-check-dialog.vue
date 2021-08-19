<template>
  <el-dialog custom-class="password-check-dialog" title="회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 확인 합니다." v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="passwordCheck">비밀번호 확인</el-button>
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
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import swal from 'sweetalert'
// import func from 'vue-editor-bridge'

export default {
  name: 'password-check-dialog',
  props: {
    open: {
      type: Boolean,
      default: false
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
        password: '',
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    const passwordCheck = function() {
      store.dispatch('root/checkPassword', {
        token: props.token,
        password: state.form.password
      })
      .then(function () {
        emit('closePasswordCheckDialog')
        swal("Succes!", "확인이 완료되었습니다.", "success")
        store.dispatch('root/getUser', { token: props.token })
        .then(function (result) {
          emit('openProfileDialog', result)
        })
        .catch(function (err){
          swal("Fail!", "Error", "error")
        })
      })
      .catch(function () {
        swal("Fail!", "잘못된 비밀번호 입니다.", "error")
      })
    }

    onMounted(() => {
    })

    const handleClose = function () {
      emit('closePasswordCheckDialog')
      location.reload()
    }

    return { profileForm, state, passwordCheck, handleClose }
  }
}
</script>
