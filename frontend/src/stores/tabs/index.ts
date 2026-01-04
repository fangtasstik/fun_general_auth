import { defineStore } from "pinia";
import { ref } from "vue";

export type Tab = {
	title: string;
	titleKey?: string;
	path: string;
};

export type TabState = {
	tabList: Tab[];
	activeTab: string;
};

export const useTabStore = defineStore(
	"tabStore",
	() => {
		const tabList = ref<TabState["tabList"]>([]);
		const activeTab = ref<string>("/dashboard");

		const addTab = (tab: Tab) => {
			const isExist = tabList.value.some((item) => item.path === tab.path);
			if (!isExist) {
        // Ensure dashboard tab is always at the front
        if (tab.path === '/dashboard' || tab.path === '/') {
          tabList.value.unshift(tab);
				} else {
					tabList.value.push(tab);
				}
			}
		};

		const removeTab = (path: string) => {
			const index = tabList.value.findIndex((item) => item.path === path);
			if (index !== -1) {
				tabList.value.splice(index, 1);
			}
		};

		const clearTabs = () => {
			tabList.value.splice(0, tabList.value.length);
		};

		const setActiveTab = (path: string) => {
			activeTab.value = path;
		};

		const addDashboardTab = (title: string) => {
			addTab({
				title,
				titleKey: "menu.dashboard",
				path: "/dashboard",
			});
		};

		return {
			tabList,
			activeTab,
			addTab,
			addDashboardTab,
			removeTab,
			clearTabs,
			setActiveTab,
		};
	},
	{
		persist: {
      key: 'tabPersist',
      storage: localStorage,
      pick: ['tabList', 'activeTab'],
		},
	}
);
