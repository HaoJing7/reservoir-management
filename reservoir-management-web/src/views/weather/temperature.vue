<template>
  <div class="container">
    <div class="app-container">
      <div>
        <el-date-picker
          class="pickerStyle"
          v-model="selectedTime"
          type="month"
          placeholder="选择月份"
          style="margin: 5px 5px 20px 5px"
          :picker-options="pickerOptions">
        </el-date-picker>
        <el-button type="primary" round size="mini" @click="click">查询</el-button>
      </div>
      <!--气温图表-->
      <div class="chart">
        <div ref="temperatureChart" style="width: 100%;height: 100%"></div>
      </div>
      <table class="temperature-table">
        <tr>
          <td class="label">最高温平均值:</td>
          <td>{{ maxAverage }}</td>
          <td>℃</td>
          <td class="label">最低温平均值:</td>
          <td>{{ minAverage }}</td>
          <td>℃</td>
          <td class="label">月最高温:</td>
          <td>{{ maxMaxTemperature }}</td>
          <td>℃</td>
          <td class="label">月最低温:</td>
          <td>{{ minMinTemperature }}</td>
          <td>℃</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import {queryTemperature} from "@/api/weather";
import * as echarts from 'echarts';

export default {
  data() {
    return {
      selectedTime: '', // 修改为 selectedTime
      selectedMonth: '',
      selectedYear: '',
      maxTemperature: [],
      minTemperature: [],
      maxAverage: '', // 最高温平均值
      minAverage: '', // 最低温平均值
      maxMaxTemperature: '', // 月最高温
      minMinTemperature: '', // 月最低温
    }
  },

  mounted() {
    this.temperature = echarts.init(this.$refs.temperatureChart, null, {
      width: 1200,
      height: 500
    })
  },

  watch: {
    // minMinTemperature
    maxTemperature() {
      console.log(this.maxTemperature)
      // 如果minMinTemperature变化了就代表重新查询了一次，要进行渲染
      this.renderTemperatureChart()
    }
  },

  methods: {
    // 点击按钮
    click() {
      this.selectedMonth = this.selectedTime.getMonth() + 1;
      this.selectedYear = this.selectedTime.getFullYear();
      this.queryData()
    },

    // 查询数据
    async queryData() {
      if (this.selectedMonth !== '') {
        const result = await queryTemperature(this.selectedYear, this.selectedMonth);
        // 把数据存入
        this.maxTemperature = result.maxTemperature
        this.minTemperature = result.minTemperature
        // 处理参数数据
        let sumMax = 0;
        let sumMin = 0;
        this.maxMaxTemperature = -100;
        this.minMinTemperature = 100;

        this.maxTemperature.forEach(temp => {
          sumMax += temp;
          this.maxMaxTemperature = Math.max(temp, this.maxMaxTemperature);
        });
        this.maxAverage = (sumMax / this.maxTemperature.length).toFixed(2);
        this.minTemperature.forEach(temp => {
          sumMin += temp;
          this.minMinTemperature = Math.min(temp, this.minMinTemperature);
        });
        this.minAverage = (sumMin / this.minTemperature.length).toFixed(2);
      } else {
        // 没有选择要查询的月份
        this.$message.warning('请选择要查询的月份')
      }
    },

    // 渲染气温图表
    renderTemperatureChart() {
      const xAxisData = Array.from({length: this.maxTemperature.length}, (_, index) => index + 1);

      const option = {
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            let tooltipText = params[0].name + '<br/>'; // X轴日期
            params.forEach(function (item) {
              tooltipText += item.seriesName + ': ' + item.value + ' ℃<br/>';
            });
            return tooltipText;
          },
        },
        title: {
          text: this.selectedYear + '年' + this.selectedMonth +'月气温日变化图表',
        },
        xAxis: {
          type: 'category',
          data: xAxisData,
          name: '日期',
        },
        yAxis: {
          type: 'value',
          name: '温度（℃）',
        },
        series: [
          {
            name: 'Max Temperature',
            type: 'line',
            data: this.maxTemperature,
          }, {
            name: 'Min Temperature',
            type: 'line',
            data: this.minTemperature,
          },
        ],
      };
      this.temperature.setOption(option);
    }
  },

  async created() {
    // 初始化的时候要获取一次数据默认为上个月
    const today = new Date(); // 获取当前日期
    if (today.getMonth() === 0) { // 如果当前月份为一月份
      this.selectedYear = today.getFullYear() - 1; // 上个月的年份为去年
      this.selectedMonth = 12; // 上个月的月份为十二月
    } else {
      this.selectedYear = today.getFullYear(); // 上个月的年份为当前年份
      this.selectedMonth = today.getMonth(); // 上个月的月份为当前月份减1
    }
    await this.queryData();
  },

  computed: {
    pickerOptions() {
      const today = new Date();
      const currentYear = today.getFullYear();
      const currentMonth = today.getMonth() + 1; // 因为月份是从0开始的，所以要加1
      const disabledDate = time => {
        const year = time.getFullYear();
        const month = time.getMonth() + 1;
        // 禁止选择当前月份及之后的所有月份
        return (
          (year > currentYear) || // 禁止选择当前月份之后的所有年份的所有月份
          (year === currentYear && month >= currentMonth) || // 禁止选择当前月份及之后的月份
          (year < currentYear - 3)
        );
      };
      return {
        disabledDate,
      };
    },

  },
};
</script>

<style>
.label {
  font-weight: bold;
  font-size: 14px;
  color: #333;
}
</style>
