<script setup lang="ts">
import BlogIndexHeader  from "@/components/BlogIndexHeader.vue";
import BlogIndexMain from "@/components/BlogIndexMain.vue";
</script>

<template>
  <el-container style="width: 100%; height: 100%">
    <el-header>
      <BlogIndexHeader></BlogIndexHeader>
    </el-header>
    <el-main>
      <BlogIndexMain></BlogIndexMain>
    </el-main>
  </el-container>
</template>

<style scoped>
  html{
    height: 100%;
    width: 100%;
  }
</style>
