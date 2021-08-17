<template>
  <div class="block">
  <el-timeline v-for="(room, i) in state.form.roomList[0]" :key="i">
      <el-card>
        <h4 style="text-align:left">방 제목: {{ room.title }}</h4>
        <p>장르: {{room.busking_genre}}</p>
        <p>시작 시간: {{room.start_time}}</p>
        <p>마친 시간: {{room.end_time}}</p>
        <p>설명: {{room.description}}</p>
        <p>좋아요: {{room.likes}}</p>
        <p>시청자: {{room.viewers}}</p>
      </el-card>
  </el-timeline>
</div>
</template>
<style>
@import url("//unpkg.com/element-plus/lib/theme-chalk/index.css");

p {
  text-align: left;
}

</style>
<script>
import { onMounted, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'

export default {
  name: 'History',

  props: {
    historyList:{
      type: Object,
    }
  },

  data() {
    return {
      token: localStorage.getItem('jwt'),
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
        roomList: [],
      },
    })


    onMounted(() => {
      store.dispatch('root/roomHistory',{
        token: state.form.token,
      })
      .then(function (result) {
        console.log('history in')
        console.log(result.data)
        state.form.roomList.push(result.data)
      })
    })

     const submit = function() {
      alert('다른 메뉴에서 검색을 이용하세요.')
    }


    return { state, submit}
  }
}
</script>
