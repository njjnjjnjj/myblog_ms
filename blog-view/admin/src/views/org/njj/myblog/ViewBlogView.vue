<template>
  <div>
    <v-md-editor v-model="blogInfo.blogContent" mode="preview" height="400px"></v-md-editor>
  </div>
</template>

<script setup>
import { onMounted, reactive } from "vue";
  import axios from "axios";

  const blogInfo = reactive({
    blogContent:'',
    publishTime:'',
    author:''
  });
  // TODO: 通过pops传入blogId
  const blogId = '20230218222931hjsuBmOUbeMcyjNT7n';

  onMounted(init);

  /**
   * 加载博客
   */
  function loadBlog(){
    axios.get('/api/blog/content?blogId='+blogId)
      .then((result)=>{
        //TODO 将状态码抽为常量
        if(result.status === 200){
          Object.assign(blogInfo,result.data.data);
        }
      })
      .catch((error)=>{
        console.error(error);
      })
  }

  function init(){
    loadBlog();
  }


</script>

<!--<script>-->
<!--export default {-->
<!--  name: "ViewBlogView"-->
<!--};-->
<!--</script>-->