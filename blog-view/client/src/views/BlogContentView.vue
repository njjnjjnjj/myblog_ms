<script setup lang="ts">
function goBack() {
  this.$router.go(-1);
}
</script>

<template>
  <el-page-header @back="goBack">
    <template #content>
      Title
    </template>
  </el-page-header>
  <el-main style="background: red">

  </el-main>
</template>

<style scoped>

</style>
