import { defineStore } from "pinia";
import { reactive, ref } from "vue";

export const useMenuStore = defineStore("menuStore", () => {
	const menuList = reactive([
		{
			path: "/system",
			component: "Layout",
			name: "system",
			meta: {
				title: "系统管理",
				icon: "Setting",
				roles: ["sys:manage"],
			},
			children: [
				{
					path: "/userList",
					component: "/system/User/UserList",
					name: "userList",
					meta: {
						title: "用户管理",
						icon: "UserFilled",
						roles: ["sys:user"],
					},
				},
				{
					path: "/roleList",
					component: "/system/Role/RoleList",
					name: "roleList",
					meta: {
						title: "角色管理",
						icon: "Wallet",
						roles: ["sys:role"],
					},
				},
				{
					path: "/menuList",
					component: "/system/Menu/MenuList",
					name: "menuList",
					meta: {
						title: "菜单管理",
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
				icon: "Setting",
				roles: ["sys:goodsRoot"],
			},
			children: [
				{
					path: "/category",
					component: "/goods/Category",
					name: "category",
					meta: {
						title: "物资类型",
						icon: "UserFilled",
						roles: ["sys:category"],
					},
				},
				{
					path: "/goodsList",
					component: "/goods/GoodsList",
					name: "goodsList",
					meta: {
						title: "商品信息",
						icon: "Wallet",
						roles: ["sys:goodsList"],
					},
          children: [
            {
              path: "/goodsDetail",
              component: "/goods/goodsList/GoodsDetail",
              name: "goodsDetail",
              meta: {
                title: "商品详情",
                icon: "UserFilled",
                roles: ["sys:goodsDetail"],
              },
            },
            {
              path: "/goodsStock",
              component: "/goods/goodsList/GoodsStock",
              name: "goodsStock",
              meta: {
                title: "商品库存",
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
				icon: "ElementPlus",
				roles: ["sys:testViews"],
			},
			children: [
				{
					path: "/tools",
					// component: "/goods/tools",
					name: "tools",
					meta: {
						title: "工具测试",
						icon: "ElementPlus",
						roles: ["tstins:category"],
					},
          children: [
            {
              path: "/testPiniaView",
              // component: "/goods/goodsList/testPiniaView",
              name: "testPiniaView",
              meta: {
                title: "Pinia测试",
                icon: "ElementPlus",
                roles: ["tstins:testPiniaView"],
              },
            },
          ]
				},
				{
					path: "/components",
					// component: "/goods/components",
					name: "components",
					meta: {
						title: "组件测试",
						icon: "ElementPlus",
						roles: ["tstins:components"],
					},
          children: [
            {
              path: "/pageniationView",
              // component: "/goods/goodsList/pageniationView",
              name: "pageniationView",
              meta: {
                title: "分页组件",
                icon: "ElementPlus",
                roles: ["tstins:pageniationView"],
              },
            }
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
