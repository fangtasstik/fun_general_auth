import { createApp } from 'vue'
import router from '@/routes'
import App from '@/App.vue'

import ElementPlus from 'element-plus'
// import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { createPinia } from 'pinia'
import { i18n } from '@/i18n'
import 'element-plus/dist/index.css'
import piniaPersist from 'pinia-plugin-persistedstate'

import './style.css'

const pinia = createPinia()
// introduce pinia persist
pinia.use(piniaPersist)
const app = createApp(App)

app.use(pinia)
app.use(i18n)
app.use(ElementPlus)
Object.entries(ElementPlusIconsVue).forEach(([key, component]) => {
  app.component(key, component)
})
app.use(router)
app.mount('#app')
