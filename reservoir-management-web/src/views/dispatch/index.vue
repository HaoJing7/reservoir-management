<template>
  <div class="container">
    <div class="app-container">
      <div class="header">
        <div class="title">水库多目标优化调度</div>
        <div class="download-btn">
          <el-button type="primary" icon="el-icon-download" @click="downloadFile">下载附件</el-button>
        </div>
      </div>
      <div class="content">
        <div class="section">
          <div class="label">请选择优化算法:</div>
          <div class="input">
            <el-select v-model="selectedAlgorithm" placeholder="请选择优化算法">
              <el-option
                v-for="item in algorithms"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </div>
        </div>
        <div class="section">
          <div class="label">请选择粒子数量:</div>
          <div class="input">
            <el-input-number v-model="particleCount" :min="30" :max="200" label="粒子数量"></el-input-number>
          </div>
        </div>
        <div class="section">
          <div class="label">请选择迭代次数:</div>
          <div class="input">
            <el-input-number v-model="iterationCount" :min="100" :max="300" label="迭代次数"></el-input-number>
          </div>
        </div>
        <div class="section">
          <div class="label">上传参数附件:</div>
          <div class="input">
            <el-upload
              drag
              class="upload-btn"
              :file-list="fileList"
              :show-file-list="true"
              action=""
              :before-upload="beforeFileUpload"
              :on-change="handleFileChange"
              accept=".xlsx,.xls">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
          </div>
        </div>
        <div class="section">
          <div class="label">下载结果文件:</div>
          <div class="input">
            <el-button type="primary" :disabled="resultFileUrl === ''" @click="downloadResultFile">下载结果文件
            </el-button>
          </div>
        </div>
        <div class="footer">
          <el-button type="primary" class="execute-btn" :loading="isExecuting" @click="executeAlgorithm">执行算法
          </el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { executeAlgorithm } from '@/api/dispatch'

Vue.use(ElementUI)

export default {
  name: 'Dispatch',
  data() {
    return {
      selectedAlgorithm: '',
      particleCount: 30,
      iterationCount: 100,
      algorithms: [
        { value: 0, label: 'MOEA/D' },
        { value: 1, label: 'NSGA-II' },
      ],
      isExecuting: false,
      resultFileUrl: '',
      file: null,
      fileList: []
    }
  },
  methods: {
    beforeFileUpload(file) {
      this.file = file;
      this.fileList.push({ name: file.name, status: 'ready', raw: file });
      return false; // Prevent default upload behavior
    },
    handleFileChange(file, fileList) {
      console.log('File changed:', file, fileList);
      this.fileList = fileList;
      this.file = file.raw;
    },
    downloadFile() {
      const url = 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx';
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', '参数模板.xlsx');
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
    downloadResultFile() {
      const url = this.resultFileUrl;
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', '优化结果.xlsx');
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    },
    async executeAlgorithm() {
      if (!this.file) {
        this.$message.error('请先上传文件');
        return;
      }
      this.isExecuting = true;
      const formData = new FormData();
      formData.append('file', this.file);
      formData.append('algorithm', this.selectedAlgorithm);
      formData.append('particleCount', this.particleCount);
      formData.append('iterationCount', this.iterationCount);

      this.resultFileUrl = await executeAlgorithm(formData)
      this.isExecuting = false
      console.log(this.resultFileUrl)
      this.$message.success("算法调用完成！")
    }
  }
}
</script>







<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.app-container {
  width: 80%;
  max-width: 800px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 2rem;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.5rem;
}

.download-btn {
  margin-left: 1rem;
}

.content {
  display: flex;
  flex-direction: column;
}

.section {
  display: flex;
  align-items: center;
  margin-bottom: 1rem;
}

.label {
  width: 30%;
  font-weight: bold;
}

.input {
  width: 70%;
}

.footer {
  display: flex;
  justify-content: flex-end;
  margin-top: 1.5rem;
}

.execute-btn {
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 0.5rem 1rem;
  cursor: pointer;
}

.execute-btn:hover {
  background-color: #0056b3;
}

.execute-btn.is-loading {
  position: relative;
  padding-right: 2.5rem;
}

.execute-btn.is-loading::after {
  content: '';
  position: absolute;
  top: 50%;
  right: 0.5rem;
  transform: translateY(-50%);
  width: 1rem;
  height: 1rem;
  border: 2px solid #fff;
  border-top-color: transparent;
  border-radius: 50%;
  animation: spin 0.5s linear infinite;
}

@keyframes spin {
  0% {
    transform: translateY(-50%) rotate(0deg);
  }
  100% {
    transform: translateY(-50%) rotate(360deg);
  }
}
</style>
