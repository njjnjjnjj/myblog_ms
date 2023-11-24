<script setup lang="ts">
import {ref} from "vue";
import {Message} from "@element-plus/icons-vue";

let eMail = ref<string>('');

function subscription() {
  console.log("subscription", eMail.value);
}
</script>

<template>
  <el-card>
    <template #header>订阅博客</template>
    <p><i>现在开始订阅吧！</i></p>
    <el-input v-model="eMail" placeholder="">
      <template #append>
        <el-button :icon="Message" @click="subscription"/>
      </template>
    </el-input>
  </el-card>

</template>

<style scoped>

</style>
