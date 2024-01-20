import layout from '@/layout'
export default {
  path: '/salary',
  component: layout,
  children: [{
    path: '',
    name: 'salary',
    component: () => import('@/views/salary'),
    meta: {
      title: '工资',
      icon: 'money'
    }
  }]
}
