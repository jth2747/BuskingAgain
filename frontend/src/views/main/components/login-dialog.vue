<template>
  <el-dialog custom-class="login-dialog" title="로그인" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
      <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.id" autocomplete="off"></el-input>
        <span v-if="state.form.id.length > 16">최대 16자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        <span v-if="state.form.password.length === 0"><el-button>로그인</el-button></span>
        <span v-else-if="state.form.password.length < 9">최소 9글자를 입력해야 합니다.<el-button >로그인</el-button></span>
        <span v-else-if="state.form.password.length > 16">최대 16자까지 입력 가능합니다.<el-button >로그인</el-button></span>
        <span v-else>
          <div v-if="state.form.id.length < 17">
            <div v-if="state.form.id.length > 0">
              <!-- <el-button type="primary" @click="[clickLogin(), lodingStart()]">로그인</el-button> -->
              <el-button type="primary" @click="clickLogin">로그인</el-button>
            </div>
            <div v-else>
              <el-button>로그인</el-button>
            </div>
          </div>
          <div v-else>
            <el-button>로그인</el-button>
          </div>
        </span>



      </el-form-item>
    </el-form>
    <template #footer>
      <!-- <ring-loader v-if="isLoding" :loading="loading" :color="color1" :size="size"></ring-loader> -->
    </template>
  </el-dialog>
</template>
<style>
.login-dialog {
  width: 400px !important;
  height: 300px;
}
.login-dialog .el-dialog__headerbtn {
  float: right;
}
.login-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.login-dialog .el-form-item {
  margin-bottom: 20px;
}
.login-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.login-dialog .el-input__suffix {
  display: none;
}
.login-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.login-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import RingLoader from 'vue-spinner/src/RingLoader.vue'
import swal from 'sweetalert'


export default {
  name: 'login-dialog',
  components: {
    RingLoader,
  },
  props: {
    open: {
      type: Boolean,
      default: false
    },
    isLoding: {
      type: Boolean,
      default: false
    }
  },
  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const loginForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        id: '',
        password: '',
        align: 'left'
      },
      rules: {
        id: [
          { required: true, message: 'Please input ID', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestLogin', { id: state.form.id, password: state.form.password })
          .then(function (result) {
            swal("Succes!", "로그인 성공", "success")
            localStorage.setItem('jwt', result.data.accessToken)
            location.reload()
          })
          .catch(function (err) {
            swal('Fail', '아이디와 비밀번호를 확인해주세요', 'error')
          })
        }
      });
    }

    const handleClose = function () {
      state.form.id = ''
      state.form.password = ''
      emit('closeLoginDialog')
    }

    const lodingStart = function () {
      emit('start-loding')
    }

    return { loginForm, state, clickLogin, handleClose, lodingStart}
  }
}
</script>
