<template>
  <div class="container">
    <div class="app-container">
      <!--选择不同类型的按钮-->
      <el-button
        style="margin-right: 30px;margin-left: 10px"
        @click="queryParams.status = -1; getApplicationList()"
        autofocus
      >全部待办
      </el-button>
      <el-badge :value="waitingNumber" class="item">
        <el-button autofocus @click="queryParams.status = 0; getApplicationList()">待处理</el-button>
      </el-badge>
      <el-badge :value="processingNumber" class="item">
        <el-button autofocus @click="queryParams.status = 1; getApplicationList()">处理中</el-button>
      </el-badge>
      <el-button autofocus @click="queryParams.status = 2; getApplicationList()">处理完成</el-button>
      <el-card>
        <el-row type="flex">
          <!--搜索框 根据姓名、类型、时间范围 进行搜索-->
          <el-input v-model="queryParams.name" size="mini" style="width: 150px; margin-right: 10px"
                    placeholder="请输入姓名"></el-input>
          <el-select v-model="queryParams.type" size="mini" placeholder="请选择申请类型" style="width: 150px; margin-right: 10px">
            <el-option label="变压器故障" value="1"></el-option>
            <el-option label="调速器故障" value="2"></el-option>
            <el-option label="水轮机故障" value="3"></el-option>
            <el-option label="发电机组故障" value="4"></el-option>
            <el-option label="其它" value="0"></el-option>
          </el-select>
          <div class="block" style="margin-right: 10px">
            <el-date-picker
              size="mini"
              style="width: 350px"
              v-model="choiceTime"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </div>
          <el-button type="primary" size="mini" round icon="el-icon-search"
                     @click="queryParams.status = -1; getApplicationList()">搜索
          </el-button>
        </el-row>
        <el-table ref="employeeTable" :data="applicationList"
                  tooltip-effect="dark" stripe style="width: 100%">
          <el-table-column prop="employeeRealname" label="申请人" width="230" sortable></el-table-column>
          <el-table-column label="申请类型" width="250">
            <template slot-scope="scope">
              {{ scope.row.type === 1 ? '变压器故障' : scope.row.type === 2 ? '调速器故障' : scope.row.type === 3 ? '水轮机故障' : scope.row.type === 4 ? '发电机组故障' : '其它'}}
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="申请发起时间" width="250" sortable></el-table-column>
          <el-table-column label="状态" width="250">
            <template slot-scope="scope">
              {{ scope.row.status === 0 ? '待处理' : scope.row.status === 1 ? '处理中' : '处理完成'}}
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template v-slot="{row}">
              <el-button type="text" @click="$router.push(`/application/detail/${row.id}`)" style="margin-right: 10px;color: green">详情
              </el-button>
              <el-button type="text" style="color: blue" @click="changeStatus(row)">
                更改状态
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row style="height: 30px" align="middle" type="flex" justify="end">
          <el-pagination
            layout="total, prev, pager, next, jumper"
            :current-page="queryParams.page"
            :page-size="queryParams.pageSize"
            @current-change="pageChange"
            :total="total">
          </el-pagination>
        </el-row>

      </el-card>

    </div>
  </div>
</template>

<script>
import {changeStatus, getApplicationList, getProcessingNumber, getWaitingNumber} from "@/api/application";
import Template from "@/views/template.vue";

export default {
  name: 'Application',
  components: {Template},
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
        status: -1,  // 状态
      },
      total: 0,  // 总条数
      applicationList: [],  // 申请列表
      choiceTime: []  // 日期选择器选择的时间
    }
  },
  methods: {
    // 获取申请列表（获取之前要设置status）
    async getApplicationList() {
      // 设置初始时间和结束时间
      this.queryParams.beginTime = this.choiceTime[0]
      this.queryParams.endTime = this.choiceTime[1]
      const {records, total} = await getApplicationList(this.queryParams)
      this.applicationList = records
      this.total = total
      // 获取完列表之后对queryParams初始化
      this.queryParams.name = ''
      this.queryParams.beginTime = 0
      this.queryParams.endTime = 0
      this.queryParams.type = ''
      this.choiceTime = []
      // this.queryParams.status = -1
    },
    // 更改申请状态
    async changeStatus(application) {
      if (application.status === 2) {
        this.$message.warning('已处理完成，无需更改状态！')
      } else {
        await changeStatus(application)
        this.$message.success('状态更改成功！')
        this.waitingNumber = await getWaitingNumber()
        this.processingNumber = await getProcessingNumber()
        await this.getApplicationList()
      }
    },
    // 页码变化
    pageChange(page) {
      this.queryParams.page = page
      this.getApplicationList()
    },
  },
  // 初始化
  async created() {
    this.waitingNumber = await getWaitingNumber()
    this.processingNumber = await getProcessingNumber()
    await this.getApplicationList()
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
