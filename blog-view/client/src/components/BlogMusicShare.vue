<script setup lang="ts">
import Jenny from "@/assets/Jenny.mp3";
</script>

<template>
  <el-card>
    <template #header>最近在听</template>
    <p style="text-align: center"><i>好不容易——告五人</i></p>
    <audio :src="Jenny" controls style="width: 100%"></audio>
  </el-card>
</template>

<style scoped>

</style>
