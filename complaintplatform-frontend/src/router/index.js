import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('../views/Home.vue'),
    },
    {
      path: '/treeHole',
      name: 'treeHole',
      component: () => import('../views/TreeHole.vue'),
    },
    {
      path: '/answer',
      name: 'answer',
      component: () => import('../views/answer.vue'),
    },
    {
      path: '/tell',
      name: 'tell',
      component: () => import('../views/Complaint.vue'),
    },
    {
      path: '/idea',
      name: 'idea',
      component: () => import('../views/Idea.vue'),
    },
    {
      path: '/bad',
      name: 'bad',
      component: () => import('../views/BadExp.vue'),
    },
    {
      path: '/emotion',
      name: 'emotion',
      component: () => import('../views/Emotion.vue'),
    },


  ],
})

export default router
