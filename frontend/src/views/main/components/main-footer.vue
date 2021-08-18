<template>
  <el-row
    class="main-footer"
    :gutter="10">
    <div class="contents" style="color: white">
      Copyright © SAMSUNG All Rights Reserved.
    </div>
  </el-row>
</template>
<style>
@media (max-width: 60px) {
  .main-footer {
    height: 40px;
  }
  .main-footer .contents {
    line-height: 40px;
    text-align: center;
    margin: 0 auto;
  }
}

@media (min-width: 61px) {
  .main-footer {
    height: 40px;
  }
  .main-footer .contents {
    line-height: 40px;
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
      default: '40px'
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
