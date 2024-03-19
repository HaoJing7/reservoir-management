<template>
  <div class="container">
    <div class="app-container">
      <el-container>
        <!--电站名称列表-->
        <el-aside width="200px" style="background-color: white;height: 90vh;border-right: inset">
          <el-table
            ref="stationList"
            :data="stationList"
            highlight-current-row
            @current-change="handleCurrentChange"
            style="width: 100%">
            <el-table-column
              type="index"
              width="50">
            </el-table-column>
            <el-table-column
              property="powerStationName"
              label="电站名称"
              width="140">
            </el-table-column>
          </el-table>
        </el-aside>


        <!--所选电站的数据-->
        <el-main style="background-color: white;height: 90vh;padding: 5px">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <h2>{{ currentStation.powerStationName }}</h2>
            </div>
            <!--展示数据-->
            <el-row>
              <!-- Other Data -->
              <el-col :span="24">
                <el-table :data="[runningData]">
                  <el-table-column label="日期">
                    <template slot-scope="scope">{{ scope.row.date }}</template>
                  </el-table-column>
                  <el-table-column label="水库ID">
                    <template slot-scope="scope">{{ scope.row.id }}</template>
                  </el-table-column>
                  <el-table-column label="进流流量(m³/s)">
                    <template slot-scope="scope">{{ scope.row.inflowFlowRate }}</template>
                  </el-table-column>
                  <el-table-column label="出流流量(m³/s)">
                    <template slot-scope="scope">{{ scope.row.outflowFlowRate }}</template>
                  </el-table-column>
                  <el-table-column label="压力(Pa)">
                    <template slot-scope="scope">{{ scope.row.pressure }}</template>
                  </el-table-column>
                  <el-table-column label="转速(rpm)">
                    <template slot-scope="scope">{{ scope.row.speed }}</template>
                  </el-table-column>
                  <el-table-column label="电网电压(V)">
                    <template slot-scope="scope">{{ scope.row.voltage }}</template>
                  </el-table-column>
                  <el-table-column label="电网频率(Hz)">
                    <template slot-scope="scope">{{ scope.row.frequency }}</template>
                  </el-table-column>
                  <el-table-column label="电网负荷(kW)">
                    <template slot-scope="scope">{{ scope.row.gridLoad }}</template>
                  </el-table-column>
                </el-table>
                <p style="font-size: 12px; font-weight: bold;">
                  **由于无法测量真实发电站数据，上述数据均为模拟生成
                </p>
              </el-col>
            </el-row>
            <!--图表数据-->
            <div class="chart">
              <div ref="powerGenerationChart" style="width: 100%;height: 100%"></div>
            </div>
            <div class="chart">
              <div ref="waterLevelChart" style="width: 100%;height: 100%"></div>
            </div>
          </el-card>
        </el-main>

      </el-container>
    </div>
  </div>
</template>
<script>
import {getAllStationList, getRunningData} from "@/api/station";
import * as echarts from 'echarts';

export default {
  name: 'Realtime',
  data() {
    return {
      // 所有电站信息
      stationList: [],
      // 当前的电站
      currentStation: {},
      // 模拟的运行数据
      runningData: {
        date: '',
        powerGeneration: [],
        waterLevel: [],
        inflowFlowRate: '',
        outflowFlowRate: '',
        pressure: '',
        speed: '',
        voltage: '',
        frequency: '',
        gridLoad: ''
      },
    }
  },

  mounted() {
    this.power = echarts.init(this.$refs.powerGenerationChart, null, {
      width: 800,
      height: 300
    })
    this.water = echarts.init(this.$refs.waterLevelChart, null, {
      width: 800,
      height: 300
    })
  },

  // 监听器
  watch: {
    // 监听runningData的变化
    runningData() {
      this.renderPowerGenerationChart();
      this.renderWaterLevelChart();
    }
  },

  methods: {
    async getAllStationList() {
      this.stationList = await getAllStationList()
    },
    // 当前选择的电站发生变化
    handleCurrentChange(val) {
      this.currentStation = val
      this.getStationData()
    },
    // 获取电站运行数据
    async getStationData() {
      // 获取模拟的数据
      this.runningData = await getRunningData(this.currentStation.powerStationId)
      // 获取当前时间戳
      const currentTimeStamp = Date.now();
      // 创建一个Date对象，使用当前时间戳
      const currentDate = new Date(currentTimeStamp);
      // 减去一天的毫秒数（86400000毫秒）
      const yesterdayTimeStamp = currentTimeStamp - (86400000);
      // 创建一个新的Date对象，使用昨日时间戳
      const yesterdayDate = new Date(yesterdayTimeStamp);
      // 使用Date对象的方法获取昨日的年、月、日
      const yesterdayYear = yesterdayDate.getFullYear();
      const yesterdayMonth = yesterdayDate.getMonth() + 1; // 月份从0开始，所以要+1
      const yesterdayDay = yesterdayDate.getDate();
      // 构建昨日的日期字符串
      this.runningData.date = `${yesterdayYear}-${yesterdayMonth}-${yesterdayDay}`
    },

    // 实现数据展示
    renderPowerGenerationChart() {
      const {powerGeneration} = this.runningData;

      const xAxisData = Array.from({length: 24}, (_, index) => index);
      const option = {
        tooltip: {
          trigger: 'axis',
          formatter: 'Power Generation: {c} kWh',
        },
        title: {
          text: '昨日发电量变化折线图'
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          name: '时间'
        },
        yAxis: {
          type: 'value',
          name: '发电量（kWh）'
        },
        series: [
          {
            name: 'Power Generation',
            type: 'line',
            data: powerGeneration,
          },
        ],
      };
      this.power.setOption(option);
      console.log(powerGeneration)
    },

    renderWaterLevelChart() {
      const {waterLevel} = this.runningData;
      const xAxisData = Array.from({length: 24}, (_, index) => index);

      const option = {
        tooltip: {
          trigger: 'axis',
          formatter: 'Water Level: {c} m',
        },
        title: {
          text: '昨日水位变化折线图'
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          name: '时间'
        },
        yAxis: {
          type: 'value',
          name: '水位（m）'
        },
        series: [
          {
            name: 'Water Level',
            type: 'line',
            data: waterLevel,
          },
        ],
      };
      this.water.setOption(option);
      console.log(waterLevel)
    },

  },
  async created() {
    // 初始化的时候要获取电站列表
    await this.getAllStationList()
    // 获取第一个电站的运行数据
    this.currentStation = this.stationList[0];
    await this.getStationData()
  },
}
</script>
<style>
.el-card__header {
  padding: 5px 15px;
}
</style>
