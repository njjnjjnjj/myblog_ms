import axios from 'axios';
import type {AxiosResponse} from "axios";
import type {BlogMetadata,PublishMode} from "./interfaces";

// 封装 API 请求
const api = axios.create({
    baseURL: 'api',
});

export async function getAllBlog(publishMode: PublishMode) {
    const response: AxiosResponse<Array<BlogMetadata>> = await api.get(`/blog/content/query/all/${publishMode}`);
    return response.data.data;
}
