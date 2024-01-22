import layout from '@/layout'
export default {
  // 路由信息
  path: '/reservoir',
  name: 'reservoir',
  component: layout, // 一级路由
  meta: {
    // 路由元信息 存储数据的
    icon: 'reservoir', // 图标
    title: '水库信息' // 标题
  },
  redirect: '/reservoir/list',
  children: [{
    path: 'list', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'list', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/reservoir/list.vue'),
    meta: {
      title: '水库列表'
    }
  },{
    path: 'hydrology', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'hydrology', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/reservoir/hydrology.vue'),
    meta: {
      title: '水文数据'
    }
  }, {
    path: 'realtime', // 二级路由地址为空时 表示 /weather 显示一级路由 + 二级路由
    name: 'realtime', // 可以用来跳转 也可以标记路由
    component: () => import('@/views/reservoir/realtime.vue'),
    meta: {
      title: '流域实况'
    }
  }]
}
