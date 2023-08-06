<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    :collapse="isCollapse"
    router
  >
    <el-sub-menu index="1">
      <template #title>
        <!--TODO: 自动导入图标-->
<!--        <el-icon><location /></el-icon>-->
        <span>文章</span>
      </template>
      <el-menu-item index="viewBlogViewer">所有文章</el-menu-item>
      <el-menu-item index="viewBlogEditor">撰写文章</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<script setup>
  import { ref } from "vue";

  const isCollapse = ref(false);
</script>

<script>
export default {
  name: "ModuleMenu"
};
</script>

<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>