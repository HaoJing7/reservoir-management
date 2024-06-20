<template>
  <div class="container">
    <div class="app-container">
      <h1 class="title">实时气象数据</h1>
      <div class="weather-info">
        <div class="weather-icon" :class="'wi wi-yahoo-' + realTime.iconDay"></div>
        <div class="weather-description">{{ realTime.textDay }}</div>
        &emsp;
        <div class="temp-max">{{ realTime.tempMin }}°C ~ {{ realTime.tempMax }}°C</div>
      </div>

      <div class="sunrise-sunset">
        <div class="sunrise">
          <svg-icon icon-class="sunrise"></svg-icon>
          <div class="time">日出时间：{{ realTime.sunrise }}</div>
        </div>
        <div class="sunset">
          <svg-icon icon-class="sundown"></svg-icon>
          <div class="time">日落时间：{{ realTime.sunset }}</div>
        </div>
      </div>

      <div class="moon-phase">
        <svg-icon icon-class="moon"></svg-icon>
        <div class="moon-description">月相：{{ realTime.moonPhase }}</div>
      </div>

      <div class="wind">
        <div class="wind-day">
          <div class="wind-info">
            <div class="wind-direction" style="font-size: 18px;margin-bottom: 2px">白天风向：{{
                realTime.windDirDay
              }}
            </div>
            <div class="wind-scale" style="font-size: 18px;margin-bottom: 2px">白天风力：{{
                realTime.windScaleDay
              }}
            </div>
            <div class="wind-speed" style="font-size: 18px;margin-bottom: 2px">白天风速：{{
                realTime.windSpeedDay
              }}m/s
            </div>
          </div>
        </div>
        <div class="wind-night">
          <div class="wind-info">
            <div class="wind-direction" style="font-size: 18px;margin-bottom: 2px">夜晚风向：{{
                realTime.windDirNight
              }}
            </div>
            <div class="wind-scale" style="font-size: 18px;margin-bottom: 2px">夜晚风力：{{
                realTime.windScaleNight
              }}
            </div>
            <div class="wind-speed" style="font-size: 18px;margin-bottom: 2px">夜晚风速：{{
                realTime.windSpeedNight
              }}m/s
            </div>
          </div>
        </div>
      </div>

      <div class="additional-info">
        <div class="info-item">
          <svg-icon icon-class="precipitation"></svg-icon>
          <div class="info-text">降水量：{{ realTime.precip }}mm</div>
        </div>
        &emsp;&emsp;
        <div class="info-item">
          <svg-icon icon-class="Ultraviolet"></svg-icon>
          <div class="info-text">紫外线指数：{{ realTime.uvIndex }}</div>
        </div>
        &emsp;&emsp;
        <div class="info-item">
          <svg-icon icon-class="Humidity"></svg-icon>
          <div class="info-text">湿度：{{ realTime.humidity }}%</div>
        </div>
        &emsp;&emsp;
        <div class="info-item">
          <svg-icon icon-class="pressure"></svg-icon>
          <div class="info-text">气压：{{ realTime.pressure }}hPa</div>
        </div>
        &emsp;&emsp;
        <div class="info-item">
          <svg-icon icon-class="Visibility"></svg-icon>
          <div class="info-text">能见度：{{ realTime.vis }}km</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getRealTime} from "@/api/weather";

export default {
  name: "Realtime",
  data() {
    return {
      realTime: {}
    };
  },
  methods: {
    async getRealTime() {
      this.realTime = await getRealTime();
    }
  },
  created() {
    this.getRealTime();
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 90vh;
  background-color: #f1f1f1;
}

.app-container {
  background-color: #fff;
  padding: 20px;
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.1);
  text-align: center;
}

.title {
  font-size: 38px;
  font-family: Arial, sans-serif;
  margin-bottom: 20px;
}

.weather-info {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.weather-icon {
  font-size: 100px;
  margin-right: 10px;
}

.temp-max,
.temp-min {
  font-size: 35px;
  font-family: Arial, sans-serif;
  margin-right: 10px;
}

.weather-description {
  font-size: 24px;
  font-family: Arial, sans-serif;
}

.sunrise-sunset {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-bottom: 24px;
}

.sunrise,
.sunset {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.sunrise i,
.sunset i {
  font-size: 32px;
}

.sunrise .time,
.sunset .time {
  font-size: 18px;
}

.moon-phase {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.moon-icon {
  font-size: 64px;
  margin-right: 10px;
}

.moon-description {
  font-size: 20px;
  font-family: Arial, sans-serif;
}

.wind {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-bottom: 20px;
}

.wind-day,
.wind-night {
  display: flex;
  align-items: center;
}

.wind-day i,
.wind-night i {
  font-size: 32px;
}

.wind-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 10px;
}

.wind-speed {
  font-size: 18px;
}

.additional-info {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin-bottom: 20px;
}

.info-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.info-item i {
  font-size: 32px;
}

.info-text {
  font-size: 20px;
  font-family: Arial, sans-serif;
}

.sunrise-sunset {
  display: flex;
  align-items: center;
}

.sunrise,
.sunset {
  display: flex;
  align-items: center;
  margin-right: 10px;
}

.svg-icon {
  width: 28px; /* 根据图标尺寸调整 */
  height: 28px; /* 根据图标尺寸调整 */
  margin-right: 5px;
}

.time {
  font-size: 18px;
}

</style>
