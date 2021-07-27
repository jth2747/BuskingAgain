<template>
  {{ $route.params.conferenceId + '번 방 상세 보기 페이지' }}
</template>
<style>
</style>
<script>
import { reactive, onMounted, onUnmounted } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'

export default {
  name: 'conference-detail',

  setup () {
    const route = useRoute()
    const store = useStore()
    const state = reactive({
      conferenceId: ''
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

    return { state }
  }
}
</script>
