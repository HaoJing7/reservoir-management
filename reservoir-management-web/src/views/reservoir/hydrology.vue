<template>
  <div class="container">
    <div class="app-container">
      <h1>水文数据管理</h1>
      <div class="data-container">
        <div class="data-item">
          <h3>水位（m）</h3>
          <p>{{ hydrologyData.waterLevel }}</p>
        </div>
        <div class="data-item">
          <h3>径流量大小（m³/s）</h3>
          <p>{{ hydrologyData.runoffVolume }}</p>
        </div>
        <div class="data-item">
          <h3>含沙量（mg/L）</h3>
          <p>{{ hydrologyData.sedimentConcentration }}</p>
        </div>
        <div class="data-item">
          <h3>汛期（季节）</h3>
          <p>{{ hydrologyData.floodSeason === 1 ? '春季' : hydrologyData.floodSeason === 2 ? '夏季' : hydrologyData.floodSeason === 3 ? '秋季' : '冬季'}}</p>
        </div>
        <div class="data-item">
          <h3>有无结冰期</h3>
          <p>{{ hydrologyData.presenceOfIceCover === 0 ? '无' : '有' }}</p>
        </div>
        <div class="data-item">
          <h3>流速（m/s）</h3>
          <p>{{ hydrologyData.flowVelocity }}</p>
        </div>
        <div class="data-item">
          <h3>河流水系形状</h3>
          <p>{{ hydrologyData.riverNetworkShape === 0 ? '未知' : hydrologyData.riverNetworkShape === 1 ? '树枝状水系' : hydrologyData.riverNetworkShape === 2 ? '向心状水系' : hydrologyData.riverNetworkShape === 3 ? '平行状水系' : '网状水系'}}</p>
        </div>
        <div class="data-item">
          <h3>水能资源蕴藏量（MW）</h3>
          <p>{{ hydrologyData.hydropowerPotential }}</p>
        </div>
        <div class="data-item">
          <h3>河流航运价值</h3>
          <p>{{ hydrologyData.riverNavigationValue === 0 ? '低': hydrologyData.riverNavigationValue === 1 ? '中': '高'}}</p>
        </div>
      </div>

      <!--修改内容-->
      <button class="btn-edit" @click="updateHydrology">修改数据</button>
      <el-dialog :visible.sync="updateDialog" @close="close">
        <div>
          <h5>*下列参数全部填写整数格式（四舍五入）</h5>
        </div>
        <el-form :model="updateHydrologyForm" ref="updateHydrologyForm" :rules="updateRules">
          <el-form-item prop="waterLevel" label="水位（m）" label-width="200px">
            <el-input v-model="updateHydrologyForm.waterLevel" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="runoffVolume" label="径流量大小（m³/s）" label-width="200px">
            <el-input v-model="updateHydrologyForm.runoffVolume" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="sedimentConcentration" label="含沙量（mg/L）" label-width="200px">
            <el-input v-model="updateHydrologyForm.sedimentConcentration" style="width: 300px" clearable></el-input>
          </el-form-item>

          <el-form-item prop="floodSeason" label="汛期（季节）" label-width="200px">
            <el-select v-model="updateHydrologyForm.floodSeason" placeholder="请选择">
              <el-option label="春季" :value="1"></el-option>
              <el-option label="夏季" :value="2"></el-option>
              <el-option label="秋季" :value="3"></el-option>
              <el-option label="冬季" :value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="presenceOfIceCover" label="有无结冰期" label-width="200px">
            <el-select v-model="updateHydrologyForm.presenceOfIceCover" placeholder="请选择">
              <el-option label="无" :value="0"></el-option>
              <el-option label="有" :value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="flowVelocity" label="流速（m/s）" label-width="200px">
            <el-input v-model="updateHydrologyForm.flowVelocity" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="riverNetworkShape" label="河流水系形状" label-width="200px">
            <el-select v-model="updateHydrologyForm.riverNetworkShape" placeholder="请选择">
              <el-option label="未知" :value="0"></el-option>
              <el-option label="树枝状水系" :value="1"></el-option>
              <el-option label="向心状水系" :value="2"></el-option>
              <el-option label="平行状水系" :value="3"></el-option>
              <el-option label="网状水系" :value="4"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="hydropowerPotential" label="水能资源蕴藏量（MW）" label-width="200px">
            <el-input v-model="updateHydrologyForm.hydropowerPotential" style="width: 300px" clearable></el-input>
          </el-form-item>
          <el-form-item prop="riverNavigationValue" label="河流航运价值" label-width="200px">
            <el-select v-model="updateHydrologyForm.riverNavigationValue" placeholder="请选择">
              <el-option label="低" :value="0"></el-option>
              <el-option label="中" :value="1"></el-option>
              <el-option label="高" :value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="confirm">确 定</el-button>
        </div>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import {getHydrology, updateHydrology} from "@/api/reservoir";

export default {
  name: 'Hydrology',
  data() {
    return {
      showModal: false,
      hydrologyData: {
        waterLevel: '',  // 水位
        runoffVolume: '',  // 径流量大小
        sedimentConcentration: '',  // 含沙量
        floodSeason: '',  // 汛期
        presenceOfIceCover: '',  // 有无结冰期
        flowVelocity: '',  // 流速
        riverNetworkShape: '',  // 河流水系形状
        hydropowerPotential: '',  //  水能资源蕴藏量
        riverNavigationValue: '',  //  河流航运价值
      },

      // 修改数据
      updateDialog: false,
      updateHydrologyForm: {
        waterLevel: '',  // 水位
        runoffVolume: '',  // 径流量大小
        sedimentConcentration: '',  // 含沙量
        floodSeason: '',  // 汛期
        presenceOfIceCover: '',  // 有无结冰期
        flowVelocity: '',  // 流速
        riverNetworkShape: '',  // 河流水系形状
        hydropowerPotential: '',  //  水能资源蕴藏量
        riverNavigationValue: '',  //  河流航运价值
      },
      updateRules: {
        waterLevel: {required: true, message: '请输入水位', trigger: 'blur'},
        runoffVolume: {required: true, message: '请输入径流量', trigger: 'blur'},
        sedimentConcentration: {required: true, message: '请输入含沙量', trigger: 'blur'},
        floodSeason: {required: true, message: '请选择汛期', trigger: 'blur'},
        presenceOfIceCover: {required: true, message: '请选择有无结冰期', trigger: 'blur'},
        flowVelocity: {required: true, message: '请输入流速', trigger: 'blur'},
        riverNetworkShape: {required: true, message: '请选择水系形状', trigger: 'blur'},
        hydropowerPotential: {required: true, message: '请输入水能资源蕴藏量', trigger: 'blur'},
        riverNavigationValue: {required: true, message: '请选择河流航运价值', trigger: 'blur'},
      },

    };
  },

  methods: {
    // 获取水文数据
    async getHydrology() {
      const data = await getHydrology()
      Object.assign(this.hydrologyData, data)
      console.log(this.hydrologyData)
    },
    // 修改水文数据
    updateHydrology() {
      // 对form中的属性进行修改
      Object.assign(this.updateHydrologyForm, this.hydrologyData)
      this.updateDialog = true
    },
    closeDialog() {
      this.updateDialog = false
    },
    confirm() {
      this.$refs.updateHydrologyForm.validate(async isOK => {
        if (isOK) {
          await updateHydrology(this.updateHydrologyForm)
          await this.getHydrology()
          this.closeDialog();
          this.$message.success("修改成功！")
        }
      })
    }
  },

  created() {
    this.getHydrology()
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 改为顶部对齐 */
  min-height: 500px;
  background-color: #f3f3f3;
}

.app-container {
  text-align: center;
}

.data-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  margin-top: 20px;
}

.data-item {
  margin: 10px;
  padding: 15px;
  background-color: #fff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  min-width: 200px;
  max-width: 300px;
}

.btn-edit {
  margin-top: 20px;
  margin-bottom: 20px;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.modal {
  display: none;
  position: fixed;
  z-index: 9999;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  max-width: 400px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
}

.close {
  position: absolute;
  top: 10px;
  right: 15px;
  font-size: 24px;
  cursor: pointer;
}

.input-group {
  margin-bottom: 15px;
}

.btn-save {
  margin-top: 15px;
  padding: 10px 20px;
  background-color: #28a745;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
