<template>
  <h1>{{ state.form.ownerId }}님의 버스킹</h1>
  <h2>방 제목 : {{ state.form.title }}</h2>
  <h3>내용 : {{ state.form.description }}</h3>
  <p>
  <span>접속자 수 : {{ state.form.viewers}} / </span>
  <span>  좋아요 : 0</span>
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
    유저이름:
    <input
      v-model="userName"
      type="text"
    >
    내용: <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3>유저이름: {{ item.userName }}</h3>
      <h3>내용: {{ item.content }}</h3>
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
  data() {
    return {
      userName: "",
      message: "",
      recvList: []
    }
  },
  created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.userName !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          userName: this.userName,
          content: this.message
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },
    connect() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/send", res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );
    }
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
      },
    })

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      console.log(route.params.conferenceId)
      state.conferenceId = route.params.conferenceId
      store.commit('root/setMenuActiveMenuName', 'home')
      store.dispatch('root/roomDetail', { token: state.form.token, id: route.params.conferenceId }
      )
      .then(function (result) {
        console.log(result.data)
        state.form.title = result.data["title"]
        state.form.ownerId = result.data["ownerId"]
        state.form.description = result.data["description"]
        state.form.thumbnail_url = result.data["thumbnail_url"]
        state.form.genre = result.data["busking_genre"]
        state.form.owner = result.data["owner"]
        state.form.viewers = result.data["viewers"]
      })
    })

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
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

    return { state, clickRoomEdit, closeRoomEdit, roomDelete, goBackHome }
  }
}
</script>
