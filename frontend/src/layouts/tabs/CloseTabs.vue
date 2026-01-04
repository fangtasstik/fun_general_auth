<template>
	<div class="close">
    <el-dropdown split-button size="small" @click="closeAllTabs">
			{{ t("tabs.closeAll") }}
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="closeCurrent">{{ t("tabs.closeCurrent") }}</el-dropdown-item>
          <el-dropdown-item @click="closeLeft">{{ t("tabs.closeLeft") }}</el-dropdown-item>
          <el-dropdown-item @click="closeRight">{{ t("tabs.closeRight") }}</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
	</div>
</template>

<script lang="ts" setup>
import { useI18n } from "vue-i18n";
import { useRouter } from "vue-router";
import { useTabStore } from "@/stores/tabs";

const { t } = useI18n();
const tabStore = useTabStore();
const router = useRouter();

const closeAllTabs = () => {
	tabStore.clearTabs();
	tabStore.addTab({
		title: t("menu.dashboard"),
		titleKey: "menu.dashboard",
		path: "/dashboard",
	});
	tabStore.setActiveTab("/dashboard");
	router.push({ path: "/dashboard" });
};

const closeCurrent = () => {
	const current = tabStore.activeTab;
	if (!current) return;
	tabStore.removeTab(current);
	if (tabStore.tabList.length === 0) {
		tabStore.addDashboardTab(t("menu.dashboard"));
		tabStore.setActiveTab("/dashboard");
		router.push({ path: "/dashboard" });
		return;
	}
	const next =
		tabStore.tabList[tabStore.tabList.length - 1]?.path || "/dashboard";
	tabStore.setActiveTab(next);
	router.push({ path: next });
};

const closeLeft = () => {
	const current = tabStore.activeTab;
	if (!current) return;
	const index = tabStore.tabList.findIndex((t) => t.path === current);
	if (index <= 0) return;
	tabStore.tabList.splice(0, index);
};

const closeRight = () => {
	const current = tabStore.activeTab;
	if (!current) return;
	const index = tabStore.tabList.findIndex((t) => t.path === current);
	if (index === -1) return;
	tabStore.tabList.splice(index + 1);
};
</script>

<style scoped lang="scss"></style>
