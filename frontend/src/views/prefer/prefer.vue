<template>
<div>
  <!-- <div class="button-wrapper">
  <el-button @click="clickBusking" v-if="this.token">버스킹 생성</el-button>
  </div> -->
  <div class="search-field">
    <div class="search-container">
      <input class ="search-input" style="float:left"  @keyup.enter="submit" placeholder="버스킹 제목 검색" v-model="state.searchValue">
      <span class="icon"><i class="el-icon-search" style="color: #ffffff; font-size: 20px; font-weight: bold; margin-top:-28px; margin-left:5px; float:left"></i></span>
    </div>
  </div>
  <!-- <div class="search-field">
    <el-input @keyup.enter="submit"
      placeholder="버스킹 제목 검색"
      prefix-icon="el-icon-search"
      v-model="state.searchValue">
    </el-input>
  </div> -->
</div>
  <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
      <el-carousel :interval="4000" type="card" height="400px" style="margin-top:10px">
        <el-carousel-item v-for="(room, i) in state.form.roomData[0]" @click="clickConference(state.form.roomData[0][i]['id'])" class="infinite-list-item medium" :key="i">
            <conference
              :image="room['thumbnail_url']"
              :title="room['title']"
              :desc="room['description']"
              :genre="room['busking_genre']"
            />
        </el-carousel-item>
      </el-carousel>
  </ul>

  <busking-dialog
    :open="buskingDialogOpen"
    :token="token"
    @closeBuskingDialog="onCloseBuskingDialog"/>


</template>
<style>
.button-wrapper {
    width: 30%;
    float: right;
    margin: 5px;
  }
.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}

@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
</style>
<script>
import Conference from '../home/components/conference'
import { onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import BuskingDialog from '../main/components/busking-dialog.vue'
import { useStore } from 'vuex'



export default {
  name: 'Prefer',

  components: {
    BuskingDialog,
    Conference,
  },

  data() {
    return {
      buskingDialogOpen:false,
      token: localStorage.getItem('jwt'), // jwt 토큰
      image: 'https://www.ssafy.com/swp/images/sns_img.png',
      value: '',
    }
  },

  methods:{
    clickBusking(){
      console.log("inin");
      this.buskingDialogOpen=true
    },
    onCloseBuskingDialog(){
      this.buskingDialogOpen=false
    },
  },

  setup () {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      form: {
        token: localStorage.getItem('jwt'),
        roomData: [],
      },
      count: 12
    })

    const load = function () {
      state.count += 4
    }

    onMounted(() => {
      store.dispatch('root/roomGenreList',{
        token: state.form.token,
      })
      .then(function (result) {
        console.log(result.data)
        // state.form.roomData = result.data
        state.form.roomData.push(result.data)
        console.log(state.form.roomData[0][0].title)
      })
    })

    const clickConference = function (id) {
      router.push({
        name: 'conference-detail',
        params: {
          conferenceId: id,
        },
      })
    }

    const loginDemended = function () {
      alert('버스킹에 입장하려면 로그인을 먼저 해주세요')
    }

    const submit = function() {
      console.log("엔터입력")
      console.log(state.searchValue)
      store.dispatch('root/findRoomList',{
        title: state.searchValue
      })
      .then(function(result){
        console.log(result.data)
        state.form.roomData = []
        state.form.roomData.push(result.data)
        // console.log(state.form.roomData[0][0].title)
      })
    }

    return { state, load, clickConference, loginDemended, submit}
  }
}
</script>
