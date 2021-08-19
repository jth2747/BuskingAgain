<template>
  <el-dialog custom-class="delete-confirm-dialog" title="비밀번호 찾기" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="ID" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.id"></el-input>
      </el-form-item>
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="이름" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.name"></el-input>
      </el-form-item>
    </el-form>
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="email" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.email"></el-input>
      </el-form-item>
    </el-form>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button type="danger" @click="clickFindPassword">비밀번호 찾기</el-button>
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
  name: 'id-find-dialog',
  components: {
  },
  props: {
    open: {
      type: Boolean,
      default: false
    },
  },

  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()

    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const buskingForm = ref(null)

    const state = reactive({
      form: {
        name: '',
        email: '',
        id: '',
        // align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log('buskingForm.value')
    })



    const onOpenPasswordDialog = function() {
      emit('openPasswordFindDialog')
    }


    const clickFindPassword = function() {
      store.dispatch('root/findPassword', {
        name: state.form.name,
        email: state.form.email,
        userId: state.form.id,
      })
      .then(function (result) {
        console.log('result',result.data)
        alert(`${result.data}`)
        location.reload()
      })
      .catch(function (result) {
        alert('가입된 회원이 확인되지 않습니다.')
      })
    }

    const handleClose = function () {
      state.form.name = ''
      state.form.email = ''
      state.form.id = ''
      emit('closePasswordDialog')
    }

    return { buskingForm, state, handleClose, onOpenPasswordDialog, clickFindPassword }
  }
}
</script>
