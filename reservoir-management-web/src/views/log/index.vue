<template>
  <div class="container">
    <div class="log-container">
      <h2>调度优化日志查看</h2>
      <div class="sorting-controls">
        <button @click="sortLogs('asc')">按文件名升序</button>
        <button @click="sortLogs('desc')">按文件名降序</button>
      </div>
      <div class="log-list">
        <div class="log-item" v-for="file in fileList" :key="file.fileName">
          <div class="log-info">
            <div class="log-name">{{ file.fileName }}</div>
          </div>
          <div class="log-actions">
            <a :href="file.fileUrl" class="download-btn">下载</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getLogList } from "@/api/log";

export default {
  name: 'LogDownload',
  data() {
    return {
      fileList: [],
      sortOrder: 'asc',
    };
  },
  methods: {
    async getLogList() {
      this.fileList = await getLogList();
      this.sortLogs(this.sortOrder);
    },
    sortLogs(order) {
      this.sortOrder = order;
      this.fileList.sort((a, b) => {
        if (order === 'asc') {
          return a.fileName.localeCompare(b.fileName);
        } else {
          return b.fileName.localeCompare(a.fileName);
        }
      });
    },
  },
  created() {
    this.getLogList();
  },
};
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
  width: 80%;
  max-width: 800px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  overflow-y: auto;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.sorting-controls {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.sorting-controls button {
  background-color: #1890ff;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 0 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.sorting-controls button:hover {
  background-color: #40a9ff;
}

.log-list {
  margin-top: 10px;
  max-height: 400px;
  overflow-y: auto;
}

.log-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
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
