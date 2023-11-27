<script setup lang="ts">
import BlogIndexHeader from "@/components/BlogIndexHeader.vue";
import BlogIndexMain from "@/components/BlogIndexMain.vue";
</script>

<template>
  <el-container style="width: 100%; height: 100%">
    <el-header>
      <BlogIndexHeader></BlogIndexHeader>
    </el-header>
    <el-scrollbar>
      <el-main>
        <BlogIndexMain></BlogIndexMain>
      </el-main>
    </el-scrollbar>
    <el-backtop :right="100" :bottom="100"/>
  </el-container>
</template>

<style scoped>
.el-header {
  position: fixed;
  top: 0;
  left: 0;
  box-sizing: border-box;
  width: 100%;
  z-index: 99997;
  overflow: hidden;
  background: #fff;
  color: #171717;
  margin: 0;
  box-shadow: 1px 3px rgba(0, 0, 0, .08), 0px 1px 2px rgba(0, 0, 0, .05);
}

.el-scrollbar{
  top: 60px;
}

</style>
