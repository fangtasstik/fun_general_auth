import { reactive } from "vue";
import type { DialogModel } from "@/types/baseType";

export function useDialog() {
	const dialog = reactive<DialogModel>({
		title: "",
		visible: false,
		width: 630,
		height: 280,
	});

	const open = () => {
		dialog.visible = true;
	};
	const close = () => {
		dialog.visible = false;
	};
	const confirm = () => {
		dialog.visible = false;
	};

	return { dialog, open, close, confirm };
}
