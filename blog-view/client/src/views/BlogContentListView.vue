<script setup lang="ts">

import BlogContentCard from "@/components/BlogContentCard.vue";
import {ref} from "vue";

type BlogContent = {
  title: string,
  content: string
}

let blogContentList = ref<BlogContent[]>([]);

blogContentList.value.push({
  title: "title1",
  content: "content1"
});
</script>

<template>
  <BlogContentCard v-for="item of blogContentList" :key="item.title" :blog-content="item"></BlogContentCard>
</template>

<style scoped>

</style>
