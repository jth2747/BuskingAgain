<template>
  <!-- <h2>{{ $route.params.conferenceId }}</h2> -->
  <el-container>
    <el-aside width="250px">
      <div style="height: 60px; background-color: rgb(0,0,0,0.9); color: #ffffff; font-size: 20px; font-weight: bold; text-align:center">
        <div style="height: 12px;"></div>
        채팅
      </div>
      <div id="socket">
        <el-scrollbar height="50pc">
          <div v-for="(item, idx) in state.form.recvList" :key="idx">
            <span> {{ item.userId }}</span>
            <span> : {{ item.message }}</span>
          </div>
          <div style="color:#ffffff">
          <textarea class="chattingInput"
            v-model="state.form.message"
            type="text"
            @keyup="sendMessage"
            placeholder=" 채팅을 입력해주세요"
          />
          </div>
        </el-scrollbar>
      </div>
    </el-aside>
    <el-container>
      <el-header >
        <span style="margin-left:20px; float:left; color:#ffffff; font-size:50px; font-weight:bold;">{{ state.form.title }}</span>
        <span style="margin-left:20px; margin-top:15px; float:left; color:#ffffff">  접속자 수 : {{ state.form.viewers}}</span>
        <span style="margin-left:5px; margin-top:15px; float:left; color:#ffffff">/  좋아요 : 0</span>
        <el-button-group v-if="state.form.owner" style="float: right">
          <span class="textbutton" style="margin:10px">
            <i class="el-icon-edit" @click="clickRoomEdit" style="cursor: pointer;  color:#ffffff">수정</i>
          </span>
          <span class="textbutton" style="margin:10px">
            <i class="el-icon-delete" @click="roomDelete" style="cursor: pointer;  color:#ffffff">종료</i>
          </span>
          <span class="textbutton" style="margin:10px">
            <i class="el-icon-warning"  @click="onOpenKickOutDialog" style="cursor: pointer;  color:#ffffff">강퇴</i>
          </span>
          <!-- <el-button type="warning" icon="el-icon-edit" @click="clickRoomEdit">수정</el-button>
          <el-button type="danger" icon="el-icon-delete" @click="roomDelete">방 종료</el-button>
          <el-button type="success" @click="onOpenKickOutDialog">강퇴</el-button> -->
          <conference-kickoutdialog
            :open="state.form.kickOutDialogOpen"
            :token="state.form.token"
            @closeKickOutDialog="onCloseKickOutDialog"
          />
        </el-button-group>
        <el-button-group v-else style="float: right">
          <span class="textbutton" style="margin:10px">
            <i class="el-icon-close" @click="goBackHome" style="cursor: pointer;  color:#ffffff">나가기</i>
          </span>
          <!-- <el-button type="danger" @click="goBackHome">나가기</el-button> -->
        </el-button-group>
        </el-header>
      <el-row>
        <!-- <el-col :span="24"> -->
          <el-main class="grid-content">
            <conference-main
             :conferenceId="$route.params.conferenceId"
             :onwer="state.form.onwerId"
             :userId="state.form.userId"
            />
          </el-main>
        <!-- </el-col> -->
      </el-row>
      <el-footer style="height: 120px;">
        <el-popover placement="bottom" title="좋아요" trigger="hover" auto-close=700	>
              <template #reference>
                <div style="float:left; margin-right: 10px; height:100px">
                  <div v-if="state.form.flag" class="coin"/>
                  <div style="visibility:hidden" v-else class="coin"/>
                  <div style="cursor:pointer" @click="clickHat()" class="hat"/>
                </div>
              </template>
        </el-popover>
        <!-- {{ state.form.ownerId }}님의 버스킹 -->
        <div style="float: left">
        <div style="height: 25px; text-align:left;"> 장르 : {{state.form.genre}}</div>
        <div style="height: 25px; text-align:left;"> 버스커 : {{state.form.userId}}</div>
        <div style="height: 25px; text-align:left;"> 버스킹 설명 : {{state.form.description}}</div>
        </div>
      </el-footer>
    </el-container>
  </el-container>
  <conference-update
    :open="state.form.roomEditDialogOpen"
    :title="state.form.title"
    :description="state.form.description"
    :thumbnail_url="state.form.thumbnail_url"
    :max_viewers="state.form.max_viewers"
    :genre="state.form.genre"
    :token="state.form.token"
    :id="$route.params.conferenceId"
    @closeRoomEdit="closeRoomEdit"
  />


</template>
<style scoped>
  #socket {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
    margin-top: 60px;
    margin-left: 10px;
  }
  .textbutton:hover{
    font-weight: bold;
  }
.chattingInput{
  /* margin-left: 10px; */
  width: 170pt;
  height: 50pt;
  border-radius: 10px;
  border: 1px solid;
  font-size: 15px;
  background-color: rgba(255, 255, 255, 0.85);
}
.chattingInput::placeholder{
  color: #000000;
}

  .el-header{
    background-color: rgb(0,0,0,0.9);
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-footer {
    background-color: rgb(204,236,255,0.8);
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: rgb(0,0,0,0.8);
    color: #ffffff;
    text-align: center;
    line-height: 30px;
  }

  .el-main {
    background-color: #FFFFFF;
    color: #333;
    text-align: center;
    line-height: 100px;
  }
  body > .el-container {
    margin-bottom: 100px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }


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
  width: 70px;
  height: 70px;
}
.hat {
  top:-70px;
  z-index: 2;
  position: relative;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url('../../assets/images/hat.png');
  border-radius: 50%;
  width: 90px;
  height: 90px;
}

</style>
<script>
import { reactive, onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { useRouter } from 'vue-router'
import conferenceUpdate from './conference-update.vue'
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import conferenceMain from './conference-main.vue'
import conferenceKickOutDialog from './conference-kickoutdialog.vue'

export default {
  name: 'conference-detail',
  components: {
    conferenceUpdate,
    conferenceMain,
    conferenceKickOutDialog,
  },


  setup () {
    const route = useRoute()
    const router = useRouter()
    const store = useStore()
    const state = reactive({
      conferenceId: '',
      form: {
        token: localStorage.getItem('jwt'), // jwt 토큰
        title: '',
        description: '',
        thumbnail_url: '',
        genre: '',
        viewers: 0,
        owner: false,
        roomEditDialogOpen: false,
        userName: "",
        message: "",
        recvList: [],
        stompClient: "",
        userId: "",
        max_viewers: 0,
        kickOutDialogOpen: false
        // drawer: false,
      },
    })

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      // 페이지에 들어오면 connect 실행
      connect()
      state.conferenceId = route.params.conferenceId
      store.commit('root/setMenuActiveMenuName', 'home')
      store.dispatch('root/roomDetail', { token: state.form.token, id: route.params.conferenceId }
      )
      .then(function (result) {
        state.form.title = result.data["title"]
        state.form.ownerId = result.data["ownerId"]
        state.form.description = result.data["description"]
        state.form.thumbnail_url = result.data["thumbnail_url"]
        state.form.genre = result.data["busking_genre"]
        state.form.owner = result.data["owner"]
        state.form.viewers = result.data["viewers"]
        state.form.userId = result.data["userId"]
        state.form.max_viewers = result.data["max_viewers"]
      })
      .catch(function (err) {
        alert('입장가능 인원을 초과하였습니다.')
        store.commit('root/setMenuActive', 0)
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        router.push({
          name: keys[0]
      })
      })
      // 뒤로가기 버튼 비활성화
      history.pushState(null, null, location.href);
      window.onpopstate = function () {
      history.go(1);
      }
      localStorage.setItem('abc', state.conferenceId )

    })

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
      localStorage.removeItem('abc')
      store.dispatch('root/roomOut', {
        token: state.form.token,
        id: state.conferenceId
      })
      .then(function (result) {
      })
      state.conferenceId = ''
    })

    // 버스킹 수정 모달창 생성
    const clickRoomEdit = function () {
      state.form.roomEditDialogOpen = true
    }

    // 버스킹 수정 모달창 종료
    const closeRoomEdit = function () {
      state.form.roomEditDialogOpen = false
    }

    // 버스킹 종료 버튼 눌렀을 때
    const roomDelete = function () {
      store.dispatch('root/deleteRoom', {
        token: state.form.token,
        id: route.params.conferenceId
      })
      .then(function () {
        alert('버스킹이 종료되었습니다.')
        store.commit('root/setMenuActive', 0)
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        router.push({
          name: keys[0]
      })
      })
    }

    // 관객이 나가기 버튼 눌렀을 때
    const goBackHome = function () {
      store.commit('root/setMenuActive', 0)
        const MenuItems = store.getters['root/getMenus']
        let keys = Object.keys(MenuItems)
        router.push({
          name: keys[0]
      })
    }

    // 강퇴 버튼
    const onOpenKickOutDialog = function () {
      state.form.kickOutDialogOpen = true
    }

    const onCloseKickOutDialog = function () {
      state.form.kickOutDialogOpen = false
    }

    //모자 애니메이션
    const clickHat = function(){
      state.form.flag=true;
    }

    ///////////////////////// 채팅 관련 ////////////////////////////

    const sendMessage = function (e) {
      if(e.keyCode === 13 && state.form.userId !== '' && state.form.message !== ''){
        send()
        state.form.message = ''
      }
    }

    const send = function() {
      // if (state.form.stompClient && state.form.stompClient.connected) {
      //   const msg = {
      //     type: "CHAT",
      //     roomId: route.params.conferenceId,
      //     // userName: state.form.userName,
      //     message: state.form.message,
      //     userId: state.form.userId
      //     // recvList: state.form.recvList,
      //   };
      const msg = {
        type: "CHAT",
        roomId: route.params.conferenceId,
        // userName: state.form.userName,
        message: state.form.message,
        userId: state.form.userId
        // recvList: state.form.recvList,
      }
        // console.log('메세지확인', msg)
        state.form.stompClient.send("/pub/chat/message", JSON.stringify(msg), {});
    }

    const connect = function() {
      // const serverURL = "https://localhost:8080/ws-stomp"
      const serverURL = "https://i5d107.p.ssafy.io/ws-stomp"
      let socket = new SockJS(serverURL);
      state.form.stompClient = Stomp.over(socket);
      // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      state.form.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          state.form.stompClient.connected = true;
          // console.log('소켓 연결 성공', frame, 'id', route.params.conferenceId);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          state.form.stompClient.subscribe('/sub/chat/room/' + route.params.conferenceId,
          res => {
            // console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            state.form.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          // console.log('소켓 연결 실패', error);
          state.form.stompClient.connected = false;
        }
      );
    }

    return { state, clickRoomEdit, closeRoomEdit, roomDelete, goBackHome, sendMessage, send, connect, onOpenKickOutDialog, onCloseKickOutDialog, clickHat }
  }
}
</script>
