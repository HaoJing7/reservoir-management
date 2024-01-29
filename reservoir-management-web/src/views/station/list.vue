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
                          placeholder="请输入电站编号"></el-input>
                <el-input v-model="queryParams.name" size="mini" style="width: 200px; margin-right: 20px"
                          placeholder="请输入电站名称"></el-input>
                <el-select v-model="queryParams.type" size="mini" placeholder="请选择电站类型" style="width: 150px; margin-right: 10px">
                  <el-option label="引水型电站" value="1"></el-option>
                  <el-option label="径流型电站" value="2"></el-option>
                  <el-option label="蓄水型电站" value="3"></el-option>
                  <el-option label="其它" value="0"></el-option>
                </el-select>
                <el-button type="primary" size="mini" round icon="el-icon-search"
                           @click="queryParams.page = 1; getStationList()">搜索
                </el-button>
              </el-col>
              <el-col :span="10">
                <el-button type="success" size="mini" plain icon="el-icon-plus" style="float: right; margin-right: 10px"
                           @click="$router.push('/station/add')">添加电站
                </el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-header>

        <!--电站列表-->
        <el-main style="width: 100%">
          <el-table ref="stationTable" :data="stationList"
                    tooltip-effect="dark" stripe style="width: 100%">
            <el-table-column prop="powerStationNumber" label="电站编号" width="150" sortable></el-table-column>
            <el-table-column prop="powerStationName" label="电站名称" width="150" sortable></el-table-column>
            <el-table-column label="电站类型" width="200">
              <template slot-scope="scope">{{ scope.row.type == 0 ? '其它' : scope.row.type == 1 ? '引水型电站' : scope.row.type == 2 ? '径流型电站' : '蓄水型电站'}}</template>
            </el-table-column>
            <el-table-column label="是否梯级" width="150">
              <template slot-scope="scope">{{ scope.row.isStair == 'true' ? '是' : '否'}}</template>
            </el-table-column>
            <el-table-column prop="unit" label="机组数量" width="150"></el-table-column>
            <el-table-column prop="contain" label="装机容量" width="150"></el-table-column>
            <el-table-column label="操作" width="220">
              <template slot-scope="scope">
                <el-button type="text" @click="showDetails(scope.row)" style="margin-right: 10px;color: green">详情</el-button>
                <el-button type="text" @click="$router.push(`/station/add/${scope.row.powerStationId}`)" style="margin-right: 10px;color: blue">修改</el-button>
                <el-button type="text" @click="deleteStation(scope.row.powerStationId)" style="margin-right: 10px;color: red">删除</el-button>
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
            <span style="font-size: 20px">电站详情</span>
          </div>
          <div>
            <el-row :gutter="20">
              <el-col :span="8" style="margin: 5px;font-size: 15px">电站编号：{{ stationDetails.powerStationNumber }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">电站名称：{{ stationDetails.powerStationName }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">
                电站类型：{{stationDetails.type == 0 ? '其它' : stationDetails.type == 1 ? '引水型电站' : stationDetails.type == 2 ? '径流型电站' : '蓄水型电站'}}
              </el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">是否梯级：{{ stationDetails.isStair == 'true' ? '是' : '否' }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">对应水库id：{{ stationDetails.reservoirId }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">机组数量：{{ stationDetails.unit }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">装机容量：{{ stationDetails.contain }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">年均径流量：{{ stationDetails.annualAverageRunoff }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">年均发电量：{{ stationDetails.annualAveragePowerGeneration }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">电站地址：{{ stationDetails.address }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">最大引用流量：{{ stationDetails.maxReferenceTaffic }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">最小引用流量：{{ stationDetails.minReferenceTaffic }}</el-col>
            </el-row>
          </div>
        </el-card>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {deleteStation, getStationList} from "@/api/station";

export default {
  name: 'List',
  data() {
    return {
      labelWidth: "150px",
      queryParams: {  // 查询参数
        page: 1,  // 分页参数
        pageSize: 10,
        number: '',  // 电站编号
        name: '',  // 电站名称
        type: '',  // 电站类型
      },
      total: 0, // 总条数
      stationList: [], // 电站列表
      // 电站详情
      stationDetails: {},
      detailsDialog: false,
    }
  },
  methods: {
    // 获取电站列表
    async getStationList() {
      const data = await getStationList(this.queryParams)
      this.stationList = data.records
      this.total = data.total
      this.queryParams.type = ''
      this.queryParams.name = ''
      this.queryParams.number = ''
    },
    // 页码变化
    pageChange(page) {
      this.queryParams.page = page
      this.getStationList()
    },
    // 电站详情详情
    showDetails(station) {
      this.stationDetails = station
      this.detailsDialog = true
    },
    // 删除水库
    deleteStation(id) {
      this.$confirm('此操作将删除该电站，是否继续？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await deleteStation(id)
        this.$message({
          type: 'success',
          message: '删除成功！'
        });
        this.queryParams.page = 1
        await this.getStationList()
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
    this.getStationList()
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
