<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <!-- <el-skeleton-item variant="image" style="width: 100%; height: 190px" /> -->
          <img :src="image" alt="props.image" style="width: 100%; height: 190px">
        </template>
      </el-skeleton>
    </div>
    <div style="text-align: left; padding: 14px;">
      <span class="title">제목 : {{ title }}</span>
      <div class="bottom">
        <p style="margin: 1 0 0 1px;">장르 : {{ genre }}</p>
        <p>설명 : {{ desc }}</p>
      </div>
    </div>
  </el-card>
</template>
<style>
.el-card {
  margin: 0 0px;
  margin-bottom: 20px;
}
.el-card .image-wrapper {
  width: 100%;
  height: 190px;
}
.el-card .title {
  font-weight: bold;
}
.el-card .bottom {
  margin-top: 5px;
  display:-webkit-box;
  word-wrap:break-word;
  -webkit-box-orient:vertical;
  overflow:hidden;
  text-overflow:ellipsis;
}
/* 테블릿, 모바일의 경우 두 줄 말줄임표시 */
@media (max-width: 1269px) {
  .el-card .bottom {
    -webkit-line-clamp: 1;
    height:42px;
  }
}
/* 데스크탑의 경우 세 줄 말줄임표시 */
@media (min-width: 1270px) {
  .el-card .bottom {
    -webkit-line-clamp: 3;
    height:110px;
  }
}

</style>
<script>
export default {
  name: 'Home',

  props: {
    title: {
      type: String,
      default: '제목'
    },
    desc: {
      type: String,
      default: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    },
    image: {
      type: String,
      default: "https://www.ssafy.com/swp/images/sns_img.png"
    },
    genre: {
      type: String,
      default: "music"
    }
  },

  setup () {}
}
</script>
