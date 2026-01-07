export type Translate = (key: string) => string;

export type TitleMeta = {
	title?: string;
	titleKey?: string;
};

export const resolveTitle = (meta?: TitleMeta, t?: Translate) => {
	const key = meta?.titleKey;
	if (key && t) return t(key);
	return meta?.title ?? "";
};
