<script setup lang="ts">

import Router from "@/router";

let props = defineProps({
  metadataId:{
    type: String,
    required: true
  }
})

function goBack(){
  Router.go(-1);
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
