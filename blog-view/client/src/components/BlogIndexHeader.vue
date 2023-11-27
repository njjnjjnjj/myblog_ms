<script setup lang="ts">
import BlogSearcher from "@/components/BlogSearcher.vue";
import logo from "@/assets/logo.png";
import Router from "@/router";

function gotoIndex(){
  Router.push('/');
}

</script>

<template>
  <div class="blog-index-header-flex">
    <el-image alt="回到首页" id="blog-index-header-logo" :src="logo" style="" @click="gotoIndex"></el-image>
    <BlogSearcher></BlogSearcher>
<!--    <el-row>-->
<!--      <el-col :span="8">-->
<!--        <el-image id="blog-index-header-logo" :src="logo" style="" @click="gotoIndex"></el-image>-->
<!--      </el-col>-->
<!--      <el-col :span="8"></el-col>-->
<!--      <el-col :span="8">-->
<!--        <BlogSearcher></BlogSearcher>-->
<!--      </el-col>-->
<!--    </el-row>-->
  </div>
</template>

<style scoped>

</style>
