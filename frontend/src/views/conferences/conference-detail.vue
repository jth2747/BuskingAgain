<template>
  <!-- <h2>{{ $route.params.conferenceId }}</h2> -->
  <h1>{{ state.form.ownerId }}님의 버스킹</h1>
  <h2>방 제목 : {{ state.form.title }}</h2>
  <h3>내용 : {{ state.form.description }}</h3>
  <p>
  <span>접속자 수 : {{ state.form.viewers}} / </span>
  <span>좋아요 : 0</span>
  </p>
  <!-- 방 만든 사람 -->
  <el-button-group v-if="state.form.owner">
    <el-button type="warning" icon="el-icon-edit" @click="clickRoomEdit">수정</el-button>
    <el-button type="danger" icon="el-icon-delete" @click="roomDelete">방 종료</el-button>
  </el-button-group>
  <!-- 관객 -->
  <el-button-group v-else>
    <el-button type="danger" @click="goBackHome">나가기</el-button>
  </el-button-group>
  <conference-update
    :open="state.form.roomEditDialogOpen"
    :title="state.form.title"
    :description="state.form.description"
    :thumbnail_url="state.form.thumbnail_url"
    :genre="state.form.genre"
    :token="state.form.token"
    :id="$route.params.conferenceId"
    @closeRoomEdit="closeRoomEdit"
  />
  <div id="socket">
    <!-- 유저이름:
    <input
      v-model="state.form.userName"
      type="text"
    > -->
    내용: <input
      v-model="state.form.message"
      type="text"
      @keyup="sendMessage"
    >
    <div
      v-for="(item, idx) in state.form.recvList"
      :key="idx"
    >
      <span> {{ item.userId }}</span>
      <span> : {{ item.message }}</span>
    </div>

  </div>
</template>
<style>
  #socket {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
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

export default {
  name: 'conference-detail',
  components: {
    conferenceUpdate,
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
      },
    })

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      // 페이지에 들어오면 connect 실행
      connect()
      // console.log('가나다라', Stomp.over(socket) )
      state.conferenceId = route.params.conferenceId
      store.commit('root/setMenuActiveMenuName', 'home')
      store.dispatch('root/roomDetail', { token: state.form.token, id: route.params.conferenceId }
      )
      .then(function (result) {
        console.log('result' ,result.data)
        state.form.title = result.data["title"]
        state.form.ownerId = result.data["ownerId"]
        state.form.description = result.data["description"]
        state.form.thumbnail_url = result.data["thumbnail_url"]
        state.form.genre = result.data["busking_genre"]
        state.form.owner = result.data["owner"]
        state.form.viewers = result.data["viewers"]
        state.form.userId = result.data["userId"]
      })
      .catch(function (err) {
        console.log('err', err)
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
    })

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
      store.dispatch('root/roomOut', {
        token: state.form.token,
        id: state.conferenceId
      })
      .then(function (result) {
        console.log(result)
      })
      state.conferenceId = ''
    })

    // 버스킹 수정 모달창 생성
    const clickRoomEdit = function () {
      console.log(state.form.roomEditDialogOpen)
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

    ///////////////////////// 채팅 관련 ////////////////////////////

    const sendMessage = function (e) {
      console.log('eeeeee', e.keyCode, 'username', state.form.userId, 'msg', state.form.message, 'recvList', state.form.recvList )
      if(e.keyCode === 13 && state.form.userId !== '' && state.form.message !== ''){
        send()
        state.form.message = ''
      }
    }

    const send = function() {
      console.log("Send message:" + state.form.message);
      if (state.form.stompClient && state.form.stompClient.connected) {
        const msg = {
          type: "CHAT",
          roomId: route.params.conferenceId,
          // userName: state.form.userName,
          message: state.form.message,
          userId: state.form.userId
          // recvList: state.form.recvList,
        };
        console.log('메세지확인', msg)
        state.form.stompClient.send("/pub/chat/message", JSON.stringify(msg), {});
      }
    }

    const connect = function() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      state.form.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      state.form.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          state.form.stompClient.connected = true;
          console.log('소켓 연결 성공', frame, 'id', route.params.conferenceId);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          state.form.stompClient.subscribe('/sub/chat/room/' + route.params.conferenceId,
          res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            state.form.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          state.form.stompClient.connected = false;
        }
      );
    }

    return { state, clickRoomEdit, closeRoomEdit, roomDelete, goBackHome, sendMessage, send, connect}
  }
}
</script>
