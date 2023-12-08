<script setup lang="ts">

import Router from "@/router";
import {onBeforeRouteLeave} from "vue-router";
import router from "@/router";
import {onMounted, reactive, ref} from "vue";
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

function loadBlogContent(){
  getBlogContent(props.metadataId).then((result)=>{
    blogContentData.value = result;
  })
}

</script>

<template :key="blogContentData.metadataId">
  <el-page-header @back="goBack">
    <template #content>
      {{blogContentData.title}}
    </template>
  </el-page-header>
  <v-md-editor v-model="blogContentData.blogContent" mode="preview"></v-md-editor>
</template>

<style scoped>

</style>
