<template>
  <el-dialog custom-class="delete-confirm-dialog" title="'회원 탈퇴'를 입력하고 탈퇴버튼을 누르면 탈퇴가 진행됩니다." v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.confirm"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="clickDelete">회원 탈퇴</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.busking-dialog {
  width: 400px !important;
  height: 580px;
}
.busking-dialog .el-dialog__headerbtn {
  float: right;
}
.busking-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.busking-dialog .el-form-item {
  margin-bottom: 20px;
}
.busking-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.busking-dialog .el-input__suffix {
  display: none;
}
.busking-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.busking-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'busking-dialog',

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
    const router = useRouter()

    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const buskingForm = ref(null)

    const state = reactive({
      form: {
        confirm: ''
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const clickDelete = function() {
      if(state.form.confirm === '회원 탈퇴') {
        store.dispatch('root/deleteUser', { token: props.token, userId: props.userInfo.data.userId })
        .then(function (result) {
          emit('click-logout')
          localStorage.removeItem('jwt')
          location.reload()
          alert('탈퇴가 완료되었습니다.')
        })
        .catch(function (err){
          alert(err)
        })
      } else {
        alert('입력을 확인 해주세요')
      }
    }

    const handleClose = function () {
      state.form.confirm = ''
      emit('closeDeleteConfirmDialog')
    }

    return { buskingForm, state, handleClose, clickDelete}
  }
}
</script>
