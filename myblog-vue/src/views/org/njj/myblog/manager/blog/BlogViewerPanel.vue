<template>
  <BlogViewerCard v-for="blogMetadata of page.records" :key="blogMetadata.metadataId"
                  :blogMetadata="blogMetadata">
  </BlogViewerCard>
  <el-pagination
    v-model:current-page="page.current"
    v-model:page-size="page.size"
    :page-sizes="[15, 30, 100, 200]"
    :background="true"
    layout="total, sizes, prev, pager, next, jumper"
    :total="page.total"
  />
<!--  @size-change="handleSizeChange"-->
<!--  @current-change="handleCurrentChange"-->
</template>

<script setup>
  import { onBeforeMount, reactive } from "vue";
  import axios from "axios";
  import { ElMessage } from "element-plus";
  import BlogViewerCard from "@/views/org/njj/myblog/manager/blog/BlogViewerCard"

  const page = reactive({
    size:15,
    current:1,
    records:[],
    total:0
  });

  const props = defineProps({
    publishMode:String
  });

  onBeforeMount(()=>{
    queryContent();
  })

  function queryContent(){
    axios.get( '/api/blog/content/query/'+props.publishMode,{
      params: page
    }).then((response)=>{
      debugger;
      Object.assign(page,response.data.data);
    }).catch((error)=>{
      ElMessage.error(error.message);
    })
  }
</script>

<script>
export default {
  name: "BlogViewerPanel"
};
</script>

<style scoped>

</style>