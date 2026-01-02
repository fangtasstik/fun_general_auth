<template>
  <MenuLogo></MenuLogo>
  <!-- <el-radio-group v-model="isCollapse" style="margin-bottom: 20px">
    <el-radio-button :value="false">expand</el-radio-button>
    <el-radio-button :value="true">collapse</el-radio-button>
  </el-radio-group> -->
  <el-menu
    :default-active="activeIndex"
    class="el-menu-vertical menu-popper"
    :collapse="menuStore.isMenuCollapsed"
    @open="handleOpen"
    @close="handleClose"
    background-color="#304156"
    unique-opened
    router
  >
    <MenuItem :menuList="menuStore.menuList"></MenuItem>
  </el-menu>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { useMenuStore } from "@/stores/menu";
import { useRoute } from "vue-router";
import MenuItem from "@/layouts/menu/MenuItem.vue";
import MenuLogo from "@/layouts/menu/MenuLogo.vue";

let menuStore = useMenuStore();
const route = useRoute();

const activeIndex = computed(() => {
  const currentPath = route.path;
  return currentPath;
});

const handleOpen = (key: string, keyPath: string[]) => {
	console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
	console.log(key, keyPath);
};
</script>

<style scoped lang="scss">
.el-menu-vertical-demo:not(.el-menu--collapse) {
	width: auto;
	min-height: 400px;
}
.el-menu {
	border-right: none;
}

:deep(.el-sub-menu .el-sub-menu__title) {
	color: #f4f4f5 !important;
}

:deep(.el-menu .el-menu-item) {
	color: #bfcbd9;
}
/* 菜单点中文字的颜色 */

:deep(.el-menu-item.is-active) {
	color: #409eff !important;
}
/* 当前打开菜单所有子菜单颜色 */

:deep(.is-opened .el-menu-item) {
	background-color: #1f2d3d !important;
}
/* 鼠标移动菜单的颜色 */

:deep(.el-menu-item:hover) {
	background-color: #001528 !important;
}

// :deep(.el-menu--collapse .el-menu-item),
// :deep(.el-menu--collapse .el-sub-menu__title) {
// 	color: #f4f4f5;
// }

</style>
