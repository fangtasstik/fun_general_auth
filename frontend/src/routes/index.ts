//vue-next-router
import {
	createRouter,
	createWebHistory,
	type RouteRecordRaw,
} from "vue-router";
// import HomeView from "@/views/HomeView.vue";
import Layout from "@/layouts/index.vue";

const routes: Array<RouteRecordRaw> = [
	{
		path: "/",
		component: Layout,
		redirect: "/dashboard",
		children: [
			{
				path: "dashboard",
				name: "Dashboard",
				component: () => import("@/views/dashboard/Index.vue"),
				meta: {
					title: "首页",
					icon: "#icondashboard",
				},
			},
		],
	},
	{
		path: "/system",
		component: Layout,
		name: "system",
		meta: {
			title: "系统管理",
			icon: "Setting",
			roles: ["sys:manage"],
		},
		children: [
			{
				path: "/userList",
				// component: "/system/User/UserList",
				component: () => import("@/views/system/User/UserList.vue"),
				name: "userList",
				meta: {
					title: "用户管理",
					icon: "UserFilled",
					roles: ["sys:user"],
				},
			},
			{
				path: "/roleList",
				// component: "/system/Role/RoleList",
				component: () => import("@/views/system/Role/RoleList.vue"),
				name: "roleList",
				meta: {
					title: "角色管理",
					icon: "Wallet",
					roles: ["sys:role"],
				},
			},
			{
				path: "/menuList",
				// component: "/system/Menu/MenuList",
				component: () => import("@/views/system/Menu/MenuList.vue"),
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
		component: Layout,
		name: "goodsRoot",
		meta: {
			title: "商品管理",
			icon: "Setting",
			roles: ["sys:goodsRoot"],
		},
		children: [
			{
				path: "/category",
				// component: "/goods/Category",
				component: () => import("@/views/goods/CategoryList.vue"),
				name: "category",
				meta: {
					title: "商品类型",
					icon: "UserFilled",
					roles: ["sys:category"],
				},
			},
			{
				path: "/goodsList",
				// component: "/goods/GoodsList",
				component: () => import("@/views/goods/GoodsList.vue"),
				name: "goodsList",
				meta: {
					title: "商品信息",
					icon: "Wallet",
					roles: ["sys:goodsList"],
				},
				children: [
					{
						path: "/goodsDetail",
						component: () =>
							import("@/views/goods/goodsList/GoodsDetail.vue"),
						name: "goodsDetail",
						meta: {
							title: "商品详情",
							icon: "UserFilled",
							roles: ["sys:goodsDetail"],
						},
					},
					{
						path: "/goodsStock",
						component: () =>
							import("@/views/goods/goodsList/GoodsStock.vue"),
						name: "goodsStock",
						meta: {
							title: "商品库存",
							icon: "UserFilled",
							roles: ["sys:goodsStock"],
						},
					},
				],
			},
		],
	},
	{
		path: "/testViews",
		component: Layout,
		name: "testViews",
		meta: {
			title: "测试视图管理",
			icon: "Setting",
			roles: ["sys:testViews"],
		},
		children: [
			{
				path: "/tools",
				component: () => import("@/views/test/tools/Index.vue"),
				name: "tools",
				meta: {
					title: "工具测试",
					icon: "ElementPlus",
					roles: ["tstins:category"],
				},
				children: [
					{
						path: "/testPiniaView",
						component: () =>
							import(
								"@/views/test/tools/TestPiniaView.vue"
							),
						name: "testPiniaView",
						meta: {
							title: "Pinia测试",
							icon: "ElementPlus",
							roles: ["tstins:testPiniaView"],
						},
					},
				],
			},
			{
				path: "/components",
				component: () => import("@/views/test/components/Index.vue"),
				name: "components",
				meta: {
					title: "组件测试",
					icon: "ElementPlus",
					roles: ["tstins:components"],
				},
				children: [
					{
						path: "/pageniationView",
						component: () =>
							import(
								"@/views/test/components/PaginationView.vue"
							),
						name: "pageniationView",
						meta: {
							title: "分页组件",
							icon: "ElementPlus",
							roles: ["tstins:pageniationView"],
						},
					},
				],
			},
		],
	},
];

const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;
