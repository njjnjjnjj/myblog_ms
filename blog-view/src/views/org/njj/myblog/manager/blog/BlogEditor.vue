<template>
  <v-md-editor v-model="blogContent"
               @upload-image="handleUploadImage"
               :disabled-menus="[]"
               placeholder="请输入博客内容"
               left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code"
               height="400px"></v-md-editor>
</template>

<script setup>
import { reactive, ref } from "vue";
  import axios from "axios";

  const blogContent = ref('');

  function handleUploadImage(event, insertImage, files){
    console.log(event);
    console.log(insertImage);
    console.log(files);
    let formData = new FormData();
    formData.append("file", files[0]);
    axios.post("/api/picture/upload",formData)
      .then((result)=>{
        console.log(result);
      })
      .catch((error)=>{
        console.log(error);
      });
  }

  function getBlogContent(){
    return blogContent.value;
  }

  defineExpose({
    getBlogContent
  })
</script>
