import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import Vuetify from 'vuetify'
import'vuetify/dist/vuetify.min.css'
import'@mdi/font/css/materialdesignicons.css'
import'material-design-icons-iconfont/dist/material-design-icons.css'
import store from './store'

import '@/permission'
Vue.config.productionTip = false
const options = {
  namespace: 'pro__',
  name: 'ls',
  storage: 'local'
}
Vue.use(Antd)
Vue.use(Vuetify)

Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})
new Vue({
  vuetify:new Vuetify({
  }),
  router,
  store,
  render: h => h(App)
}).$mount('#app')
