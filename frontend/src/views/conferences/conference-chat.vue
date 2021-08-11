<template>
  <div id="socket">
    <h1>채팅</h1>
        <!-- <el-scrollbar height="50pc">
          <div v-for="(item, idx) in state.form.recvList" :key="idx">
            <span> {{ item.userId }}</span>
            <span> : {{ item.message }}</span>
          </div>
          채팅: <input
            v-model="state.form.message"
            type="text"
            @keyup="sendMessage"
          >
        </el-scrollbar> -->
      </div>
</template>

<script>
export default {

}
</script>

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
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 30px;
  }

  .el-main {
    background-color: #E9EEF3;
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
</style>
