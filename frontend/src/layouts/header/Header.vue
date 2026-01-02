<template>
	<div class="header-bar">
		<div class="header-left">
			<CollapseMenu></CollapseMenu>
			<BreadCrumb></BreadCrumb>
		</div>
		<div class="header-right">
			<LanguageSwitch></LanguageSwitch>
		</div>
	</div>
</template>

<script lang="ts" setup>
import BreadCrumb from "./BreadCrumb.vue";
import CollapseMenu from "./CollapseMenu.vue";
import LanguageSwitch from "./LanguageSwitch.vue";
</script>

<style scoped>
.header-bar {
	display: flex;
	align-items: center;
	justify-content: space-between;
	height: 100%;
	width: 100%;
}

.header-left {
	display: flex;
	align-items: center;
	gap: 12px;
}
.header-right {
	margin-left: auto;
}
</style>
