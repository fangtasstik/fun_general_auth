import axios, {
	type AxiosInstance,
	type AxiosRequestConfig,
	type AxiosResponse,
	type InternalAxiosRequestConfig,
} from "axios";
import { ElMessage } from "element-plus";
import { i18n } from "@/i18n";

const config = {
	baseURL: "http://localhost:8089",
	timeout: 10000,
};

export interface Result<T = any> {
	code: number;
	msg: string;
	data: T;
}

class Http {
	private instance: AxiosInstance;
	constructor(config: AxiosRequestConfig) {
		this.instance = axios.create(config);
		this.interceptors();
	}

	private t(key: string, params?: Record<string, unknown>) {
		return i18n.global.t(key, params ?? {}) as string;
	}

	private interceptors() {
		//axios handle prior request send
		this.instance.interceptors.request.use(
			(config: InternalAxiosRequestConfig) => {
				let token = "";
				if (token) {
					config.headers!["token"] = token;
				}
				return config;
			},
			(error: any) => {
				error.data = {};
				error.data.msg = this.t("http.serverErrorContact");
				return error;
			}
		);
		//axios handle after response received
		this.instance.interceptors.response.use(
			(res: AxiosResponse) => {
				if (res.data.code == 200) {
					return res.data;
				} else {
					const message = res.data.msg || this.t("http.serverError");
					ElMessage.error(message);
					return Promise.reject(message);
				}
			},
			(error: any) => {
				console.log("进入错误");
				error.data = {};
				if (error && error.response) {
					switch (error.response.status) {
						case 400:
							error.data.msg = this.t("http.badRequest");
							ElMessage.error(error.data.msg);
							break;
						case 401:
							error.data.msg = this.t("http.unauthorized");
							ElMessage.error(error.data.msg);
							break;
						case 403:
							error.data.msg = this.t("http.forbidden");
							ElMessage.error(error.data.msg);
							break;
						case 404:
							error.data.msg = this.t("http.notFound");
							ElMessage.error(error.data.msg);
							break;
						case 405:
							error.data.msg = this.t("http.methodNotAllowed");
							ElMessage.error(error.data.msg);
							break;
						case 408:
							error.data.msg = this.t("http.requestTimeout");
							ElMessage.error(error.data.msg);
							break;
						case 500:
							error.data.msg = this.t("http.internalServerError");
							ElMessage.error(error.data.msg);
							break;
						case 501:
							error.data.msg = this.t("http.notImplemented");
							ElMessage.error(error.data.msg);
							break;
						case 502:
							error.data.msg = this.t("http.badGateway");
							ElMessage.error(error.data.msg);
							break;
						case 503:
							error.data.msg = this.t("http.serviceUnavailable");
							ElMessage.error(error.data.msg);
							break;
						case 504:
							error.data.msg = this.t("http.gatewayTimeout");
							ElMessage.error(error.data.msg);
							break;
						case 505:
							error.data.msg = this.t("http.httpVersionNotSupported");
							ElMessage.error(error.data.msg);
							break;
						default:
							error.data.msg = this.t("http.connectionError", {
								status: error.response.status,
							});
							ElMessage.error(error.data.msg);
					}
				} else {
					error.data.msg = this.t("http.connectFailed");
					ElMessage.error(error.data.msg);
				}
				return Promise.reject(error);
			}
		);
	}

	/* GET */
	get<T = Result>(url: string, params?: object): Promise<T> {
		return this.instance.get(url, { params });
	}
	/* POST */
	post<T = Result>(url: string, data?: object): Promise<T> {
		return this.instance.post(url, data);
	}
	/* PUT */
	put<T = Result>(url: string, data?: object): Promise<T> {
		return this.instance.put(url, data);
	}
	/* DELETE */
	delete<T = Result>(url: string): Promise<T> {
		return this.instance.delete(url);
	}
	//image upload
	upload<T = Result>(url: string, params?: object): Promise<T> {
		return this.instance.post(url, params, {
			headers: {
				"Content-Type": "multipart/form-data",
			},
		});
	}
}

export default new Http(config);
