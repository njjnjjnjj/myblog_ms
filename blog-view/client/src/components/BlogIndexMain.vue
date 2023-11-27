<script setup lang="ts">
import BlogSubscription from "@/components/BlogSubscription.vue";
import BlogMusicShare from "@/components/BlogMusicShare.vue";


</script>

<template>
  <el-container>
    <el-main>
      <RouterView></RouterView>
    </el-main>
    <el-aside width="30vw" class="blog-index-aside">
      <BlogSubscription></BlogSubscription>
      <BlogMusicShare></BlogMusicShare>
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
