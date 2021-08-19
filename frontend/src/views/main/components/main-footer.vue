<template>
  <el-row
    class="main-footer"
    :gutter="10">
    <span class="contents" style="color: white">
      Copyright © Busking Again
    </span>
        <!-- <span style="width:30px"><a href="http://www.facebook.com"><img src="../../../assets/images/facebook1.png"/></a></span>
        <span><a href="http://www.twitter.com"><img src="../../../assets/images/twitter1.png"/></a></span>
        <span><a href="http://www.instagram.com"><img src="../../../assets/images/instagram2.png"/></a></span> -->
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
