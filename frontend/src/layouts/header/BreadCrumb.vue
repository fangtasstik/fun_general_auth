<template>
  <el-breadcrumb class="breadcrumb-container" :separator-icon="ArrowRight">
    <el-breadcrumb-item
      v-for="item in tabs"
      :key="item.path"
      :to="{ path: item.path }"
    >
      {{ getTitle(item) }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script lang="ts" setup>
import { ArrowRight } from '@element-plus/icons-vue'
import { useRoute } from 'vue-router'
import { ref, watch } from 'vue'
import type { BreadcrumbItem } from '@/types/route'
import { useI18n } from 'vue-i18n'
import { resolveTitle } from '@/utils/i18n'

const route = useRoute()
const tabs = ref<BreadcrumbItem[]>([])
const { t } = useI18n()
const getBreadCrumb = () => {
  const matched = route.matched
    .filter(item => item.meta && item.meta.title)
    // map RouteLocationMatched to BreadcrumbItem (custom type)
    .map(item => ({
      path: item.path,
      meta: item.meta as BreadcrumbItem['meta'],
    }))
  const firstItem = matched[0]
  if (firstItem && firstItem.path !== '/dashboard') {
    matched.unshift({
      path: '/dashboard',
      meta: { titleKey: 'menu.dashboard', title: '首页' },
    })
  }
  tabs.value = matched
}

const getTitle = (item: BreadcrumbItem) => resolveTitle(item.meta, t)

// watch for route changes to update breadcrumb
watch(
  () => route.fullPath,
  () => getBreadCrumb(),
  { immediate: true }
)

</script>

<style scoped>
.breadcrumb-container {
  margin-left: 20px;
}
:deep(.el-breadcrumb__inner) {
  color: #fff !important;
}
:deep(.el-breadcrumb__inner a) {
  color: #fff !important;
}
:deep(.el-breadcrumb__item) {
  font-size: 16px !important;
}
</style>
