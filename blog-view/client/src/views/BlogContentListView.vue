<script setup lang="ts">

import BlogContentCard from "@/components/BlogContentCard.vue";
import {onMounted, ref} from "vue";
import {getAllBlog, queryBlog} from "@/apis/api";
import type {BlogMetadata} from "@/apis/interfaces";

let blogMetadataList = ref<BlogMetadata[]>([]);

onMounted(() => {
  loadBlogList();
})

let currentPage = 1;
//TODO: 倪佳俊 [] 可配置
const pageSize = 2;

function loadBlogList(){
  queryBlog("published", {
    current: currentPage,
    size: pageSize
  }).then((result)=>{
    debugger;
    blogMetadataList.value?.push(...result.records);
    console.debug(blogMetadataList.value);
  });
}

function loadMore(){
  currentPage ++;
  loadBlogList();
}
</script>

<template>
  <div class="blog-content-list">
    <BlogContentCard v-for="blogMetadata of blogMetadataList" :key="blogMetadata.metadataId" :blog-content="blogMetadata"></BlogContentCard>
    <el-button @click="loadMore">加载更多</el-button>
  </div>
</template>

<style scoped>

</style>
