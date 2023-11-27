<script setup lang="ts">
import BlogIndexHeader from "@/components/BlogIndexHeader.vue";
import BlogIndexMain from "@/components/BlogIndexMain.vue";
</script>

<template>
  <el-container style="width: 100%; height: 100%">
    <el-header class="blog-index-header">
      <BlogIndexHeader></BlogIndexHeader>
    </el-header>
    <el-scrollbar class="blog-index-scrollbar">
      <el-main>
        <BlogIndexMain></BlogIndexMain>
      </el-main>
    </el-scrollbar>

  </el-container>
</template>

<style scoped>

</style>
