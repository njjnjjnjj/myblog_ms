<script setup lang="ts">

import Router from "@/router";
import {onBeforeRouteLeave} from "vue-router";
import router from "@/router";
import {computed, onMounted, reactive, ref} from "vue";
import {getBlogContent} from "@/apis/api";
import type {BlogContentData} from "@/apis/interfaces";

let props = defineProps({
  metadataId:{
    type: String,
    required: true
  }
})

onBeforeRouteLeave((to, from, next)=>{
  to.meta.keepAlive = true;
  next();
})

function goBack(){
  Router.back();
}

let blogContentData = ref<BlogContentData>(<BlogContentData>{});

onMounted(()=>{
  loadBlogContent();
})

let loading = ref<boolean>(true);

function loadBlogContent(){
  getBlogContent(props.metadataId).then((result)=>{
    blogContentData.value = result;
    loading.value = false;
  })
}

const formattedDate = computed(() => {
  if(blogContentData.value.publishDatetime == null){
    return "";
  }
  const date = new Date(blogContentData.value.publishDatetime);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1，并使用padStart方法补零
  const day = String(date.getDate()).padStart(2, '0'); // 使用padStart方法补零
  const hours = String(date.getHours()).padStart(2, '0'); // 使用padStart方法补零
  const minutes = String(date.getMinutes()).padStart(2, '0'); // 使用padStart方法补零
  const seconds = String(date.getSeconds()).padStart(2, '0'); // 使用padStart方法补零
  return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`;
});

</script>

<template :key="blogContentData.metadataId">
  <el-page-header @back="goBack">
    <template #content>
      <el-skeleton :rows="0" animated :loading="loading">
        {{blogContentData.title}}
      </el-skeleton>
    </template>
    <template #extra>
        {{formattedDate}}
    </template>
  </el-page-header>
  <el-skeleton :rows="5" animated :loading="loading">
    <v-md-editor v-model="blogContentData.blogContent" mode="preview"></v-md-editor>
  </el-skeleton>

</template>

<style scoped>

</style>
