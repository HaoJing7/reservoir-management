import layout from '@/layout'
export default {
  // 路由信息
  path: '/station',
  name: 'station',
  component: layout, // 一级路由
  meta: {
    // 路由元信息 存储数据的
    icon: 'station', // 图标
    title: '电站信息' // 标题
  },
  redirect: '/station/list',
  children: [{
    path: 'list', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'list', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/station/list.vue'),
    meta: {
      title: '电站列表'
    }
  },{
    path: 'unit', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'unit', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/station/unit.vue'),
    meta: {
      title: '机组列表'
    }
  }, {
    path: 'realtime', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'realtime', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/station/realtime.vue'),
    meta: {
      title: '运行数据'
    }
  }]
}
