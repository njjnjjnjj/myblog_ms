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
      <BlogSubscription></BlogSubscription>
      <BlogMusicShare></BlogMusicShare>
      <BlogCalendarCard></BlogCalendarCard>
    </el-aside>
    <el-backtop :right="50" :bottom="50"/>
  </el-container>
</template>

<style scoped>
.blog-index-aside {
  .el-card {
    margin: 20px;
  }
}
</style>
