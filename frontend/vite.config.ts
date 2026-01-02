import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from "path";

// https://vite.dev/config/
export default defineConfig({
	plugins: [vue()],
	server: {
		host: "0.0.0.0",
		port: 5148,
		hmr: true,
		open: true,
	},
	resolve: {
    // 对象写法
    // alias: {
		// 		// '@': '/src',
		// 		// '@': resolve(__dirname, 'src'),
    // },
    // 数组写法，方便使用正则、前缀等复杂匹配
		alias: [
			{
				find: "@",
				replacement: resolve(__dirname, "src"),
			},
		],
	},
});
