<template>
  <el-row
    class="main-footer">
    <div class="contents">
      Copyright © SAMSUNG All Rights Reserved.
    </div>
  </el-row>
</template>
<style>
@media (max-width: 60px) {
  .main-footer {
    height: 60px;
  }
  .main-footer .contents {
    line-height: 60px;
    text-align: center;
    margin: 0 auto;
  }
}

@media (min-width: 61px) {
  .main-footer {
    height: 60px;
  }
  .main-footer .contents {
    line-height: 60px;
    text-align: center;
    margin: 0 auto;
  }
}
</style>
<script>
import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'main-footer',

  props: {
    height: {
      type: String,
      default: '60px'
    }
  },

  setup() {
    const state = reactive({})

    return { state }
  }
}
</script>
<style>
</style>
