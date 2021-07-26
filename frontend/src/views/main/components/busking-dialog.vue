<template>
  <el-dialog custom-class="busking-dialog" title="버스킹 생성" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="방 제목" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.title" autocomplete="off"></el-input>
        <span v-if="state.form.title.length == 0"></span>
        <span v-else-if="state.form.title.length < 2">최소 2글자 이상 입력하시오.</span>
      </el-form-item>
      <el-form-item prop="genre" label="장르" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.genre" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="description" label="상세 설명" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.description" autocomplete="off"></el-input>
        <span v-if="state.form.description.length > 100">최대 100자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="thumbnailurl" label="썸네일 사진" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.thumbnailurl" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="clickCreate">방 생성</el-button>
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
//import axios from "axios"
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

//const SERVER_URL = "http://localhost:8080";

export default {
  name: 'busking-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    }
  },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const buskingForm = ref(null)

    const state = reactive({
      form: {
        title:'',
        genre:'',
        description:'',
        thumbnailurl:'',
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(buskingForm.value)
    })

    const clickCreate = function () {
      buskingForm.value.validate((valid) => {
        if (valid) {
          console.log('submit')
          store.dispatch('root/requestBusking',
          {
            title: state.form.title,
            genre: state.form.genre,
            description: state.form.description,
            thumbnailurl: state.form.thumbnailurl,
          })
          .then(function () {
            alert('버스킹 생성 성공')
            location.reload()
          })
          .catch(function (err) {
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const handleClose = function () {
      state.form.title = ''
      state.form.genre = ''
      state.form.description = ''
      emit('closeBuskingDialog')
    }

    return { buskingForm, state, handleClose, clickCreate }
  }
}
</script>
