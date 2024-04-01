import layout from '@/layout'
export default {
  // 路由信息
  path: '/weather',
  name: 'weather',
  component: layout, // 一级路由
  meta: {
    // 路由元信息 存储数据的
    icon: 'weather', // 图标
    title: '气象信息' // 标题
  },
  redirect: '/weather/realtime',
  children: [{
    path: 'realtime', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'realtime', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/weather/realtime.vue'),
    meta: {
      title: '实时气象'
    }
  },{
    path: 'temperature', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'temperature', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/weather/temperature.vue'),
    meta: {
      title: '月气温统计'
    }
  }, {
    path: 'rain', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'rain', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/weather/rain.vue'),
    meta: {
      title: '年降水统计'
    }
  }]
}
