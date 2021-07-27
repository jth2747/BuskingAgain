<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align">
      <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.email" autocomplete="off"></el-input>
        <span v-if="state.form.email.length > 30">최대 30자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="genre" label="선호하는 장르" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.genre" autocomplete="off"></el-input>
        <span v-if="state.form.genre.length > 30">최대 30자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="name" label="이름" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.name" autocomplete="off"></el-input>
        <span v-if="state.form.name.length > 30">최대 30자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="uid" label="아이디" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.uid" autocomplete="off"></el-input>
        <span v-if="state.form.uid.length > 16">최대 16자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="upwd" label="비밀번호" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.upwd" autocomplete="off" show-password></el-input>
        <span v-if="state.form.upwd.length < 9">최소 9글자를 입력해야 합니다.</span>
        <span v-else-if="state.form.upwd.length > 16">최대 16자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="upwd_check" label="비밀번호 확인" :label-width="state.formLabelWidth">
        <el-input v-model="state.form.upwd_check" autocomplete="off" show-password></el-input>
        <span v-if="state.form.upwd_check != state.form.upwd">입력한 비밀번호와 일치하지 않습니다.</span>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="clickSignup">회원가입</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.signup-dialog {
  width: 400px !important;
  height: 580px;
}
.signup-dialog .el-dialog__headerbtn {
  float: right;
}
.signup-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.signup-dialog .el-form-item {
  margin-bottom: 20px;
}
.signup-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.signup-dialog .el-input__suffix {
  display: none;
}
.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
//import axios from "axios"
import { reactive, computed, ref, onMounted, watch } from 'vue'
import { useStore } from 'vuex'

//const SERVER_URL = "http://localhost:8080";

export default {
  name: 'signup-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },
  // watch:{
  //   upwd: function(newValue, oldValue){
  //     if(newValue==1){
  //       console.log(upwd);
  //     }
  //   }
  // },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)

    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        email:'',
        genre:'',
        name:'',
        uid: '',
        upwd: '',
        upwd_check:'',
        align: 'left',
        date_array: [false, false, false, false, false, false],
        evalid: true,
      },
      rules: {
        email: [
          { required: true, message: 'Please input email', trigger: 'blur' }
        ],
        genre: [
          { required: true, message: 'Please input genre', trigger: 'blur' }
        ],
        name: [
          { required: true, message: 'Please input Name', trigger: 'blur' }
        ],
        uid: [
          { required: true, message: 'Please input ID', trigger: 'blur' }
        ],
        upwd: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ],
        upwd_check: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(signupForm.value)
    })

    const clickSignup = function () {
      // signupForm.value.validate((valid) => {
        if(validpwd() == false){
          console.log("비밀번호 유효성 안맞음")
          state.form.evalid = false;
        }else{
          console.log("비밀번호 유효성 "+ validpwd());
        }

        if(validemail() == false){
          console.log("이메일 유효성 안맞음")
          state.form.evalid = false;
        }else{
          console.log("이메일 유효성 "+validemail());
        };

        if(validid() == false){
          console.log("아이디 유효성 안맞음")
          state.form.evalid = false;
        }else{
          console.log("아이디 유효성 "+validid());
        }

        if(validinput() == false){
          console.log("장르랑 이름 null")
          state.form.evalid = false;
        }else{
          console.log("장르랑 이름 유효성" + validinput());
        }

        if(validinput() && validpwd() && validemail() && validid())
          state.form.evalid = true;

        if (state.form.evalid==true) {
          console.log(validpwd())
          console.log(state.form.evalid)
          console.log('submit')
          store.dispatch('root/requestSignup',
          {
            email: state.form.email,
            genre: state.form.genre,
            name: state.form.name,
            id: state.form.uid,
            password: state.form.upwd })
          .then(function (
          ) {
            alert('회원가입 성공')
            location.reload()
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      //  });
    }

    const validpwd = function (){
      var pw = state.form.upwd;
      var pwck = state.form.upwd_check;
      var num = pw.search(/[0-9]/g);
      var eng = pw.search(/[a-z]/ig);
      var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

      if(pw.length < 9 || pw.length > 16){
        alert("9자리 ~ 16자리 이내로 입력해주세요.");
        return false;
        }
      if(pw.search(/₩s/) != -1){
        alert("비밀번호는 공백없이 입력해주세요.");
        return false;
        }
      if(num < 0 || eng < 0 || spe < 0 ){
        alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
        return false;
        }
      if(pw != pwck){
        alert("비밀번호가 일치하지 않습니다.");
        return false;
      }
      return true;
    }

    const validemail = function(){
      var email = state.form.email;
      var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    }

    const validid = function(){
      var id = state.form.uid;
      console.log(id);
      if(id.length > 16 || id == "")
        return false;
      return true;
    }
    const validinput = function(){
      var name = state.form.name;
      var genre = state.form.genre;
      if(name == "" || genre == ""){
        console.log(name+" "+genre);
        return false;
      }
      return true;
    }


    const handleClose = function () {
      state.form.email = ''
      state.form.genre = ''
      state.form.name = ''
      state.form.uid = ''
      state.form.upwd = ''
      state.form.upwd_check = ''
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, handleClose, validpwd, validemail, validid, validinput }
  }
}
</script>
