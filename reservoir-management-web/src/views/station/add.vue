<template>
  <div class="container">
    <div class="app-container">
      <div class="edit-form">
        <el-form ref="stationForm" :model="stationInfo" :rules="rules" label-width="220px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="电站编号" prop="powerStationNumber">
                <el-input v-model="stationInfo.powerStationNumber" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="电站名称" prop="powerStationName">
                <el-input v-model="stationInfo.powerStationName" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="电站类型" prop="type">
                <el-select v-model="stationInfo.type" size="mini" class="inputW">
                  <el-option label="引水型电站" :value="1"/>
                  <el-option label="径流型电站" :value="2"/>
                  <el-option label="蓄水型电站" :value="3"/>
                  <el-option label="其它" :value="0"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="是否梯级" prop="isStair">
                <el-select v-model="stationInfo.isStair" size="mini" class="inputW">
                  <el-option label="是" :value="'true'"/>
                  <el-option label="否" :value="'false'"/>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="对应水库id" prop="reservoirId">
                <el-input v-model="stationInfo.reservoirId" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="机组数量" prop="unit">
                <el-input v-model="stationInfo.unit" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="装机容量" prop="contain">
                <el-input v-model="stationInfo.contain" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="年均径流量" prop="annualAverageRunoff">
                <el-input v-model="stationInfo.annualAverageRunoff" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="年均发电量" prop="annualAveragePowerGeneration">
                <el-input v-model="stationInfo.annualAveragePowerGeneration" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="电站地址" prop="address">
                <el-input v-model="stationInfo.address" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="最大引用流量" prop="maxReferenceTaffic">
                <el-input v-model="stationInfo.maxReferenceTaffic" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="最小引用流量" prop="minReferenceTaffic">
                <el-input v-model="stationInfo.minReferenceTaffic" size="mini" class="inputW"/>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- 保存电站信息 -->
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
import {addStation, getStationDetail, updateStation} from "@/api/station";

export default {
  name: 'Add',
  created() {
    console.log(this.$route.params.id)
    // 获取路由参数中的id
    if (this.$route.params.id) {
      this.getStationDetail()
    }
  },
  data() {
    return {
      stationInfo: {
        powerStationNumber: '',  // 电站编号
        powerStationName: '',  // 电站名称
        type: '',  // 电站类型 1为引水 2为径流 3为蓄水 0为其它
        isStair: '',  // 是否梯级
        reservoirId: '',  // 对应水库id
        unit: '',  // 机组数量
        contain: '',  // 装机容量
        annualAverageRunoff: '',  // 年均径流量
        annualAveragePowerGeneration: '',  // 年均发电量
        address: '',  // 电站地址
        maxReferenceTaffic: '',  // 最大引用流量
        minReferenceTaffic: '',  // 最小引用流量
      },
      rules: {
        powerStationNumber: {required: true, message: '请输入电站编号', trigger: 'blur'},
        powerStationName: {required: true, message: '请输入电站名称', trigger: 'blur'},
        type: {required: true, message: '请选择电站类型', trigger: 'blur'},
        isStair: {required: true, message: '请选择是否梯级', trigger: 'blur'},
        reservoirId: {required: true, message: '请选择对应水库', trigger: 'blur'},
        unit: {required: true, message: '请输入机组数量', trigger: 'blur'},
        contain: {required: true, message: '请输入装机容量', trigger: 'blur'},
        annualAverageRunoff: {required: true, message: '请输入年均径流量', trigger: 'blur'},
        annualAveragePowerGeneration: {required: true, message: '请输入年均发电量', trigger: 'blur'},
        address: {required: true, message: '请输入电站地址', trigger: 'blur'},
        maxReferenceTaffic: {required: true, message: '请输入最大引用流量', trigger: 'blur'},
        minReferenceTaffic: {required: true, message: '请输入最小引用流量', trigger: 'blur'},
      }
    }
  },
  methods: {
    saveData() {
      this.$refs.stationForm.validate(async isOK => {
        if (isOK) {
          // 校验通过
          // 判断是否为编辑模式
          if (this.$route.params.id) {
            await updateStation(this.stationInfo)
            this.$message.success('修改电站成功')
          } else {
            await addStation(this.stationInfo)
            this.$message.success('新增电站成功')
          }
          this.$router.push('/station/list')
        }
      })
    },
    async getStationDetail() {
      this.stationInfo = await getStationDetail(this.$route.params.id)
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
