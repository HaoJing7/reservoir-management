<template>
  <div class="container">
    <div class="log-container">
      <h2>调度日志下载</h2>
      <div class="log-list">
        <div class="log-item" v-for="file in fileList" :key="file.name">
          <div class="log-info">
            <div class="log-name">{{ file.name }}</div>
            <div class="log-size">{{ formatFileSize(file.size) }}</div>
          </div>
          <div class="log-actions">
            <a :href="file.url" class="download-btn">下载</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LogDownload',
  data() {
    return {
      fileList: [
        {
          name: '2024-6-24-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 18
        },{
          name: '2024-6-23-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 30
        },{
          name: '2024-6-22-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 25
        },{
          name: '2024-6-21-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 21
        },{
          name: '2024-6-20-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 16
        },{
          name: '2024-6-19-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 18
        },{
          name: '2024-6-18-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 17
        },{
          name: '2024-6-17-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 19
        },{
          name: '2024-6-16-21h-22m-03s',
          url: 'https://tanhao-bucket.oss-cn-guangzhou.aliyuncs.com/%E5%8F%82%E6%95%B0%E6%A8%A1%E6%9D%BF.xlsx',
          size: 1024 * 25
        },

      ]
    }
  },
  methods: {
    formatFileSize(size) {
      if (size < 1024) {
        return `${size} B`
      } else if (size < 1024 * 1024) {
        return `${(size / 1024).toFixed(2)} KB`
      } else {
        return `${(size / (1024 * 1024)).toFixed(2)} MB`
      }
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
  background-color: #f5f5f5;
}

.log-container {
  width: 800px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 40px;
}

.log-list {
  margin-top: 20px;
}

.log-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #f0f0f0;
}

.log-info {
  display: flex;
  align-items: center;
}

.log-name {
  font-size: 16px;
  font-weight: bold;
  margin-right: 16px;
}

.log-size {
  font-size: 14px;
  color: #8c8c8c;
}

.download-btn {
  display: inline-block;
  background-color: #1890ff;
  color: #fff;
  padding: 8px 16px;
  border-radius: 4px;
  text-decoration: none;
  transition: background-color 0.3s;
}

.download-btn:hover {
  background-color: #40a9ff;
}
</style>
