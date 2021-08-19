<template>
  <el-dialog custom-class="busking-dialog" title="버스킹 생성" v-model="state.dialogVisible" @close="handleClose">
    <el-form :model="state.form" :rules="state.rules" ref="buskingForm" :label-position="state.form.align">
      <el-form-item prop="title" label="방 제목" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.title" autocomplete="off"></el-input>
        <span v-if="state.form.title.length == 0"></span>
        <span v-else-if="state.form.title.length < 2">최소 2글자 이상 입력하시오.</span>
      </el-form-item>
      <el-form-item prop="genre" label="장르" :label-width="state.formLabelWidth" >
      <el-select v-model="state.form.genre" placeholder="장르 선택" autocomplete="off">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      </el-form-item>
      <el-form-item prop="max_viewers" label="입장 가능 인원" :label-width="state.formLabelWidth" >
        <!-- <el-input v-model="state.form.max_viewers" autocomplete="off"></el-input> -->
        <el-input-number v-model="state.form.max_viewers" controls-position="right" @change="handleChange" :min="1" :max="20"></el-input-number>
      </el-form-item>
      <el-form-item prop="description" label="상세 설명" :label-width="state.formLabelWidth" >
        <el-input v-model="state.form.description" autocomplete="off"></el-input>
        <span v-if="state.form.description.length > 100">최대 100자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="thumbnail_url" label="썸네일 사진" :label-width="state.formLabelWidth" >
        <!-- <el-input v-model="state.form.thumbnail_url" autocomplete="off"></el-input> -->
        <input type="file" @change="imgUpload">

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
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
  name: 'busking-dialog',

  data() {
      return {
        options: [{
          value: '발라드',
          label: '발라드'
        }, {
          value: '어쿠스틱',
          label: '어쿠스틱'
        }, {
          value: '힙합',
          label: '힙합'
        }, {
          value: 'K-POP',
          label: 'K-POP'
        }, {
          value: '마술',
          label: '마술'
        }, {
          value: '댄스',
          label: '댄스'
        }, {
          value: '클래식',
          label: '클래식'
        }, {
          value: '재즈',
          label: '재즈'
        }, {
          value: '블루스',
          label: '블루스'
        }, {
          value: '컨트리',
          label: '컨트리'
        }, {
          value: '포크',
          label: '포크'
        }, {
          value: '레게',
          label: '레게'
        }, {
          value: '디스코',
          label: '디스코'
        }, {
          value: '록',
          label: '록'
        }, {
          value: 'EDM',
          label: 'EDM'
        }, {
          value: '트로트',
          label: '트로트'
        }],
        value: ''
      }
    },

  props: {
    open: {
      type: Boolean,
      default: false
    },
    token: {
      type: String,
    },
  },

  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()

    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const buskingForm = ref(null)

    const state = reactive({
      form: {
        title:'',
        genre:'',
        max_viewers: 1,
        description:'',
        thumbnail_url:'',
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
    })

    const clickCreate = function () {
      store.dispatch('root/requestBusking', {
        token: props.token,
        title: state.form.title,
        max_viewers: state.form.max_viewers,
        genre: state.form.genre,
        description: state.form.description,
        thumbnail_url: state.form.thumbnail_url,
      })
      .then(function (result) {
        alert('버스킹 생성 성공')
        router.push({
        name: 'conference-detail',
        params: {
          conferenceId: result.data.id,
          },
        })
        handleClose()
      })
      .catch(function (err) {
        alert(err)
      })

    }

    const imgUpload = function (event) {
      let file = event.target.files
      let imgUrl = URL.createObjectURL(file[0])
      state.form.thumbnail_url = imgUrl
    }


    const handleClose = function () {
      state.form.title = ''
      state.form.genre = ''
      state.form.max_viewers = 0
      state.form.description = ''
      state.form.thumbnail_url = ''
      emit('closeBuskingDialog')
    }

    const handleChange = function (value) {
    }

    return { buskingForm, state, handleClose, clickCreate, imgUpload, handleChange }
  }
}
</script>
