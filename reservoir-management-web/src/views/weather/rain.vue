<template>
  <div class="container">
    <div class="app-container">
      <div>
        <el-date-picker
          v-model="selectedYear"
          type="year"
          placeholder="选择年份"
          style="margin: 5px 5px 20px 5px"
          :picker-options="pickerOptions"
        ></el-date-picker>
        <el-button type="primary" round size="mini" @click="queryData">查询</el-button>
      </div>
      <!--降水总量图表-->
      <div class="chart">
        <div ref="precipitationChart" style="width: 100%;height: 100%"></div>
      </div>
      <table>
        <tr>
          <td class="label">年降水总量:</td>
          <td>{{ sumPrecipitation }}</td>
          <td>(mm)</td>
        </tr>
      </table>
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import {queryPrecipitation} from "@/api/weather";

export default {
  name: 'Rain',
  data() {
    return {
      selectedYear: new Date(), // 选定的年份
      sumPrecipitation: '', // 降水总量
      monthPrecipitation: [],  // 月降水总量数据
      pickerOptions: {
        disabledDate(time) {
          const currentYear = new Date().getFullYear();
          // 禁用将来的年份和过去五年之前的年份
          return time.getFullYear() > currentYear || time.getFullYear() <= currentYear - 5;
        }
      }
    }
  },

  watch: {
    monthPrecipitation() {
      this.renderPrecipitationChart()
    }
  },

  mounted() {
    this.precipitation = echarts.init(this.$refs.precipitationChart, null, {
      width: 1200,
      height: 500
    })
  },

  methods: {
    // 查询降水数据
    async queryData() {
      const fetchedData = await queryPrecipitation(this.selectedYear.getFullYear());
      // 初始化月降水数据为0
      this.monthPrecipitation = Array.from({ length: 12 }, () => 0);
      // 更新已查询到的月降水数据
      fetchedData.forEach((precipitation, monthIndex) => {
        this.monthPrecipitation[monthIndex] = precipitation;
      });
      // 计算年降水总量
      this.sumPrecipitation = this.monthPrecipitation.reduce((total, monthPrecipitation) => total + monthPrecipitation, 0).toFixed(1);
    },


    // 渲染降水图表
    renderPrecipitationChart() {
      const xAxisData = Array.from({length: 12}, (_, index) => index + 1);

      const option = {
        tooltip: {
          trigger: 'axis',
          formatter: '当月降水量为：{c} mm',
        },
        title: {
          text: this.selectedYear.getFullYear() + '年' + '降水总量统计图表',
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          name: '月份',
        },
        yAxis: {
          type: 'value',
          name: '降水量（mm）',
        },
        series: [
          {
            name: '降水量',
            type: 'bar',
            data: this.monthPrecipitation,
          }
        ],
      };
      this.precipitation.setOption(option);
    }
  },

  created() {
    this.selectedYear = new Date();
    this.queryData()
  },
}
</script>
