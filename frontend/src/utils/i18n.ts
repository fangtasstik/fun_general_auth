import type { AppRouteMeta } from "@/types/route";

type Translate = (key: string) => string;

export const resolveTitle = (meta?: AppRouteMeta, t?: Translate) => {
	const key = meta?.titleKey;
	if (key && t) return t(key);
	return meta?.title ?? "";
};
