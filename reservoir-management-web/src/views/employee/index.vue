<template>
  <div class="container">
    <div class="app-container">
      <el-container>
        <el-header>
          <!--搜索栏-->
          <el-card style="width: 100%; margin-top: 5px">
            <el-row type="flex" justify="space-between">
              <el-col :span="14">
                <el-input v-model="queryParams.name" size="mini" style="width: 200px; margin-right: 10px" placeholder="请输入姓名"></el-input>
                <el-input v-model="queryParams.phone" size="mini" style="width: 200px; margin-right: 20px" placeholder="请输入电话"></el-input>
                <el-button type="primary" size="mini" round icon="el-icon-search" @click="queryParams.page = 1; getEmployeeList()">搜索</el-button>
              </el-col>
              <el-col :span="10">
                <el-button type="success" size="mini" plain icon="el-icon-plus" style="float: right; margin-right: 10px" @click="addEmp">添加员工</el-button>
                <el-button type="primary" size="mini" plain icon="el-icon-plus" style="float: right; margin-right: 10px" @click="messageDialogVisible=true">群发消息</el-button>
              </el-col>
            </el-row>
          </el-card>
        </el-header>
        <el-main style="width: 100%">
          <el-table ref="employeeTable" :data="employeeList" @selection-change="handleSelectionChange" tooltip-effect="dark" stripe style="width: 100%">
            <!--多选框-->
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column prop="realname" label="姓名" width="250" sortable></el-table-column>
            <el-table-column prop="phone" label="手机号" width="250" sortable></el-table-column>
            <el-table-column label="性别" width="150">
              <template slot-scope="scope">{{ scope.row.gender == 1 ? '男' : '女'}}</template>
            </el-table-column>
            <el-table-column prop="workPlace" label="工作地点" width="200"></el-table-column>
            <el-table-column label="操作" width="250">
              <template slot-scope="scope">
                <el-button type="text" @click="showDetails(scope.row)" style="margin-right: 10px;color: green">详情</el-button>
                <el-button type="text" style="color: red" @click="confirmDelete(scope.row.id)">删除</el-button>
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
      <el-dialog title="群发消息" :visible.sync="messageDialogVisible" @close="closeMessageDialog">
        <el-form ref="messageForm" :model="messageForm">
          <el-form-item label="消息等级" :label-width="labelWidth" prop="level">
            <el-select v-model="messageForm.level" placeholder="请选择消息等级">
              <el-option value="1" label="通知消息"></el-option>
              <el-option value="2" label="提示消息"></el-option>
              <el-option value="3" label="紧急消息"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="消息内容" :label-width="labelWidth" prop="content">
            <el-input
              type="textarea"
              placeholder="请输入消息内容"
              v-model="messageForm.content"
              autocomplete="off"
              style="width: 400px;"
              :rows="4">
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeMessageDialog">取 消</el-button>
          <el-button type="primary" @click="sendMessage">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog :visible.sync="detailsDialog">
        <el-card>
          <div slot="header" class="clearfix">
            <span style="font-size: 20px">员工详情</span>
          </div>
          <div>
            <div style="width: 150px; height: 150px; float: left" >
              <img style="width: 100%; height: 100%" :src="employeeDetails.icon" alt="无效" />
            </div>
            <div>
              <el-row :gutter="20">
                <el-col :span="8" style="margin: 5px;font-size: 15px">姓名：{{employeeDetails.realname}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">登陆用户名：{{employeeDetails.username}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">手机号：{{employeeDetails.phone}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">性别：{{employeeDetails.sex == 1? '男':'女'}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">工作地点：{{employeeDetails.workPlace}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">家庭地址：{{employeeDetails.homeAddress}}</el-col>
                <el-col :span="8" style="margin: 5px;font-size: 15px">创建时间：{{employeeDetails.createTime}}</el-col>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-dialog>
      <el-dialog :visible.sync="addDialog" @close="closeAddDialog">
        <el-form :model="addEmployeeForm" ref="addEmployeeForm">
          <el-form-item prop="realname" label="姓名" label-width="200px">
            <el-input v-model="addEmployeeForm.realname" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="username" label="登陆用户名" label-width="200px">
            <el-input v-model="addEmployeeForm.username" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password" label="密码" label-width="200px">
            <el-input v-model="addEmployeeForm.password" style="width: 300px" placeholder="密码默认为123456" clearable disabled></el-input>
          </el-form-item>
          <el-form-item prop="phone" label="手机号" label-width="200px">
            <el-input v-model="addEmployeeForm.phone" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="gender" label="性别" label-width="200px">
            <el-select v-model="addEmployeeForm.gender" placeholder="请选择">
              <el-option label="男" :value="1"></el-option>
              <el-option label="女" :value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="workPlace" label="工作地点" label-width="200px">
            <el-input v-model="addEmployeeForm.workPlace" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="homeAddress" label="家庭地址" label-width="200px">
            <el-input v-model="addEmployeeForm.homeAddress" style="width: 300px" clearable></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeAddDialog">取 消</el-button>
          <el-button type="primary" @click="confirmAdd">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import {addEmployee, deleteEmployee, getEmployeeList, sendMessage} from "@/api/employee";

// TODO 添加员工
export default {
  name: 'Employee',
  data() {
    return {
      labelWidth: "150px",
      queryParams: {  // 查询参数
        page: 1,  // 分页参数
        pageSize: 10,
        name: '',  // 姓名模糊查询
        phone: '', // 电话模糊查询
      },
      total: 0, // 总条数
      employeeList: [], // 员工列表
      selectedEmployee: [], // 选中群发的员工
      messageDialogVisible: false, // 发送消息对话框
      messageForm: {
        level: '',
        content: '',
        employeeIds: []
      },
      employeeDetails: {},
      detailsDialog: false,
      addEmployeeForm: {
        realname: '',
        username: '',
        password: '123456',
        phone: '',
        gender: 1,
        workPlace: '',
        homePlace: '',
        icon: '',
      },
      addDialog: false,
    }
  },
  methods: {
    // 获取员工列表
    async getEmployeeList() {
      const data = await getEmployeeList(this.queryParams)
      this.employeeList = data.records
      this.total = data.total
    },
    // 多选
    handleSelectionChange(val) {
      this.selectedEmployee = val;
    },
    // 群发消息
    async sendMessage() {
      for (const val of this.selectedEmployee) {
        console.log(val.id)
        this.messageForm.employeeIds.push(val.id)
      }
      await sendMessage(this.messageForm)
      this.closeMessageDialog()
    },
    closeMessageDialog() {
      this.messageDialogVisible = false
      this.$refs.messageForm.resetFields()
    },
    // 页码变化
    pageChange(page) {
      this.queryParams.page = page
      this.getEmployeeList()
    },
    // 删除员工
    confirmDelete(id) {
      this.$confirm('此操作将删除该用户，是否继续？', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        console.log(id)
        deleteEmployee(id)
        this.$message({
          type: 'success',
          message: '删除成功！'
        });
        this.queryParams.page = 1
        this.getEmployeeList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    // 员工详情
    showDetails(employee) {
      this.employeeDetails = employee
      this.detailsDialog = true
    },
    // 添加员工
    addEmp() {
      this.addDialog = true
    },
    closeAddDialog() {
      this.addDialog = false
      this.$refs.addEmployeeForm.resetFields()
    },
    async confirmAdd() {
      await addEmployee(this.addEmployeeForm)
      this.closeAddDialog()
    }
  },
  created() {
    // 页面创建的时候获取员工列表
    this.getEmployeeList()
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
