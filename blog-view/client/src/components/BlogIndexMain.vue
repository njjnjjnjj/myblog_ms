<script setup lang="ts">
import BlogSubscription from "@/components/BlogSubscription.vue";
import {ref} from "vue";
import BlogContentCard from "@/components/BlogContentCard.vue";

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
  <el-container>
    <el-main style="background: red">
      <BlogContentCard v-for="item of blogContentList" :key="item.title" :blog-content="item"></BlogContentCard>
    </el-main>
    <el-aside style="background: yellow">
      <BlogSubscription></BlogSubscription>
    </el-aside>
  </el-container>
</template>

<style scoped>

</style>
