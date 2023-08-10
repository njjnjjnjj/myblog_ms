<template>
  <el-tabs v-model="activeName" class="demo-tabs">
    <el-tab-pane lazy label="已发布" name="published">
      <BlogViewerPanel publish-mode="published"></BlogViewerPanel>
    </el-tab-pane>
    <el-tab-pane lazy label="草稿" name="sketch">
      <BlogViewerPanel publish-mode="sketch"></BlogViewerPanel>
    </el-tab-pane>
    <el-tab-pane lazy label="预发布" name="prePublish">
      <BlogViewerPanel publish-mode="prePublish"></BlogViewerPanel>
    </el-tab-pane>
    <el-tab-pane lazy label="已放入回收站" name="recycled">
      <BlogViewerPanel publish-mode="recycled"></BlogViewerPanel>
    </el-tab-pane>
  </el-tabs>
</template>

<script setup>
  import BlogViewerPanel from "@/views/org/njj/myblog/manager/blog/BlogViewerPanel";
  import { onBeforeMount, ref } from "vue";
  import axios from "axios";
  import { ElMessage } from "element-plus";

  const activeName = ref('published');

  onBeforeMount(()=>{
    // listContent();
  });
</script>