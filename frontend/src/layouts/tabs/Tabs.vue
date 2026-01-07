<template>
	<div class="tabs-bar">
		<el-tabs
			v-model="activeTab"
			type="card"
			closable
			class="demo-tabs"
			@tab-remove="removeTab"
			@tab-click="tabClicked"
		>
			<el-tab-pane
				v-for="item in tabList"
				:key="item.path"
				:label="getTitle(item)"
				:name="item.path"
			>
			</el-tab-pane>
		</el-tabs>
    <CloseTabs></CloseTabs>
	</div>
</template>

<script lang="ts" setup>
import { computed, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import { useTabStore, type Tab } from "@/stores/tabs";
import { resolveTitle } from "@/utils/i18n";
import type { TabPaneName, TabsPaneContext } from "element-plus";
import CloseTabs from "@/layouts/tabs/CloseTabs.vue";

const tabStore = useTabStore();
const route = useRoute();
const router = useRouter();
const { t } = useI18n();

const getTitle = (tab: Tab) => resolveTitle(tab, t);
const tabList = computed(() => tabStore.tabList);
const activeTab = computed({
	get: () => tabStore.activeTab,
	set: (val: string) => tabStore.setActiveTab(val),
});

const addTab = () => {
	const { path, meta } = route;
	const tab: Tab = {
		title: (meta.title as string) || path,
		path: path,
		titleKey: meta.titleKey as string,
	};
	tabStore.addTab(tab);
	tabStore.setActiveTab(path);
};
const tabClicked = (tCtx: TabsPaneContext) => {
	const { props } = tCtx;
	router.push({ path: props.name as string });
};

const removeTab = (targetName: TabPaneName) => {
	const tabs = tabList.value;
	let activeName = activeTab.value;
	if (activeName === targetName) {
		tabs.forEach((tab: Tab, index: number) => {
			if (tab.path === targetName) {
				const nextTab = tabs[index + 1] || tabs[index - 1];
				if (nextTab) {
					activeName = nextTab.path;
				}
			}
		});
	}

	tabStore.setActiveTab(activeName);
	tabStore.removeTab(targetName as string);
	router.push({ path: activeName });
};

watch(
	() => route.path,
	() => {
		addTab();
	}
);

onMounted(() => {
	addTab();
});
</script>

<style scoped lang="scss">
:deep(.el-tabs__header) {
	margin: 0px;
}
:deep(.el-tabs__item) {
	height: 26px !important;
	line-height: 26px !important;
	text-align: center !important;
	border: 1px solid #d8dce5 !important;
	margin: 0px 3px !important;
	color: #495060;
	font-size: 12px !important;
	padding: 0px 10px !important;
}
:deep(.el-tabs__nav) {
	border: none !important;
}
:deep(.is-active) {
	border-bottom: 1px solid transparent !important;
	border: 1px solid #409eff !important;
	background-color: #409eff !important;
	color: #fff !important;
}
:deep(.el-tabs__item:hover) {
	color: #495060 !important;
}
:deep(.is-active:hover) {
	color: #fff !important;
}
:deep(.el-tabs__nav-next) {
	line-height: 26px !important;
}
:deep(.el-tabs__nav-prev) {
	line-height: 26px !important;
}

.tabs-bar {
	display: flex;
	align-items: center;
	gap: 12px;
}

.tabs-bar .demo-tabs {
	flex: 1;
}

.demo-tabs > .el-tabs__content {
	padding: 32px;
	color: #6b778c;
	font-size: 32px;
	font-weight: 600;
}
</style>
