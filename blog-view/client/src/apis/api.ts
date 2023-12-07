import axios, { AxiosResponse } from 'axios';

// 定义数据交换类型
interface User {
    id: number;
    name: string;
    email: string;
    // 其他属性...
}

// 封装 API 请求
const api = axios.create({
    baseURL: 'http://localhost:1108',
});

export async function fetchUser(id: number): Promise<User> {
    const response: AxiosResponse<User> = await api.get(`/users/${id}`);
    return response.data;
}

export async function createUser(user: User): Promise<User> {
    const response: AxiosResponse<User> = await api.post('/users', user);
    return response.data;
}

export async function updateUser(id: number, user: User): Promise<User> {
    const response: AxiosResponse<User> = await api.put(`/users/${id}`, user);
    return response.data;
}

export async function deleteUser(id: number): Promise<void> {
    await api.delete(`/users/${id}`);
}
