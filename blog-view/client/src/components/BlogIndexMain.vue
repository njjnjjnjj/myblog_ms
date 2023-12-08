<script setup lang="ts">
import BlogSubscription from "@/components/BlogSubscription.vue";
import BlogMusicShare from "@/components/BlogMusicShare.vue";
import BlogCalendarCard from "@/components/BlogCalendarCard.vue";


</script>

<template>
  <el-container>
    <el-main>
      <RouterView></RouterView>
    </el-main>
    <el-aside width="30vw" class="blog-index-aside">
<!--//TODO: 倪佳俊 逐渐实现其功能后取消注释  []-->
      <BlogMusicShare></BlogMusicShare>
<!--      <BlogSubscription></BlogSubscription>
      <BlogMusicShare></BlogMusicShare>
      <BlogCalendarCard></BlogCalendarCard>-->
    </el-aside>
  </el-container>
</template>

<style scoped>
.blog-index-aside {
  .el-card {
    margin: 20px;
  }
}
</style>
