<script setup lang="ts">

defineProps({
  blogContent: {
    type: Object,
    required: true
  }
})

</script>

<template>
  <el-card>
    <template #header>
      {{blogContent.title}}
    </template>
    <p>{{blogContent.content}}</p>
  </el-card>
</template>

<style scoped>

</style>
