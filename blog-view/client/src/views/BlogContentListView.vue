<script setup lang="ts">

import BlogContentCard from "@/components/BlogContentCard.vue";
import {onMounted, ref} from "vue";

type BlogContent = {
  title: string,
  content: string
}

let blogContentList = ref<BlogContent[]>([]);

onMounted(()=>{
  generateBlogContentList();
})

function generateBlogContentList() {
  // 循环插入重复数据，模拟多条数据
  for (let i = 0; i < 20; i++) {
    blogContentList.value.push({
      title: "你好标题" + i,
      content: "你好内容" + i
    });
  }
}



function load(){
  generateBlogContentList();
}
</script>

<template>
  <div class="blog-content-list">
    <BlogContentCard v-for="item of blogContentList" :key="item.title" :blog-content="item"></BlogContentCard>
    <el-button @click="load">加载更多</el-button>
  </div>
</template>

<style scoped>

</style>
