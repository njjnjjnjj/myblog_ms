<script setup lang="ts">
import BlogSearcher from "@/components/BlogSearcher.vue";
</script>

<template>
  <div class="blog-index-header">
    <el-row>
      <el-col :span="8">
        <p>Blog</p>
      </el-col>
      <el-col :span="8"></el-col>
      <el-col :span="8">
        <BlogSearcher></BlogSearcher>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>

</style>
