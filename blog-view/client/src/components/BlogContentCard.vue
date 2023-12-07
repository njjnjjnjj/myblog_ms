<script setup lang="ts">
import image from "@/assets/image.jpeg";
import type {BlogMetadata} from "@/apis/interfaces";

defineProps({
  blogContent: {
    type: Object as () => BlogMetadata,
    required: true
  }
})

// 查看完整博客内容
function gotoBlogContentDetail() {
  console.log("查看完整博客内容");
}

</script>

<template>
  <el-card>
    <template #header>
      <div style="
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: nowrap;
            flex-direction: row;
      ">
        <p style="font-size: 18px;font-weight: bold;cursor:pointer;">{{ blogContent.title }}</p>
        <p>{{blogContent.publishDatetime}}</p>
      </div>
    </template>
    <el-container>
      <el-main style="padding: 0;">
<!--        <el-image :src="image"></el-image>-->
        <p>{{ blogContent.contentSummary }}</p>
      </el-main>
      <el-footer style="
            height: 32px;
            display: flex;
            flex-direction: row-reverse;
            align-items: center;"
      >
        <el-button @click="gotoBlogContentDetail">继续阅读</el-button>
      </el-footer>
    </el-container>


  </el-card>
</template>

<style scoped>

</style>
