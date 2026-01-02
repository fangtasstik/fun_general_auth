<template>
		<el-container class="main-container">
			<el-aside :width="asideWidth" class="leftmenu">
        <MenuBar></MenuBar>
      </el-aside>
			<el-container>
				<el-header class="header">
          <Header></Header>
        </el-header>
				<el-main class="main">
          <RouterView></RouterView>
        </el-main>
				<!-- <el-footer class="footer">Footer</el-footer> -->
			</el-container>
		</el-container>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { useMenuStore } from "@/stores/menu";
import Header from "@/layouts/header/Header.vue";
import MenuBar from "@/layouts/menu/MenuBar.vue";

const menuStore = useMenuStore();
const asideWidth = computed(() =>
	menuStore.isMenuCollapsed ? "64px" : "250px"
);
</script>

<style scoped>
/* .common-layout {
  height: 100vh;
} */
.main-container {
	height: 100%;
	.header {
		background-color: #b3c0d1;
	}
	.leftmenu {
		background-color: #304156;
		transition: width 0.2s ease;
		overflow: hidden;
	}
	.header {
		background-color: #b3c0d1;
    display: flex;
    align-items: center;
	}
	.main {
		background-color: #e9eef3;
	}
	.footer {
		background-color: #b3c0d1;
	}
}
</style>
