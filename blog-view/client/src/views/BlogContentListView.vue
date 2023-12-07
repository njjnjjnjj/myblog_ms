<script setup lang="ts">

import BlogContentCard from "@/components/BlogContentCard.vue";
import {onMounted, ref} from "vue";
import {getAllBlog} from "@/apis/api";
import type {BlogMetadata} from "@/apis/interfaces";

let blogMetadataList = ref<BlogMetadata[]>();

onMounted(() => {
  getAllBlog("published").then((blogMetaDataList) => {
    blogMetadataList.value = blogMetaDataList;
    console.log('blogMetadataList.value:',blogMetadataList.value)
  });
})

function generateBlogContentList() {
  // 循环插入重复数据，模拟多条数据
  // for (let i = 0; i < 20; i++) {
  //   blogContentList.value.push({
  //     title: "你好标题" + i,
  //     content: "你好内容" + i
  //   });
  // }
}


function load() {
  generateBlogContentList();
}
</script>

<template>
  <div class="blog-content-list">
    <BlogContentCard v-for="blogMetadata of blogMetadataList" :key="blogMetadata.metadataId" :blog-content="blogMetadata"></BlogContentCard>
    <el-button @click="load">加载更多</el-button>
  </div>
</template>

<style scoped>

</style>
