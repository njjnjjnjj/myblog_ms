<script setup lang="ts">
import {Search} from "@element-plus/icons-vue";
import {ref} from "vue";

let searchContent = ref<any>('');

function search() {
  console.log("search", searchContent.value);
}
</script>

<template>
  <!--  //TODO: 倪佳俊  [] 这里的 placeholder 使用 i18n 不写死-->
  <el-input v-model="searchContent" placeholder="">
    <template #append>
      <el-button :icon="Search" @click="search"/>
    </template>
  </el-input>
</template>

<style scoped>

</style>
