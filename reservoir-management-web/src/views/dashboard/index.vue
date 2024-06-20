<template>
  <div class="dashboard">
    <div class="container">
      <!-- Left content -->
      <div class="left">
        <div class="panel">
          <!-- User Info -->
          <div class="user-info">
            <img v-if="avatar" class="avatar" :src="avatar" alt="">
            <span v-else class="username">{{ name?.charAt(0) }}</span>
            <div class="company-info">
              <div class="title">
                数字化水库管理系统
                <span>测试版</span>
              </div>
              <div class="depart">{{ name }} ｜ 管理员</div>
            </div>
          </div>
          <!-- Todo List -->
          <div class="todo-list">
            <div class="todo-item">
              <span>水库总数</span>
              <count-to :start-val="0" :end-val="homeData.reservoir" :duration="1000"/>
            </div>
            <div class="todo-item">
              <span>电站总数</span>
              <count-to :start-val="0" :end-val="homeData.station" :duration="1000"/>
            </div>
            <div class="todo-item">
              <span>员工总数</span>
              <count-to :start-val="0" :end-val="homeData.employee" :duration="1000"/>
            </div>
            <div class="todo-item">
              <span>申请待处理</span>
              <count-to :start-val="0" :end-val="homeData.waiting" :duration="1000"/>
            </div>
            <div class="todo-item">
              <span>申请处理中</span>
              <count-to :start-val="0" :end-val="homeData.processing" :duration="1000"/>
            </div>
          </div>
        </div>
        <!-- Quick Entry -->
        <div class="panel">
          <div class="panel-title">快捷入口</div>
          <div class="quick-entry">
            <div class="entry-item" @click="$router.push(`/application`)">
              <div class="entry-icon approval"/>
              <span>员工申请</span>
            </div>
            <div class="entry-item" @click="$router.push(`/employee`)">
              <div class="entry-icon role"/>
              <span>员工管理</span>
            </div>
            <div class="entry-item" @click="$router.push(`/station/list`)">
              <div class="entry-icon social"/>
              <span>电站列表</span>
            </div>
            <div class="entry-item" @click="$router.push(`/reservoir/list`)">
              <div class="entry-icon departMent"/>
              <span>水库列表</span>
            </div>
            <div class="entry-item" @click="$router.push(`/dispatch`)">
              <div class="entry-icon bpm"/>
              <span>发电调度</span>
            </div>
          </div>
        </div>
        <!-- Chart Data -->
        <div class="panel">
          <div class="panel-title">{{ new Date().getFullYear() }}年发电量折线图</div>
          <div class="chart-container">
            <div class="chart-info">
              <div class="info-main">
                <span>发电总量(万千瓦时)</span>
                <count-to :start-val="0" :end-val="homeData.sumPower" :duration="1000"/>
              </div>
              <div class="info-list">
                <div class="info-list-item">
                  <span>平均发电量</span>
                  <count-to :start-val="0" :end-val="homeData.avgPower" :duration="1000"/>
                </div>
                <div class="info-list-item">
                  <span>最大发电量</span>
                  <count-to :start-val="0" :end-val="homeData.maxPower" :duration="1000"/>
                </div>
                <div class="info-list-item">
                  <span>最小发电量</span>
                  <count-to :start-val="0" :end-val="homeData.minPower" :duration="1000"/>
                </div>
              </div>
            </div>
            <div class="chart">
              <!-- Chart -->
              <div ref="power" style="width: 100%; height: 300px;"></div>
            </div>
          </div>
        </div>
      </div>
      <!-- Right content -->
      <div class="right">
        <!-- Help Links -->
        <div class="panel">
          <div class="help">
            <div class="help-left">
              <div class="panel-title">帮助链接</div>
              <div class="help-list">
                <div class="help-block">
                  <i class="icon-entry"/>
                  入门指南
                </div>
                <div class="help-block">
                  <i class="icon-help"/>
                  在线帮助手册
                </div>
                <div class="help-block">
                  <i class="icon-support"/>
                  联系技术支持
                </div>
                <div class="help-block">
                  <i class="icon-add"/>
                  添加链接
                </div>
              </div>
            </div>
            <div class="help-right">
              <div class="calendar">
                <el-calendar/>
              </div>
            </div>
          </div>
        </div>
        <div class="panel">
          <div class="panel-title-container">
            <div class="panel-title" style="font-size: 20px;">
              待办
            </div>
            <el-button @click="showForm = !showForm" class="add-button" type="text">
              {{ showForm ? '取消' : '添加' }}
            </el-button>
          </div>
          <div v-if="showForm" class="add-todo-form">
            <el-form id="text" :model="textForm">
              <el-form-item label="内容" label-width="150px">
                <el-input type="textarea" v-model="textForm.content" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div class="form-actions">
              <el-button @click="showForm = false; textForm = {}">取消</el-button>
              <el-button type="primary" @click="addRecord()">确定</el-button>
            </div>
          </div>
          <div class="information-list">
            <div v-for="(item, index) in list" :key="item.id" class="information-list-item">
              <img src="@/assets/common/img.jpeg" alt="">
              <div class="memo-content">
                <p>{{ item.content }}</p>
                <p>{{ item.createTime }}</p>
              </div>
              <el-button @click="deleteRecord(item.id)" type="text" size="mini" class="delete-button">删除</el-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CountTo from 'vue-count-to'
import {mapGetters} from 'vuex'
import * as echarts from 'echarts/core'
import {LineChart} from 'echarts/charts'
import {GridComponent, TooltipComponent} from 'echarts/components'
import {CanvasRenderer} from 'echarts/renderers'
import {addRecord, getData, getList, deleteRecord, deleteRecordById} from "@/api/dashboard";

echarts.use([
  LineChart,
  GridComponent,
  TooltipComponent,
  CanvasRenderer
])

export default {
  components: {
    CountTo
  },
  data() {
    return {
      homeData: {
        reservoir: 10,
        station: 20,
        employee: 50,
        waiting: 80,
        processing: 100,
        powerData: {
          xAxis: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          yAxis: [50, 78, 90, 100, 89, 106, 60, 99, 102, 60, 37, 40]
        },
        sumPower: 0,
        maxPower: 0,
        avgPower: 0,
        minPower: 0,
      },
      list: [],
      textForm: {
        content: '',
        createTime: '',
      },
      showForm: false
    }
  },
  computed: {
    ...mapGetters(['avatar', 'name', 'company', 'departmentName'])
  },
  watch: {
    homeData: {
      handler() {
        this.renderChart()
      },
      deep: true
    }
  },
  created() {
    this.getData()
    this.getList()
  },
  mounted() {
    this.power = echarts.init(this.$refs.power)
    this.renderChart()
  },
  methods: {
    renderChart() {
      this.power.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.homeData.powerData.xAxis
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.homeData.powerData.yAxis,
            type: 'line',
            areaStyle: {
              color: '#04c9be'
            },
            lineStyle: {
              color: '#04c9be'
            }
          }
        ]
      })
    },
    async getData() {
      const {
        reservoir,
        station,
        employee,
        waiting,
        processing,
        list,
        sumPower,
        avgPower,
        minPower,
        maxPower
      } = await getData()
      this.homeData.reservoir = reservoir
      this.homeData.station = station
      this.homeData.employee = employee
      this.homeData.waiting = waiting
      this.homeData.processing = processing
      this.homeData.powerData.yAxis = list
      this.homeData.sumPower = sumPower
      this.homeData.avgPower = avgPower
      this.homeData.minPower = minPower
      this.homeData.maxPower = maxPower
    },
    async getList() {
      this.list = await getList()
    },
    async addRecord() {
      if (this.list.length >= 10) {
        this.$message.warning('最多只能添加十条备忘录');
        return;
      }
      this.textForm.createTime = this.getFormattedDateTime();
      await addRecord(this.textForm);
      await this.getList();
      this.showForm = false;
      this.textForm = {};
    },
    deleteRecord(id) {
      deleteRecordById(id).then(() => {
        this.$message.success('删除成功');
        this.getList();
      }).catch(() => {
        this.$message.error('删除失败');
      });
    },
    getFormattedDateTime() {
      const now = new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, '0');
      const day = String(now.getDate()).padStart(2, '0');
      const hours = String(now.getHours()).padStart(2, '0');
      const minutes = String(now.getMinutes()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
  }
}
</script>

<style scoped>
.information-list-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
  padding-right: 20px;
}

.memo-content {
  flex: 1; /* 占据剩余空间 */
}

.delete-button {
  margin-left: auto; /* 将删除按钮对齐到右侧 */
}

.add-button-container {
  margin-bottom: 10px; /* 与待办列表分开一定距离 */
}

.add-todo-form {
  margin-bottom: 20px; /* 与待办列表分开一定距离 */
}

.panel-title-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.add-button {
  float: right;
  padding: 3px 0;
}
</style>





<style scoped lang="scss">

.dashboard {
  background: #f5f6f8;
  width: 100%;
  min-height: calc(100vh - 80px);

  ::v-deep .el-calendar-day {
    height: 40px;
  }

  ::v-deep .el-calendar-table__row td, ::v-deep .el-calendar-table tr td:first-child, ::v-deep .el-calendar-table__row td.prev {
    border: none;
  }

  .date-content {
    height: 40px;
    text-align: center;
    line-height: 40px;
    font-size: 14px;
  }

  .date-content .rest {
    color: #fff;
    border-radius: 50%;
    background: rgb(250, 124, 77);
    width: 20px;
    height: 20px;
    line-height: 20px;
    display: inline-block;
    font-size: 12px;
    margin-left: 10px;
  }

  .date-content .text {
    width: 20px;
    height: 20px;
    line-height: 20px;
    display: inline-block;

  }

  ::v-deep .el-calendar-table td.is-selected .text {
    background: #409eff;
    color: #fff;
    border-radius: 50%;
  }

  .container {
    display: flex;

    .right,
    .left {
      width: 50%;

      .panel {
        margin-left: 8px;
      }

      :nth-child(1) {
        margin-top: 0;
      }
    }

    .panel {
      background-color: #fff;
      margin-top: 8px;
      padding: 20px;

      .panel-title {
        font-size: 16px;
        color: #383c4e;
        font-weight: 500;
      }

      // 用户信息样式
      .user-info {
        display: flex;

        .avatar {
          width: 48px;
          height: 48px;
          border-radius: 12px;
          background-color: #d9d9d9;
          line-height: 48px;
          text-align: center;
        }

        .username {
          width: 30px;
          height: 30px;
          text-align: center;
          line-height: 30px;
          border-radius: 50%;
          background: #04c9be;
          color: #fff;
          margin-right: 4px;
        }

        .company-info {
          margin-left: 10px;
          height: 48px;
          display: flex;
          flex-direction: column;
          justify-content: space-around;

          .title {
            color: #383c4e;
            font-weight: 500;
            font-size: 16px;
            font-family: PingFang SC, PingFang SC-Medium;

            span {
              font-size: 12px;
              background: #f5f6f8;
              text-align: center;
              padding: 2px 8px;
              border-radius: 2px;
              color: #697086;
            }
          }

          .depart {
            font-size: 14px;
            color: #697086;
            font-weight: 400;
          }
        }
      }

      // 代办样式
      .todo-list {
        margin-top: 10px;
        display: flex;
        flex-wrap: wrap;

        .todo-item {
          width: 18%;
          height: 90px;
          display: flex;
          flex-direction: column;
          padding: 10px;
          justify-content: space-around;

          :nth-child(1) {
            color: #697086;
            font-size: 14px;
          }

          :nth-child(2) {
            color: #383c4e;
            font-size: 30px;
            font-weight: 500;
          }
        }
      }

      // 快捷入口
      .quick-entry {
        margin-top: 16px;
        display: flex;

        .entry-item {
          display: flex;
          flex-direction: column;
          align-items: center;
          margin-left: 60px;

          &:nth-child(1) {
            margin-left: 0px;
          }

          .entry-icon {
            width: 40px;
            height: 40px;
            border-radius: 10px;
            background: #f5f6f8;
            background-size: cover;

            &.approval {
              background-image: url('~@/assets/common/approval.png');
            }

            &.social {
              background-image: url('~@/assets/common/social.png');
            }

            &.departMent {
              background-image: url('~@/assets/common/departMent.png');
            }

            &.role {
              background-image: url('~@/assets/common/role.png');
            }

            &.bpm {
              background-image: url('~@/assets/common/bpm.png');
            }
          }

          span {
            color: #697086;
            font-size: 14px;
            margin-top: 8px;
          }
        }
      }

      // 图表数据
      .chart-container {
        display: flex;

        .chart-info {
          width: 240px;
          margin-top: 10px;

          .info-main {
            padding: 10px;
            display: flex;
            flex-direction: column;

            :nth-child(1) {
              font-size: 14px;
              color: #697086;
            }

            :nth-child(2) {
              margin-top: 10px;
              font-size: 30px;
              color: #04c9be;
              font-weight: 500;
            }
          }

          .info-list {
            background: #f5f6f8;
            border-radius: 4px;
            padding: 12px 15px;
            display: flex;
            flex-wrap: wrap;
            align-items: center;

            .info-list-item {
              width: 50%;
              margin-top: 10px;
              display: flex;
              flex-direction: column;

              :nth-child(1) {
                font-size: 14px;
                color: #697086;
              }

              :nth-child(2) {
                margin-top: 10px;
                font-size: 30px;
                color: #383c4e;
                font-weight: 500;
              }
            }
          }
        }

        .chart {
          flex: 1
        }
      }

      // 帮助链接
      .help {
        display: flex;

        .help-left {
          width: 40%;
        }

        .help-right {
          flex: 1;
        }

        .help-list {
          .help-block {
            background: #f5f6f8;
            border-radius: 4px;
            width: 264px;
            height: 54px;
            padding: 17px 10px;
            font-size: 14px;
            color: #697086;
            margin-top: 10px;

            i {
              width: 14px;
              height: 14px;
              display: inline-block;
              background-size: cover;
              vertical-align: middle;
            }

            i.icon-help {
              background-image: url("~@/assets/common/help.png");
            }

            i.icon-support {
              background-image: url("~@/assets/common/support.png");
            }

            i.icon-add {
              background-image: url("~@/assets/common/add.png");
            }

            i.icon-entry {
              background-image: url("~@/assets/common/entry.png");
            }
          }
        }
      }

      // 待办
      .information-list {
        margin-top: 20px;

        .information-list-item {
          display: flex;
          align-items: center;
          margin: 15px 0;

          img {
            width: 40px;
            height: 40px;
            border: 50%;
          }

          .col {
            color: #8a97f8;
          }

          div :nth-child(2) {
            color: #697086;
            font-size: 14px;
          }
        }
      }
    }
  }
}
</style>

