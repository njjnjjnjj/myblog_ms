import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '@/views/HomeView.vue';
import IndexView from '@/views/IndexView.vue';
import BlogContentListView from "@/views/BlogContentListView.vue";
import BlogContentView from "@/views/BlogContentView.vue";

const router = createRouter({
    // import.meta.env.BASE_URL
    history: createWebHistory("/"),
    routes: [
        {
            path: '/',
            name: 'index',
            component: IndexView,
            children: [
                {
                    path: 'blog_list',
                    name: 'blog_list',
                    component: BlogContentListView,
                    meta:{
                        keepAlive: true
                    }
                },
                {
                    path: 'blog_content/:metadataId',
                    name: 'blog_content',
                    component: BlogContentView,
                    props: true
                }
            ]
        },
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/about',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/AboutView.vue')
        }
    ]
})

export default router
