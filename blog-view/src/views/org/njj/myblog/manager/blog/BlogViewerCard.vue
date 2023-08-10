<template>
  <el-card>
    <template #header>
      {{blogMetadata.title}}

      <el-dropdown>
        <el-icon><MoreFilled /></el-icon>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="deleteBlog">删除</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </template>
    <v-md-editor v-model="blogMetadata.contentSummary" mode="preview" height="400px"></v-md-editor>
  </el-card>
</template>

<script setup>
  const props = defineProps({
    blogMetadata:Object
  })
  console.log(props.blogMetadata);

  // 删除博客


</script>

<style scoped>

</style>