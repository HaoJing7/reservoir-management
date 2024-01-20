import layout from '@/layout'
export default {
  path: '/employee',
  component: layout,
  children: [{
    path: '',
    name: 'employee',
    component: () => import('@/views/employee'),
    meta: {
      title: '员工',
      icon: 'people'
    }
  }]
}
