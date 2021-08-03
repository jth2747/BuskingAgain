<template>
  <el-dialog custom-class="genre-dialog" title="선호 장르" v-model="state.dialogVisible" @close="handleClose">
      <div>
      <el-item style="margin: 10px">기존 선호 장르 리스트: {{state.form.genreList["genreList"]}}</el-item>
      </div>
      <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange" style="margin: 15px 10px">Check all</el-checkbox>
      <el-checkbox-group v-model="state.form.checkedGenre" @change="handleCheckedGenreChange">
      <el-checkbox style="margin: 5px 10px" v-for="gr in state.form.genre" :label="gr" :key="gr">{{gr}}</el-checkbox>
      </el-checkbox-group>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="clickCheckbox">선택 완료</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<style>
.genre-dialog {
  width: 400px !important;
  height: 580px;
}

.genre-dialog .el-dialog__headerbtn {
  float: right;
}
.genre-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.genre-dialog .el-form-item {
  margin-bottom: 20px;
}
.genre-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.genre-dialog .el-input__suffix {
  display: none;
}
.genre-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.genre-dialog .dialog-footer .el-button {
  width: 120px;
}
</style>
<script>
import { reactive, computed, ref, onMounted} from 'vue'
import { mapGetters, useStore } from 'vuex'

const genreOptions = ['발라드', '어쿠스틱', '힙합', 'K-POP', '마술', '댄스','클래식','재즈','블루스',
'컨트리','포크','레게','디스코','록','EDM','트로트'];

export default {
  name: 'genre-dialog',

  props: {
    open: {
      type: Boolean,
      default: false
    },
    token: {
      type: String
    },
    genreList:{
      type:Array
    }
  },

  computed:{
    ...mapGetters(["getAccessToken","getGenreList"])
  },


  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const genreForm = ref(null)
    store.dispatch('root/getGenre', { token: props.token })
        .then(function (result) {
          console.log(result.data)
          state.form.genreList=result.data
        })
        .catch(function (err){
          alert(err)
        })
    const state = reactive({
      form: {
        checkAll: false,
        checkedGenre: [],
        genreList:[],
        genre: genreOptions,
        isIndeterminate: true
      },
      rules: {
        genre: [
          { required: true, message: 'Please input genre', trigger: 'blur' }
        ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px'
    })

    onMounted(() => {

    })

    const handleCheckAllChange=function(val) {
        state.form.checkedGenre = val ? genreOptions : [];
        state.form.isIndeterminate = false;
        console.log(state.form.checkedGenre);
      }

    const handleCheckedGenreChange =function(value) {
        let checkedCount = value.length;
        state.form.checkAll = checkedCount === state.form.genre.length;
        state.form.isIndeterminate = checkedCount > 0 && checkedCount < state.form.genre.length;
        console.log(state.form.checkedGenre);
        //console.log(genreList);
    }


    const handleClose = function () {
      state.form.genre = ''
      emit('closeGenreDialog')
    }

    const clickCheckbox = function(){
      console.log(state.form.checkedGenre)
      store.dispatch('root/requestGenre',
          {
            token: props.token,
            genre: state.form.checkedGenre,
          })
          .then(function (
          ) {
            alert('선호 장르 성공')
            location.reload()
          })
          .catch(function (err) {
            alert(err)
          })
    }

    return { genreForm, state, handleClose, clickCheckbox, handleCheckedGenreChange, handleCheckAllChange }
  }
}
</script>
