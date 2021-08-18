<template>
<div v-if="state.form.flag" class="coin"/>
<div style="visibility:hidden" v-else class="coin"/>
<div style="cursor:pointer" @click="clickHat()" class="hat"/>

  <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">

    <el-container style="margin: 20px">
      <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="2"><div class="like"></div></el-col>
      <el-col :span="4"><div class="ranktitle" style="margin: 20px">좋아요 TOP 5</div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
    </el-container>

      <el-carousel :interval="4000" type="card" height="330px">
        <el-carousel-item v-for="(room, i) in state.form.roomData_1[0]" @click="clickConference(state.form.roomData_1[0][i]['id'])" class="infinite-list-item medium" :key="i">
             <div v-if="i===0">
              <div class="rank-logo1"/>
            </div>
            <div v-else-if="i===1">
              <div class="rank-logo2"/>
            </div>
            <div v-else-if="i===2">
              <div class="rank-logo3"/>
            </div>
            <div v-else-if="i===3">
              <div class="rank-logo4"/>
            </div>
            <div v-else-if="i===4">
              <div class="rank-logo5"/>
            </div>

            <conference
              :image="room['thumbnail_url']"
              :title="room['title']"
              :desc="room['description']"
              :genre="room['busking_genre']"
            />
        </el-carousel-item>
      </el-carousel>
    <div></div>
    <!-- <el-container style="margin: 20px">
      <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="1"><div class="like"></div></el-col>
      <el-col :span="5"><div class="ranktitle" style="margin: 20px">좋아요 TOP 5</div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
    </el-container> -->
    <el-container style="margin: 20px">
      <el-col :span="10"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="2"><div class="viewer"></div></el-col>
      <el-col :span="4"><div class="ranktitle" style="margin: 20px">시청자 TOP 5</div></el-col>
      <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
    </el-container>

      <span> </span>
      <span> </span>
      <el-carousel :interval="4000" type="card" height="330px">
        <el-carousel-item v-for="(room, i) in state.form.roomData_2[0]" @click="clickConference(state.form.roomData_2[0][i]['id'])" class="infinite-list-item medium" :key="i">
             <div v-if="i===0">
              <div class="rank-logo1"/>
            </div>
            <div v-else-if="i===1">
              <div class="rank-logo2"/>
            </div>
            <div v-else-if="i===2">
              <div class="rank-logo3"/>
            </div>
            <div v-else-if="i===3">
              <div class="rank-logo4"/>
            </div>
            <div v-else-if="i===4">
              <div class="rank-logo5"/>
            </div>

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
.like {
  background-size: contain;
  background-image: url('../../assets/images/like.png');
  width: 82px;
  height: 82px;
}
.viewer {
  background-size: contain;
  background-image: url('../../assets/images/viewer.png');
  width: 82px;
  height: 82px;
}
.ranktitle{
  text-align: left;
  font-size: 30px;
  color:white;
}
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
  height: 360px;
  display: inline-block;
  cursor: pointer;
}

.rank-logo1 {
  width: 70px;
  height: 50px;
  margin: auto;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/1등.png');
}

.rank-logo2 {
  width: 70px;
  height: 50px;
  margin:auto;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/2등.png');
}

.rank-logo3 {
  width: 70px;
  height: 50px;
  margin:auto;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/3등.png');
}

.rank-logo4 {
  width: 70px;
  height: 50px;
  margin:auto;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/4등.png');
}

.rank-logo5 {
  width: 70px;
  height: 50px;
  margin:auto;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/5등.png');
}


/* ----------------------------------------------
 * Generated by Animista on 2021-8-11 15:6:52
 * Licensed under FreeBSD License.
 * See http://animista.net/license for more info.
 * w: http://animista.net, t: @cssanimista
 * ---------------------------------------------- */

/**
 * ----------------------------------------
 * animation bounce-in-top
 * ----------------------------------------
 */
@keyframes bounce-in-top {
  0% {
    -webkit-transform: translateY(-500px);
            transform: translateY(-500px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
    opacity: 0;
  }
  38% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
    opacity: 1;
  }
  55% {
    -webkit-transform: translateY(-65px);
            transform: translateY(-65px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  72% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  81% {
    -webkit-transform: translateY(-28px);
            transform: translateY(-28px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  90% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
  95% {
    -webkit-transform: translateY(-8px);
            transform: translateY(-8px);
    -webkit-animation-timing-function: ease-in;
            animation-timing-function: ease-in;
  }
  100% {
    -webkit-transform: translateY(0);
            transform: translateY(0);
    -webkit-animation-timing-function: ease-out;
            animation-timing-function: ease-out;
  }
}
.coin {
  left: 10px;
  position: relative;
  z-index: 1;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/coin.png');
  border-radius: 50%;
  animation: bounce-in-top 1.1s ease 1;
  width: 100px;
  height: 100px;
}
.hat {
  top:-100px;
  z-index: 2;
  position: relative;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/hat.png');
  border-radius: 50%;
  width: 120px;
  height: 120px;
}

</style>
<script>
import Conference from '../home/components/conference'
import { computed, onMounted, reactive } from 'vue'
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
        flag: false
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

    const clickHat = function(){
      console.log("click hat");
      state.form.flag=true;
    }

    const loginDemended = function () {
      alert('버스킹에 입장하려면 로그인을 먼저 해주세요')
    }

    return { state, load, clickConference, loginDemended, clickHat}
  }
}
</script>
