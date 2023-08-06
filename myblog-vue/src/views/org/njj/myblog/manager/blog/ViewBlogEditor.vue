<template>
  <ElPageHeader>
    <template #title>
      撰写博客
    </template>
    <template #content>
      <el-form inline :rules="formRules" :model="blogMetaData">
        <el-form-item  label="标题" prop="title">
          <el-input v-model="blogMetaData.title" placeholder="请输入标题" autofocus></el-input>
        </el-form-item>
      </el-form>
    </template>
    <template #extra>
      <el-button @click="saveBlog">保存草稿</el-button>
      <el-button type="primary" @click="prePublishBlog">发布</el-button>
    </template>
    <template #default />
  </ElPageHeader>
  <el-drawer v-model="drawer" title="发布参数" size="20%">
    <template #default>
      <el-form :model="blogMetaData" label-position="top">
        <el-form-item label="公开性">
          <el-radio-group v-model="blogMetaData.visibility">
            <el-radio-button label="public" value="public">公开</el-radio-button>
            <el-radio-button label="private" value="private">私密</el-radio-button>
            <el-radio-button label="protected" value="protected">密码保护</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="blogMetaData.visibility === 'protected'" label="访问密码">
          <el-input v-model="blogMetaData.accessPassword" placeholder="请输入访问密码"></el-input>
        </el-form-item>
        <el-form-item label="发布时间">
          <el-radio-group v-model="blogMetaData.publishMode">
            <el-radio-button label="published">立刻</el-radio-button>
            <el-radio-button label="prePublish">指定</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="blogMetaData.publishMode === 'prePublish'" label="发布时间">
          <el-config-provider :locale="zhCn">
            <el-date-picker
              v-model="blogMetaData.prePublishDatetime"
              type="datetime"
              placeholder="请选择发布时间"
              format="YYYY/MM/DD hh:mm:ss"
              value-format="x"
            />
          </el-config-provider>
        </el-form-item>
<!--        // TODO: 添加标签以及分类-->
      </el-form>
    </template>
    <template #footer>
      <el-button>取消</el-button>
      <el-button type="primary" @click="publishBlog">发布</el-button>
    </template>
  </el-drawer>
  <BlogEditor ref="blogEditor"></BlogEditor>
</template>

<script setup>
  import BlogEditor from '@/views/org/njj/myblog/manager/blog/BlogEditor';
  import { reactive, ref, toRefs } from "vue";
  import zhCn from'element-plus/es/locale/lang/zh-cn';
  import axios from "axios";
  import { ElMessage } from "element-plus";

  const drawer = ref(false);
  const blogMetaData = reactive({
    title:'',
    visibility:'public',
    accessPassword:'',
    prePublishDatetime:'',
    publishMode:'published'
  });
  const formRules = reactive({
    title:[
      { required: true, message: '请输入标题', trigger: 'blur' },
    ]
  });
  const blogEditor = ref(null);


  /**
   * 预发布博客，设置发布参数
   */
  function prePublishBlog(){
    drawer.value = true;
  }

  /**
   * 发布博客
   */
  function publishBlog(){
    axios.post( '/api/blog/content',
      generateBlogRequestBody()
    ).then((response)=>{
      ElMessage.success('博客发布成功');
    }).catch((error)=>{
      ElMessage.error('博客放松失败:'+error.message);
    })
  }

  /**
   * 保存草稿
   */
  function saveBlog(){
    axios.post( '/api/blog/content',
      generateBlogRequestBody('sketch')
    ).then((response)=>{
      ElMessage.success('博客保存成功');
    }).catch((error)=>{
      ElMessage.error('博客保存失败:'+error.message);
    })
  }

  function generateBlogRequestBody(publishMode){
    let title = blogMetaData.title;
    let blogContent = getBlogContent();
    if(publishMode == null){
      publishMode = blogMetaData.publishMode;
    }
    return {
      title: title,
      blogContent: blogContent,
      visibility: blogMetaData.visibility,
      accessPassword: blogMetaData.accessPassword,
      prePublishDatetime: blogMetaData.prePublishDatetime,
      publishMode: publishMode
    }
  }

  /**
   * 获取博客内容
   * @returns 博客内容
   */
  function getBlogContent(){
    return blogEditor.value.getBlogContent();
  }


</script>


<style scoped>
  .el-form-item{
    margin-bottom: 0;
  }
  .el-input{
    width: 300px;
  }
</style>