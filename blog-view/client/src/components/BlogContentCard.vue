<script setup lang="ts">
import image from "@/assets/image.jpeg";
import type {BlogMetadata} from "@/apis/interfaces";
import {computed, onMounted, ref} from "vue";
import Router from "@/router";


let props = defineProps({
  blogContent: {
    type: Object as () => BlogMetadata,
    required: true
  }
})

// 查看完整博客内容
function gotoBlogContentDetail() {
  console.log("查看完整博客内容", props.blogContent?.metadataId);
  Router.push(`/blog_content/${props.blogContent?.metadataId}`);
}

const formattedDate = computed(() => {
  const date = new Date(props.blogContent?.publishDatetime);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1，并使用padStart方法补零
  const day = String(date.getDate()).padStart(2, '0'); // 使用padStart方法补零
  const hours = String(date.getHours()).padStart(2, '0'); // 使用padStart方法补零
  const minutes = String(date.getMinutes()).padStart(2, '0'); // 使用padStart方法补零
  const seconds = String(date.getSeconds()).padStart(2, '0'); // 使用padStart方法补零
  return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`;
});

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
        <p>{{ formattedDate }}</p>
      </div>
    </template>
    <el-container>
      <el-main style="padding: 0;">
        <!--        <el-image :src="image"></el-image>-->
        <v-md-editor v-model="blogContent.contentSummary" height="300px" mode="preview"></v-md-editor>
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
