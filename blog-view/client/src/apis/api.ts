import axios from 'axios';
import type {AxiosResponse} from "axios";
import type {BlogMetadata, Page, PublishMode, ResponseResult} from "./interfaces";

// 封装 API 请求
const api = axios.create({
    baseURL: 'api',
});

export async function getAllBlog(publishMode: PublishMode) {
    const response: AxiosResponse<ResponseResult<BlogMetadata[]>> = await api.get(`/blog/content/query/all/${publishMode}`);
    return response.data.data;
}

export async function queryBlog(publishMode: PublishMode, page: Page) {
    const response: AxiosResponse<ResponseResult<BlogMetadata[]>> = await api.get(`/blog/content/query/${publishMode}`, {
        params: page
    });
    return response.data.data;
}
