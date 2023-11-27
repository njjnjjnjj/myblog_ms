<script setup lang="ts">

import BlogContentCard from "@/components/BlogContentCard.vue";
import {ref} from "vue";

type BlogContent = {
  title: string,
  content: string
}

let blogContentList = ref<BlogContent[]>([]);

// 循环插入重复数据，模拟多条数据
for (let i = 0; i < 20; i++) {
  blogContentList.value.push({
    title: "title" + i,
    content: "content" + i
  });
}
</script>

<template>
  <BlogContentCard v-for="item of blogContentList" :key="item.title" :blog-content="item"></BlogContentCard>
</template>

<style scoped>

</style>
