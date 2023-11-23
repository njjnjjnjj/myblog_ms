<template>
  <div>
    <el-container>
      <el-header>Blog Manager</el-header>
      <el-container>
        <el-aside width="200px">
          <ModuleMenu></ModuleMenu>
        </el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
  import ModuleMenu from "@/views/org/njj/myblog/manager/ModuleMenu";
</script>