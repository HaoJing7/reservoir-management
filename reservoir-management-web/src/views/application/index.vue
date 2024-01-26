<template>
  <div class="container">
    <div class="app-container">
      <!--选择不同类型的按钮-->
      <el-button
        style="margin-right: 30px;margin-left: 10px"
        @click="getAll"
      >全部待办
      </el-button>
      <el-badge :value="waitingNumber" class="item">
        <el-button @click="getWaiting">待处理</el-button>
      </el-badge>
      <el-badge :value="processingNumber" class="item">
        <el-button @click="getProcessing">处理中</el-button>
      </el-badge>
      <el-button @click="getProcessed">处理完成</el-button>
      <!--搜索框 根据真实姓名、手机号、时间返回进行搜索-->

    </div>
  </div>
</template>

<script>
import {getProcessingNumber, getWaitingNumber} from "@/api/application";

export default {
  name: 'Application',
  data() {
    return {
      waitingNumber: 0,  // 待处理数量  用于角标
      processingNumber: 0,  // 处理中数量
      queryParams: {  // 查询参数
        page: 1,  // 分页参数
        pageSize: 10,
        name: '',  // 姓名模糊查询
        beginTime: '',  // 开始时间
        endTime: '',  // 结束时间
        type: '',  // 类型
      },
      total: 0,  // 总条数
      applicationList: [],  // 申请列表

    }
  },
  methods: {
    // 获取所有申请
    getAll() {

    },
    // 获取待处理的申请
    getWaiting() {

    },
    // 获取处理中的申请
    getProcessing() {

    },
    // 获取处理完成的申请
    getProcessed() {

    },
  },
  // 初始化
  created() {
    this.waitingNumber = getWaitingNumber()
    this.processingNumber = getProcessingNumber()
    this.getAll()
  },
}
</script>

<style>
.item {
  margin-right: 30px;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
