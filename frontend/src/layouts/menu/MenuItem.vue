<template>
  <template v-for="menu in menuList" :key="menu.path">
    <el-sub-menu class="white--text" v-if="menu.children && menu.children.length > 0" :index="menu.path">
      <template #title>
        <el-icon class="white--text">
          <component :is="menu.meta.icon" />
        </el-icon>
        <span class="white--text">{{ menu.meta.title }}</span>
      </template>
      <MenuItem :menuList="menu.children"></MenuItem>
    </el-sub-menu>
    <el-menu-item class="white--text" v-else :index="menu.path">
      <el-icon class="white--text">
        <component :is="menu.meta.icon" />
      </el-icon>
      <template #title>{{ menu.meta.title }}</template>
    </el-menu-item>
  </template>
</template>

<script lang="ts" setup>
const props = defineProps(['menuList']);
console.log(props.menuList);
</script>

<style scoped>
.white--text {
  color: #fff;
}
</style>
