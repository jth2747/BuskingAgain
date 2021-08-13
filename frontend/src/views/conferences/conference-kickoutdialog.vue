<template>
  <el-dialog custom-class="conference-update" title="버스킹 정보 수정" v-model="state.dialogVisible" @close="handleClose">
    <!-- <el-form :model="state.form" :rules="state.rules" ref="roomUpdateForm" :label-position="state.form.align">
      <el-form-item prop="title" label="방 제목" :label-width="state.formLabelWidth" >
        <el-input v-model="title" autocomplete="off"></el-input>
        <span v-if="title.length == 0"></span>
        <span v-else-if="title.length < 2">최소 2글자 이상 입력하시오.</span>
      </el-form-item>
      <el-form-item prop="genre" label="장르" :label-width="state.formLabelWidth" >
        <el-input v-model="genre" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="max_viewers" label="최대 인원 수" :label-width="state.formLabelWidth" >
        <el-input-number v-model="max_viewers" controls-position="right" :min="1" :max="20"></el-input-number>
      </el-form-item>
      <el-form-item prop="description" label="상세 설명" :label-width="state.formLabelWidth" >
        <el-input v-model="description" autocomplete="off"></el-input>
        <span v-if="description.length > 100">최대 100자까지 입력 가능합니다.</span>
      </el-form-item>
      <el-form-item prop="thumbnail_url" label="썸네일 사진" :label-width="state.formLabelWidth" >
        <input type="file" @change="imgUpload">
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="roomUpdate">방 정보 수정</el-button>
      </span>
    </template> -->
  </el-dialog>
</template>
<style>
.conference-update {
  width: 400px !important;
  height: 580px;
}
.conference-update .el-dialog__headerbtn {
  float: right;
}
.conference-update .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.conference-update .el-form-item {
  margin-bottom: 20px;
}
.conference-update .el-form-item__error {
  font-size: 12px;
  color: red;
}
.conference-update .el-input__suffix {
  display: none;
}
.conference-update .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.conference-update .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'conference-update',

  props: {
    open: {
      type: Boolean,
      default: false
    },
    title: {
      type: String
    },
    description: {
      type: String
    },
    thumbnail_url: {
      type: String
    },
    genre: {
      type: String
    },
    max_viewers: {
      type: Number
    },
    token: {
      type: String
    },
    id: {
      type: String
    },
  },

  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const roomUpdateForm = ref(null)

    const state = reactive({
      form: {
        title: props.title,
        genre: props.genre,
        max_viewers: props.max_viewers,
        description: props.description,
        thumbnail_url: '',
        max_viewers: props.max_viewers,
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      state.form.title = props.title
      state.form.genre = props.genre
      state.form.description = props.description
      state.form.thumbnail_url = props.thumbnail_url
    })


    // 버스킹 정보 수정
    const roomUpdate = function () {
      if(state.form.thumbnail_url){
        store.dispatch('root/updateRoom', {
          token: props.token,
          title: props.title,
          genre: props.genre,
          max_viewers: props.max_viewers,
          description: props.description,
          thumbnail_url: state.form.thumbnail_url,
          max_viewers: props.max_viewers,
          id: props.id,
        })
        .then(function (result) {
          alert('수정 완료')
          console.log(result)
          handleClose()
        })
      } else{
        store.dispatch('root/updateRoom', {
          token: props.token,
          title: props.title,
          genre: props.genre,
          max_viewers: props.max_viewers,
          description: props.description,
          thumbnail_url: props.thumbnail_url,
          max_viewers: props.max_viewers,
          id: props.id,
        })
        .then(function (result) {
          alert('수정 완료')
          console.log(result)
          location.reload()
        })
      }
    }

    const handleClose = function () {
      state.form.title = props.title
      state.form.genre = props.genre
      state.form.max_viewers = props.max_viewers
      state.form.description = props.description
      // state.form.thumbnail_url = props.thumbnail_url
      state.form.max_viewers = props.max_viewers
      emit('closeRoomEdit')
    }

    const imgUpload = function (event) {
      let file = event.target.files
      console.log('파일', file[0])
      let imgUrl = URL.createObjectURL(file[0])
      console.log(imgUrl)
      state.form.thumbnail_url = imgUrl
    }

    return { roomUpdateForm, state, roomUpdate, handleClose, imgUpload }
  }
}
</script>
