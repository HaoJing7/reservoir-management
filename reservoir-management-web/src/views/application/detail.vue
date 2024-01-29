<template>
  <div class="container">
    <div class="app-container">
      <!--员工信息-->
      <el-card>
        <div slot="header" class="clearfix">
          <span style="font-size: 20px">员工信息</span>
        </div>
        <div>
          <div style="width: 150px; height: 150px; float: left">
            <img style="width: 100%; height: 100%" :src="applicationDetail.icon" alt="无效"/>
          </div>
          <div>
            <el-row :gutter="20">
              <el-col :span="8" style="margin: 5px;font-size: 15px">姓名：{{ applicationDetail.realname }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">登陆用户名：{{ applicationDetail.username }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">手机号：{{ applicationDetail.phone }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">
                性别：{{ applicationDetail.gender == 1 ? '男' : '女' }}
              </el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">工作地点：{{ applicationDetail.workPlace }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">家庭地址：{{ applicationDetail.homeAddress }}
              </el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">创建时间：{{ applicationDetail.createTime }}</el-col>
            </el-row>
          </div>
        </div>
      </el-card>
      <!--申请详情-->
      <el-card style="margin-top: 10px">
        <div slot="header" class="clearfix">
          <span style="font-size: 20px">申请信息</span>
        </div>
        <div>
          <div style="width: 150px; height: 150px; float: left">
            <img style="width: 100%; height: 100%" :src="applicationDetail.picture" alt="附件"/>
          </div>
          <div>
            <el-row :gutter="20">
              <el-col :span="8" style="margin: 5px;font-size: 15px">水库名称：{{ applicationDetail.reservoirName }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">电站名称：{{ applicationDetail.powerStationName }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">申请类型：{{ applicationDetail.type }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">申请内容：{{ applicationDetail.content }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">当前的状态：{{ applicationDetail.status }}</el-col>
              <el-col :span="8" style="margin: 5px;font-size: 15px">创建时间：{{ applicationDetail.createTime }}</el-col>
            </el-row>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
import {getApplicationDetail} from "@/api/application";

export default {
  name: 'Detail',
  data() {
    return {
      // 申请详情信息
      applicationDetail: {},
    }
  },
  methods: {
    // 获取申请详情
    async getApplicationDetail() {
      this.applicationDetail = await getApplicationDetail(this.$route.params.id)
    }
  },
  created() {
    this.getApplicationDetail()
  }
}
</script>
