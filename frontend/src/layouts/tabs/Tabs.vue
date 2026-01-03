<template>
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
</template>

<script lang="ts" setup>
import { computed, ref, watch, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useI18n } from "vue-i18n";
import { useTabStore, type Tab } from "@/stores/tabs";
import { resolveTitle } from "@/utils/i18n";
import type { TabPaneName, TabsPaneContext } from "element-plus";

const tabStore = useTabStore();
const route = useRoute();
const router = useRouter();
const { t } = useI18n();

const getTitle = (tab: Tab) => resolveTitle(tab, t);
const tabList = computed(() => tabStore.tabList);
const activeTab = ref("");

const setActiveTab = () => {
	activeTab.value = route.path;
};

const addTab = () => {
  const { path, meta } = route;
	const tab: Tab = {
		title: meta.title as string || path,
		path: path,
		titleKey: meta.titleKey as string,
	};
	tabStore.addTab(tab);
  setActiveTab();
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

	activeTab.value = activeName;
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
})

</script>

<style>
.demo-tabs > .el-tabs__content {
	padding: 32px;
	color: #6b778c;
	font-size: 32px;
	font-weight: 600;
}
</style>
