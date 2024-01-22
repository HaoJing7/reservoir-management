import layout from '@/layout'
export default {
  // 路由信息
  path: '/employee',
  name: 'employee',
  component: layout, // 一级路由
  children: [{
    path: '', // 二级路由地址为空时 表示 /employee 显示一级路由 + 二级路由
    component: () => import('@/views/employee'),
    name: 'employee', // 可以用来跳转 也可以标记路由
    meta: {
      // 路由元信息 存储数据的
      icon: 'peoples', // 图标
      title: '员工管理' // 标题
    }
  }]
}
