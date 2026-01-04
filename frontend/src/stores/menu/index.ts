import { defineStore } from "pinia";
import { reactive, ref } from "vue";

export const useMenuStore = defineStore("menuStore", () => {
	const menuList = reactive([
		{
			path: "/dashboard",
			component: "dashboard/Index",
			name: "dashboard",
			meta: {
				title: "首页",
				titleKey: "menu.dashboard",
				icon: "House",
				roles: ["sys:dashboard"],
			},
    },
		{
			path: "/system",
			component: "Layout",
			name: "system",
			meta: {
				title: "系统管理",
				titleKey: "menu.system",
				icon: "Setting",
				roles: ["sys:manage"],
			},
			children: [
				{
					path: "/system/userList",
					component: "/system/User/UserList",
					name: "userList",
					meta: {
						title: "用户管理",
						titleKey: "menu.userList",
						icon: "UserFilled",
						roles: ["sys:user"],
					},
				},
				{
					path: "/system/roleList",
					component: "/system/Role/RoleList",
					name: "roleList",
					meta: {
						title: "角色管理",
						titleKey: "menu.roleList",
						icon: "Wallet",
						roles: ["sys:role"],
					},
				},
				{
					path: "/system/menuList",
					component: "/system/Menu/MenuList",
					name: "menuList",
					meta: {
						title: "菜单管理",
						titleKey: "menu.menuList",
						icon: "Menu",
						roles: ["sys:menu"],
					},
				},
			],
		},
		{
			path: "/goodsRoot",
			component: "Layout",
			name: "goodsRoot",
			meta: {
				title: "商品管理",
				titleKey: "menu.goodsRoot",
				icon: "Setting",
				roles: ["sys:goodsRoot"],
			},
			children: [
				{
					path: "/goodsRoot/category",
					component: "/goods/Category",
					name: "category",
					meta: {
						title: "物资类型",
						titleKey: "menu.category",
						icon: "UserFilled",
						roles: ["sys:category"],
					},
				},
				{
					path: "/goodsRoot/goodsList",
					component: "/goods/GoodsList",
					name: "goodsList",
					meta: {
						title: "商品信息",
						titleKey: "menu.goodsList",
						icon: "Wallet",
						roles: ["sys:goodsList"],
					},
          children: [
            {
              path: "/goodsRoot/goodsList/goodsDetail",
              component: "/goods/goodsList/GoodsDetail",
              name: "goodsDetail",
              meta: {
                title: "商品详情",
                titleKey: "menu.goodsDetail",
                icon: "UserFilled",
                roles: ["sys:goodsDetail"],
              },
            },
            {
              path: "/goodsRoot/goodsList/goodsStock",
              component: "/goods/goodsList/GoodsStock",
              name: "goodsStock",
              meta: {
                title: "商品库存",
                titleKey: "menu.goodsStock",
                icon: "UserFilled",
                roles: ["sys:goodsStock"],
              },
            }
          ],
        },
      ],
		},
		{
			path: "/testViews",
			component: "Layout",
			name: "testViews",
			meta: {
				title: "测试视图管理",
				titleKey: "menu.testViews",
				icon: "ElementPlus",
				roles: ["sys:testViews"],
			},
			children: [
				{
					path: "/testViews/tools",
					// component: "/goods/tools",
					name: "tools",
					meta: {
						title: "工具测试",
						titleKey: "menu.tools",
						icon: "ElementPlus",
						roles: ["tstins:category"],
					},
          children: [
            {
              path: "/testViews/tools/testPiniaView",
              // component: "/goods/goodsList/testPiniaView",
              name: "testPiniaView",
              meta: {
                title: "Pinia测试",
                titleKey: "menu.testPiniaView",
                icon: "ElementPlus",
                roles: ["tstins:testPiniaView"],
              },
            },
          ]
				},
				{
					path: "/testViews/components",
					// component: "/goods/components",
					name: "components",
					meta: {
						title: "组件测试",
						titleKey: "menu.components",
						icon: "ElementPlus",
						roles: ["tstins:components"],
					},
          children: [
            {
              path: "/testViews/components/pageniationView",
              // component: "/goods/goodsList/pageniationView",
              name: "pageniationView",
              meta: {
                title: "分页组件",
                titleKey: "menu.pageniationView",
                icon: "ElementPlus",
                roles: ["tstins:pageniationView"],
              },
            },
            {
              path: "/testViews/components/dialogView",
              // component: "/goods/goodsList/dialogView",
              name: "dialogView",
              meta: {
                title: "弹窗组件",
                titleKey: "menu.dialogView",
                icon: "ElementPlus",
                roles: ["tstins:dialogView"],
              },
            },
          ],
        },
      ],
		},
	]);
  const isMenuCollapsed = ref(false)
  const setMenuCollapse = (collapse: boolean) => {
    isMenuCollapsed.value = collapse
  }
  const toggleMenuCollapse = () => {
    isMenuCollapsed.value = !isMenuCollapsed.value
  }
  return { menuList, isMenuCollapsed, setMenuCollapse, toggleMenuCollapse };
});
