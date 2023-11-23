<template>
  <div>
    <v-md-editor v-model="blogContent"
                 @save="saveBlog"
                 @upload-image="handleUploadImage"
                 :disabled-menus="[]"
                 height="400px"></v-md-editor>
  </div>
</template>

<script setup>
  import { getCurrentInstance, ref } from "vue";
  import axios from "axios";

  const blogContent = ref('');

  /**
   *
   */
  function saveBlog(){
    axios.post( '/api/blog/content', {
      blogContent: blogContent.value
    }).then((response)=>{
      console.log(response);
    }).catch((error)=>{
      console.log(error);
    })
  }

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
    debugger;
  }
</script>


<!--<script>-->
<!--import { ref } from "vue";-->

<!--/**-->
<!-- * 博客编辑组件-->
<!-- */-->
<!--export default {-->
<!--  name: "EditBlogView",-->
<!--  setup(){-->

<!--  }-->

<!--};-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->