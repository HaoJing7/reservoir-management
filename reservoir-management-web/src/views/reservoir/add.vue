<template>
  <div class="container">
    <div class="app-container">
      <div class="edit-form">
        <el-form ref="reservoirForm" :model="reservoirInfo" :rules="rules" label-width="220px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="水库编号" prop="number">
                <el-input v-model="reservoirInfo.number" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="水库名称" prop="name">
                <el-input v-model="reservoirInfo.name" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="水库类型" prop="type">
                <el-select v-model="reservoirInfo.type" size="mini" class="inputW">
                  <el-option label="引水型水库" :value="1"/>
                  <el-option label="径流型水库" :value="2"/>
                  <el-option label="蓄水型水库" :value="3"/>
                  <el-option label="其它" :value="0"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="水库地址" prop="name">
                <el-input v-model="reservoirInfo.address" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="有效容量" prop="name">
                <el-input v-model="reservoirInfo.usableCapacity" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="设计容量" prop="name">
                <el-input v-model="reservoirInfo.designCapacity" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="最大容量" prop="name">
                <el-input v-model="reservoirInfo.maxCapacity" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="最小容量" prop="name">
                <el-input v-model="reservoirInfo.minCapacity" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="死容量" prop="name">
                <el-input v-model="reservoirInfo.deadCapacity" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="正常水位" prop="name">
                <el-input v-model="reservoirInfo.normalWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="梅汛期水位" prop="name">
                <el-input v-model="reservoirInfo.meixunWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="台风期水位" prop="name">
                <el-input v-model="reservoirInfo.typhoonWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="洪水期水位" prop="name">
                <el-input v-model="reservoirInfo.floodWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="最高水位" prop="name">
                <el-input v-model="reservoirInfo.maxWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="死水位" prop="name">
                <el-input v-model="reservoirInfo.deadWaterLevel" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 保存水库信息 -->
          <el-row type="flex">
            <el-col :span="12" style="margin-left:220px">
              <el-button size="mini" type="primary" @click="saveData">保存</el-button>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import {addReservoir, getReservoirDetail, updateReservoir} from "@/api/reservoir";

export default {
  name: 'Add',
  created() {
    // 获取路由参数中的id
    if (this.$route.params.id) {
      this.getReservoirDetail()
    }
  },
  data() {
    return {
      reservoirInfo: {
        number: '',  // 水库编号
        name: '',  // 水库名称
        type: '',  // 水库类型 1为引水 2为径流 3为蓄水 0为其它
        address: '',  // address
        usableCapacity: '',  // 有效容量
        designCapacity: '',  // 设计容量
        maxCapacity: '',  // 最大容量
        minCapacity: '',  // 最小容量
        deadCapacity: '',  // 死容量
        normalWaterLevel: '',  // 正常水位
        meixunWaterLevel: '',  // 梅汛期水位
        typhoonWaterLevel: '',  // 台风期水位
        floodWaterLevel: '',  // 洪水期水位
        maxWaterLevel: '',  // 最高水位
        deadWaterLevel: '',  // 死水位
      },
      rules: {
        number: {required: true, message: '请输入水库编号', trigger: 'blur'},
        name: {required: true, message: '请输入水库名称', trigger: 'blur'},
        type: {required: true, message: '请选择水库类型', trigger: 'blur'},
        address: {required: true, message: '请输入水库编号', trigger: 'blur'},
        usableCapacity: {required: true, message: '请输入有效容量', trigger: 'blur'},
        designCapacity: {required: true, message: '请输入设计容量', trigger: 'blur'},
        maxCapacity: {required: true, message: '请输入最大容量', trigger: 'blur'},
        minCapacity: {required: true, message: '请输入最小容量', trigger: 'blur'},
        deadCapacity: {required: true, message: '请输入死容量', trigger: 'blur'},
        normalWaterLevel: {required: true, message: '请输入正常水位', trigger: 'blur'},
        meixunWaterLevel: {required: true, message: '请输入梅汛期水位', trigger: 'blur'},
        typhoonWaterLevel: {required: true, message: '请输入台风期水位', trigger: 'blur'},
        floodWaterLevel: {required: true, message: '请输入洪水期水位', trigger: 'blur'},
        maxWaterLevel: {required: true, message: '请输入最高水位', trigger: 'blur'},
        deadWaterLevel: {required: true, message: '请输入死水位', trigger: 'blur'},
      }
    }
  },
  methods: {
    saveData() {
      this.$refs.reservoirForm.validate(async isOK => {
        if (isOK) {
          // 校验通过
          // 判断是否为编辑模式
          if (this.$route.params.id) {
            await updateReservoir(this.reservoirInfo)
            this.$message.success('修改水库成功')
          } else {
            await addReservoir(this.reservoirInfo)
            this.$message.success('新增水库成功')
          }
          this.$router.push('/reservoir/list')
        }
      })
    },
    async getReservoirDetail() {
      this.reservoirInfo = await getReservoirDetail(this.$route.params.id)
    }
  }
}
</script>

<style scoped lang="scss">
.edit-form {
  background: #fff;
  padding: 20px;

  .inputW {
    width: 380px
  }
}

</style>
