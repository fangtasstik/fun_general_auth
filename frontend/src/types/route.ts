type Permission = string;

interface AppRouteMeta {
	title?: string;
	titleKey?: string;
	icon?: string;
	roles?: Permission[];
	hidden?: boolean;
}

interface MenuItem {
	path: string;
	meta?: AppRouteMeta;
	children?: MenuItem[];
}

interface BreadcrumbItem {
	path: string;
	meta?: AppRouteMeta;
}
export {
	type AppRouteMeta,
	type MenuItem,
	type Permission,
	type BreadcrumbItem,
};
