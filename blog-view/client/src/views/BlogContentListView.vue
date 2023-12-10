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
const pageSize = 10;

let loading = ref<boolean>(true);

function loadBlogList() {
  queryBlog("published", {
    current: currentPage,
    size: pageSize
  }).then((result) => {
    blogMetadataList.value?.push(...result.records);
    loading.value = false;
  });
}

function loadMore() {
  currentPage++;
  loadBlogList();
}
</script>

<template>
  <div class="blog-content-list">
    <el-skeleton :loading="loading" :rows="5" animated :throttle="1000" >
      <BlogContentCard v-for="blogMetadata of blogMetadataList" :key="blogMetadata.metadataId"
                       :blog-content="blogMetadata"></BlogContentCard>
      <el-button @click="loadMore">加载更多</el-button>
    </el-skeleton>
  </div>
</template>

<style scoped>

</style>
