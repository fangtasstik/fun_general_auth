<template>
		<el-container class="main-container">
			<el-aside width="250px" class="leftmenu">
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
  import Header from '@/layouts/header/Header.vue'
  import MenuBar from '@/layouts/menu/MenuBar.vue'
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
