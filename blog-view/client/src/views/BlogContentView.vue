<script setup lang="ts">

import Router from "@/router";
import {onBeforeRouteLeave} from "vue-router";
import router from "@/router";

let props = defineProps({
  metadataId:{
    type: String,
    required: true
  }
})

onBeforeRouteLeave((to, from, next)=>{
  debugger;
  to.meta.keepAlive = true;
  next();
})

function goBack(){
  Router.back();
}
</script>

<template>
  <el-page-header @back="goBack">
    <template #content>
      {{metadataId}}
    </template>
  </el-page-header>
  <el-main style="background: red">

  </el-main>
</template>

<style scoped>

</style>
