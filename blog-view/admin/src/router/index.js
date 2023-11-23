import { createRouter, createWebHashHistory } from "vue-router";

import EditBlogView from "@/views/org/njj/myblog/EditBlogView.vue";
import ViewBlogView from "@/views/org/njj/myblog/ViewBlogView.vue";
import ManagerIndex from "@/views/org/njj/myblog/manager/Index.vue";
import ViewBlogEditor from "@/views/org/njj/myblog/manager/blog/ViewBlogEditor";
import ViewBlogViewer from "@/views/org/njj/myblog/manager/blog/ViewBlogViewer";

const routes = [
  {
    path:"/edit-blog",
    name:"editBlog",
    component: EditBlogView
  },
  {
    path:"/view-blog",
    name:"viewBlog",
    component: ViewBlogView
  },
  {
    path:"/manager",
    name:"managerIndex",
    component: ManagerIndex,
    children:[
      {
        path: "/viewBlogEditor",
        name: "viewBlogEditor",
        component: ViewBlogEditor
      },
      {
        path: "/viewBlogViewer",
        name: "viewBlogViewer",
        component: ViewBlogViewer
      },
    ]
  },
  // {
  //   path:"/viewBlogEditor",
  //   name:"viewBlogEditor",
  //   component: ViewBlogEditor
  // }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
