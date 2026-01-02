<template>
  <template v-for="menu in menuList" :key="menu.path">
    <el-sub-menu class="white--text" v-if="menu.children && menu.children.length > 0" :index="menu.path">
      <template #title>
        <el-icon class="white--text">
          <component :is="menu.meta.icon" />
        </el-icon>
        <span class="white--text">{{ getTitle(menu) }}</span>
      </template>
      <MenuItem :menuList="menu.children"></MenuItem>
    </el-sub-menu>
    <el-menu-item class="white--text" v-else :index="menu.path">
      <el-icon class="white--text">
        <component :is="menu.meta.icon" />
      </el-icon>
      <template #title>{{ getTitle(menu) }}</template>
    </el-menu-item>
  </template>
</template>

<script lang="ts" setup>
import { useI18n } from "vue-i18n";
import type { MenuItem } from "@/types/route";
import { resolveTitle } from "@/utils/i18n";

const props = defineProps<{ menuList: MenuItem[] }>();
const { t } = useI18n();

const getTitle = (menu: MenuItem) => resolveTitle(menu.meta, t);
</script>

<style scoped>
.white--text {
  color: #fff;
}
</style>
