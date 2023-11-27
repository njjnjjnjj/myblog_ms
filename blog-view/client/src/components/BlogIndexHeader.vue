<script setup lang="ts">
import BlogSearcher from "@/components/BlogSearcher.vue";
import logo from "@/assets/logo.png";
import Router from "@/router";

function gotoIndex(){
  Router.push('/');
}

</script>

<template>
  <div class="blog-index-header">
    <el-row>
      <el-col :span="8">
        <el-image :src="logo" style="" @click="gotoIndex"></el-image>
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
