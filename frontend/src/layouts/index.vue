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
          <Tabs></Tabs>
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
import Tabs from "@/layouts/tabs/Tabs.vue";

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
	.leftmenu {
		background-color: #304156;
		transition: width 0.2s ease;
		/* overflow: hidden; */
	}
	.header {
		background-color: #2b2f3a;
    display: flex;
    align-items: center;
	}
	.main {
    padding: 0px;
		/* background-color: #e9eef3; */
    .tab {
      display: flex;
      justify-content: space-between;
    }
	}
	.footer {
		background-color: #b3c0d1;
	}
}
</style>
