<template>
  <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
      <el-carousel :interval="4000" type="card" height="330px">
        <el-carousel-item v-for="(room, i) in state.form.roomData_1[0]" @click="clickConference(state.form.roomData_1[0][i]['id'])" class="infinite-list-item medium" :key="i">
            <conference
              :image="room['thumbnail_url']"
              :title="room['title']"
              :desc="room['description']"
              :genre="room['busking_genre']"
            />
        </el-carousel-item>
      </el-carousel>
      <el-carousel :interval="4000" type="card" height="330px">
        <el-carousel-item v-for="(room, i) in state.form.roomData_2[0]" @click="clickConference(state.form.roomData_2[0][i]['id'])" class="infinite-list-item medium" :key="i">
            <conference
              :image="room['thumbnail_url']"
              :title="room['title']"
              :desc="room['description']"
              :genre="room['busking_genre']"
            />
        </el-carousel-item>
      </el-carousel>
  </ul>

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
import { useStore } from 'vuex'



export default {
  name: 'Rank',

  components: {
    Conference,
  },

  data() {
    return {
      //token: localStorage.getItem('jwt'),
      image: 'https://www.ssafy.com/swp/images/sns_img.png',
      value: '',
    }
  },

  methods:{

  },

  setup () {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      form: {
        token: localStorage.getItem('jwt'),
        roomData_1: [],
        roomData_2: [],
      },
      count: 12
    })

    const load = function () {
      state.count += 4
    }

    onMounted(() => {
      store.dispatch('root/roomRankLike')
      .then(function (result) {
        console.log(result.data)
        state.form.roomData_1.push(result.data)
        console.log(state.form.roomData_1[0][0].title)
      })
      store.dispatch('root/roomRankPerson')
      .then(function (result) {
        console.log(result.data)
        state.form.roomData_2.push(result.data)
        console.log(state.form.roomData_2[0][0].title)
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

    return { state, load, clickConference, loginDemended}
  }
}
</script>
