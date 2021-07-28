<template>
  <h1>{{ $route.params.conferenceId + '번 방 상세 보기 페이지' }}</h1>
  <el-button-group>
    <el-button type="warning" icon="el-icon-edit" @click="openRoomEdit">수정</el-button>
    <el-button type="danger" icon="el-icon-delete" @click="roomDelete">방 종료</el-button>
  </el-button-group>
  <conference-update
    :open="state.form.roomEditDialogOpen"
    :title="state.form.title"
    :description="state.form.description"
    :thumbnailurl="state.thumbnailurltitle"
    :genre="state.form.genre"
    :token="state.form.token"
    @closeRoomEdit="closeRoomEdit"
  />
</template>
<style>
</style>
<script>
import { reactive, onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import conferenceUpdate from './conference-update.vue'

export default {
  name: 'conference-detail',
  components: {
    conferenceUpdate,
  },

  setup () {
    const route = useRoute()
    const store = useStore()
    const state = reactive({
      conferenceId: '',
      form: {
        token: localStorage.getItem('jwt'), // jwt 토큰
        title: '',
        description: '',
        thumbnailurl: '',
        genre: '',
        roomEditDialogOpen: false
      }
    })

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      console.log(route.params.conferenceId)
      state.conferenceId = route.params.conferenceId
      store.commit('root/setMenuActiveMenuName', 'home')
      store.dispatch('root/roomDetail', { id: route.params.conferenceId }
      )
      .then(function (result) {
        console.log(result)
      })
    })

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
      state.conferenceId = ''
    })

    // 버스킹 수정 모달창 생성
    const openRoomEdit = function () {
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
        location.reload()
      })
    }

    return { state, openRoomEdit, closeRoomEdit, roomDelete }
  }
}
</script>
