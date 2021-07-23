<template>
  <el-dialog custom-class="profile-dialog" title="회원정보" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="genre" label="장르" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.genre"></el-input>
      </el-form-item>
      <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.email" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.name"></el-input>
      </el-form-item>
      <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.userId" :disabled="true"></el-input>
      </el-form-item>
      <!-- <el-form-item prop="password" label="비밀번호 수정" :label-width="state.formLabelWidth">
        <el-input placeholder="Please input password" v-model="state.form.password" show-password></el-input>
        <span v-if="state.form.password.length === 0"></span>
        <span v-else-if="state.form.password.length < 9">최소 9글자를 입력해야 합니다.</span>
        <span v-if="state.form.password.length > 16">최대 16자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="password" label="비밀번호 확인" :label-width="state.formLabelWidth">
        <el-input placeholder="Please input password" v-model="state.form.passwordcheck" show-password></el-input>
        <span v-if="state.form.password != state.form.passwordcheck">입력한 비밀번호와 일치하지 않습니다.</span>
      </el-form-item> -->
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success"  @click="updateUser">비밀번호 변경</el-button>
        <el-button type="warning"  @click="updateUser">정보수정</el-button>
        <el-button type="danger" @click="deleteUser">회원탈퇴</el-button>
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
    },
    userInfo: {
      type: Object,
    },
    token: {
      type: String
    },
  },

  data () {
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

  },

  setup(props, { emit }) {
    const store = useStore()
    const profileForm = ref(null)

    const state = reactive({
      form: {
        genre: props.userInfo.data.genre,
        email: props.userInfo.data.email,
        name: props.userInfo.data.name,
        userId: props.userInfo.data.userId,
        password: '',
        passwordcheck: '',
        align: 'left'
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const updateUser = function() {
      // if (state.form.password == state.form.passwordcheck && state.form.password.length >= 9 && state.form.password.length <= 16) {
      if (state.form.password) {
        store.dispatch('root/updateUser', {
          token: props.token,
        genre: state.form.genre,
        email: state.form.email,
        name: state.form.name,
        userId: state.form.userId,
        password: state.form.password,
      })
      .then(function () {
        location.reload()
        alert('회원정보가 수정되었습니다.')
      })
      .catch(function (err) {
        alert(err)
      })
    }
      else {
        store.dispatch('root/updateUser', {
          token: props.token,
          genre: state.form.genre,
          email: state.form.email,
          name: state.form.name,
          userId: state.form.userId,
          password: props.userInfo.data.password,
        })
        .then(function () {
          location.reload()
          alert('회원정보가 수정되었습니다.')
        })
        .catch(function (err) {
          alert(err)
        })
      }
    }
      // else {
      //   alert('비밀번호를 확인해 주세요')
      // }

    const deleteUser = function() {
      store.dispatch('root/deleteUser', { token: props.token, userId: props.userInfo.data.userId })
      .then(function (result) {
        console.log(result.date)
        emit('click-logout')
        location.reload()
        alert('탈퇴가 완료되었습니다.')
      })
      .catch(function (err){
        alert(err)
      })
    }

    const handleClose = function () {
      state.form.password = ''
      state.form.passwordcheck = ''
      state.form.genre = props.userInfo.data.genre
      state.form.email = props.userInfo.data.email
      state.form.name = props.userInfo.data.name
      state.form.userId = props.userInfo.data.userId
      emit('closeProfileDialog')
    }

    return { profileForm, state, updateUser, deleteUser, handleClose }
  }
}
</script>
