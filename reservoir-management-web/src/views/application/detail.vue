<template>
  <div class="container">
    <div class="app-container">
      申请详情
    </div>
  </div>
</template>
<script>
import {getApplicationDetail} from "@/api/application";

export default {
  name: 'Detail',
  data() {
    return {
      // 申请详情信息
      applicationDetail: {},
    }
  },
  methods: {
    // 获取申请详情
    getApplicationDetail() {
      this.applicationDetail = getApplicationDetail(this.$route.params.id)
    }
  },
  created() {
    this.getApplicationDetail()
  }
}
</script>
