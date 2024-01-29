<template>
  <div class="container">
    <div class="app-container">
      <el-container>
        <!--搜索栏-->
        <el-header>
          <el-card style="width: 100%; margin-top: 5px">
            <el-row type="flex" justify="space-between">
              <el-col :span="14">
                <el-input v-model="queryParams.number" size="mini" style="width: 200px; margin-right: 10px"
                          placeholder="请输入水库编号"></el-input>
                <el-input v-model="queryParams.name" size="mini" style="width: 200px; margin-right: 20px"
                          placeholder="请输入水库名称"></el-input>
                <el-select v-model="queryParams.type" size="mini" placeholder="请选择水库类型" style="width: 150px; margin-right: 10px">
                  <el-option label="引水型水库" value="1"></el-option>
                  <el-option label="径流型水库" value="2"></el-option>
                  <el-option label="蓄水型水库" value="3"></el-option>
                  <el-option label="其它" value="0"></el-option>
                </el-select>
                <el-button type="primary" size="mini" round icon="el-icon-search"
                           @click="queryParams.page = 1; getReservoirList()">搜索
                </el-button>
              </el-col>
              <el-col :span="10">
                <el-button type="success" size="mini" plain icon="el-icon-plus" style="float: right; margin-right: 10px"
                           @click="$router.push('/reservoir/add')">添加水库
                </el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-header>

        <!--水库列表-->
        <el-main style="width: 100%">
          <el-table ref="reservoirTable" :data="reservoirList"
                    tooltip-effect="dark" stripe style="width: 100%">
            <el-table-column prop="number" label="水库编号" width="150" sortable></el-table-column>
            <el-table-column prop="name" label="水库名称" width="150" sortable></el-table-column>
            <el-table-column label="水库类型" width="200">
              <template slot-scope="scope">{{ scope.row.type == 0 ? '其它' : scope.row.type == 1 ? '引水型水库' : scope.row.type == 2 ? '径流型水库' : '蓄水型水库'}}</template>
            </el-table-column>
            <el-table-column prop="usableCapacity" label="有效库容" width="150"></el-table-column>
            <el-table-column prop="designCapacity" label="设计库容" width="150"></el-table-column>
            <el-table-column prop="normalWaterLevel" label="正常水位" width="150"></el-table-column>
            <el-table-column label="操作" width="220">
              <template slot-scope="scope">
                <el-button type="text" @click="showDetails(scope.row)" style="margin-right: 10px;color: green">详情</el-button>
                <el-button type="text" @click="$router.push(`/reservoir/add/${scope.row.id}`)" style="margin-right: 10px;color: blue">修改</el-button>
                <el-button type="text" @click="deleteReservoir(scope.row.id)" style="margin-right: 10px;color: red">删除</el-button>
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
        </el-main>
      </el-container>

      <!--水库详情-->
      <el-dialog :visible.sync="detailsDialog">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="font-size: 20px">水库详情</span>
          </div>
          <div>
            <el-row :gutter="20">
              <el-col :span="8" style="margin: 5px;font-size: 15px">水库编号：{{ reservoirDetails.number }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">水库名称：{{ reservoirDetails.name }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">
                水库类型：{{reservoirDetails.type == 0 ? '其它' : reservoirDetails.type == 1 ? '引水型水库' : reservoirDetails.type == 2 ? '径流型水库' : '蓄水型水库'}}
              </el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">水库地址：{{ reservoirDetails.address }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">有效容量：{{ reservoirDetails.usableCapacity }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">设计容量：{{ reservoirDetails.designCapacity }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">最大容量：{{ reservoirDetails.maxCapacity }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">最小容量：{{ reservoirDetails.minCapacity }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">死容量：{{ reservoirDetails.deadCapacity }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">正常水位：{{ reservoirDetails.normalWaterLevel }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">梅汛期水位：{{ reservoirDetails.meixunWaterLevel }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">台风期水位：{{ reservoirDetails.typhoonWaterLevel }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">洪水期水位：{{ reservoirDetails.floodWaterLevel }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">最高水位：{{ reservoirDetails.maxWaterLevel }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">死水位：{{ reservoirDetails.deadWaterLevel }}</el-col>
            </el-row>
          </div>
        </el-card>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import {deleteReservoir, getReservoirList} from "@/api/reservoir";
export default {
  name: 'List',
  data() {
    return {
      labelWidth: "150px",
      queryParams: {  // 查询参数
        page: 1,  // 分页参数
        pageSize: 10,
        number: '',  // 水库编号
        name: '',  // 水库名称
        type: '',  // 水库类型
      },
      total: 0, // 总条数
      reservoirList: [], // 员工列表
      // 水库详情
      reservoirDetails: {},
      detailsDialog: false,
    }
  },
  methods: {
    // 获取水库列表
    async getReservoirList() {
      const data = await getReservoirList(this.queryParams)
      this.reservoirList = data.records
      this.total = data.total
      this.queryParams.type = ''
      this.queryParams.name = ''
      this.queryParams.number = ''
    },
    // 页码变化
    pageChange(page) {
      this.queryParams.page = page
      this.getReservoirList()
    },
    // 水库详情详情
    showDetails(reservoir) {
      this.reservoirDetails = reservoir
      this.detailsDialog = true
    },
    // 删除水库
    deleteReservoir(id) {
      this.$confirm('此操作将删除该水库，是否继续？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await deleteReservoir(id)
        this.$message({
          type: 'success',
          message: '删除成功！'
        });
        this.queryParams.page = 1
        await this.getReservoirList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
  },
  created() {
    // 页面创建的时候获取水库
    this.getReservoirList()
  },
}
</script>

<style lang="scss" scoped>
.app-container {
  background: #fff;
  display: flex;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 300px;
}
</style>
