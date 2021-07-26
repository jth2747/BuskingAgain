<template>
  <div class="button-wrapper">
  <el-select v-model="value" placeholder="Select">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>

  <el-button @click="clickBusking">버스킹 생성</el-button>
  </div>
  <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
    <li v-for="i in state.count" @click="clickConference(i)" class="infinite-list-item" :key="i" >
      <conference
        :image="image"
        :title="title"
        :desc="desc"
      />
    </li>
  </ul>

  <busking-dialog
    :open="buskingDialogOpen"
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
import Conference from './components/conference'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import BuskingDialog from '../main/components/busking-dialog.vue'


export default {
  name: 'Home',

  components: {
    BuskingDialog,
    Conference,
  },

  data() {
    return {
      buskingDialogOpen:false,
      title: '방 제목',
      desc: '상세 설명',
      image: 'https://www.ssafy.com/swp/images/sns_img.png',
      options: [{
          value: 'Option1',
          label: '좋아요 순'
        }, {
          value: 'Option2',
          label: '접속자 순'
        }, {
          value: 'Option3',
          label: '제목 순'
        }],
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
    const router = useRouter()

    const state = reactive({
      count: 12
    })

    const load = function () {
      state.count += 4
    }

    const clickConference = function (id) {
      router.push({
        name: 'conference-detail',
        params: {
          conferenceId: id
        }
      })
    }

    return { state, load, clickConference  }
  }
}
</script>
