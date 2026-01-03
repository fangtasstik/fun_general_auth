import { defineStore } from "pinia";
import { reactive } from "vue";

export type Tab = {
  title: string;
  titleKey?: string;
  path: string;
}

export type TabState = {
  tabList: Tab[];
};

export const useTabStore = defineStore("tabStore", () => {
  const tabList = reactive<TabState['tabList']>([]);

  const addTab = (tab: any) => {
    const isExist = tabList.some((item) => item.path === tab.path);
    if (!isExist) {
      tabList.push(tab);
    }
  };

  const removeTab = (path: string) => {
    const index = tabList.findIndex((item) => item.path === path);
    if (index !== -1) {
      tabList.splice(index, 1);
    }
  };

  return { tabList, addTab, removeTab };
});