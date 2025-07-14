<template>
  <div id="app">
    <!-- 顶部导航栏 -->
    <el-header style="background-color: #e50000; color: white; height: 60px; display: flex; align-items: center;">
      <div style="display: flex; align-items: center; margin-left: 20px;">
        <img src="https://via.placeholder.com/50 " alt="Logo" style="width: 50px; height: 50px;" />
        <span style="font-size: 24px; font-weight: bold; margin-left: 10px;">吐槽平台</span>
      </div>
      <div style="margin-left: auto; display: flex; align-items: center;">
        <el-menu :default-active="activeIndex" mode="horizontal" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">树洞</el-menu-item>
        </el-menu>
        <el-input
            placeholder="搜索你感兴趣的帖子"
            prefix-icon="el-icon-search"
            style="width: 300px; margin-left: 20px;"
        ></el-input>
        <el-button type="primary" style="margin-left: 10px;">搜索</el-button>
        <el-dropdown trigger="click" style="margin-left: 20px;">
          <span class="el-dropdown-link">
            <i class="el-icon-user-solid"></i> 个人中心
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>设置</el-dropdown-item>
            <el-dropdown-item divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-badge :value="1" class="item" style="margin-left: 20px;">
          <i class="el-icon-bell"></i> 我的消息
        </el-badge>
      </div>
    </el-header>

    <!-- 主内容区域 -->
    <el-container style="margin-top: 20px;">
      <el-aside width="200px" style="background-color: #f9fafc;">
        <el-menu default-active="1" class="el-menu-vertical-demo">
          <el-menu-item index="1">全部</el-menu-item>
          <el-menu-item index="2">吐槽宣泄</el-menu-item>
          <el-menu-item index="3">答题解惑</el-menu-item>
          <el-menu-item index="4">意见建议</el-menu-item>
          <el-menu-item index="5">信访投诉</el-menu-item>
        </el-menu>
      </el-aside>

      <el-main>
        <el-row :gutter="20">
          <!-- 左侧帖子列表 -->
          <el-col :span="16">
            <el-tabs v-model="activeTab" type="card" style="margin-bottom: 20px;">
              <el-tab-pane label="发布时间" name="time"></el-tab-pane>
              <el-tab-pane label="点赞数" name="likes"></el-tab-pane>
              <el-tab-pane label="跟帖数" name="comments"></el-tab-pane>
              <el-tab-pane label="关注度" name="attention"></el-tab-pane>
            </el-tabs>
            <el-tabs v-model="categoryTab" type="card">
              <el-tab-pane label="全部" name="all"></el-tab-pane>
              <el-tab-pane label="教育训练" name="education"></el-tab-pane>
              <el-tab-pane label="科研工作" name="research"></el-tab-pane>
              <el-tab-pane label="政治工作" name="politics"></el-tab-pane>
              <el-tab-pane label="管理保障" name="management"></el-tab-pane>
              <el-tab-pane label="纪检监察" name="supervision"></el-tab-pane>
              <el-tab-pane label="研究生教育" name="graduate"></el-tab-pane>
              <el-tab-pane label="其他" name="other"></el-tab-pane>
            </el-tabs>
            <div v-for="(post, index) in posts" :key="index" class="post-card">
              <div class="post-header">
                <img :src="post.user.avatar" alt="User Avatar" class="avatar" />
                <span>{{ post.user.username }}</span>
                <span>{{ post.user.fans }} 粉丝 | {{ post.user.posts }} 帖子</span>
                <el-button size="small" type="primary" round>关注</el-button>
              </div>
              <div class="post-content">
                <el-tag type="info">{{ post.category }}</el-tag>
                <h3>{{ post.title }}</h3>
                <p>{{ post.content }}</p>
              </div>
              <div class="post-footer">
                <span>{{ post.timeAgo }}</span>
                <el-button size="small" icon="el-icon-star-off" round>收藏</el-button>
                <el-button size="small" icon="el-icon-chat-dot-round" round>评论</el-button>
                <el-button size="small" icon="el-icon-thumb" round>{{ post.likes }} 赞</el-button>
              </div>
            </div>
          </el-col>

          <!-- 右侧用户信息 -->
          <el-col :span="8">
            <div class="user-info-card">
              <img src="https://via.placeholder.com/50 " alt="User Avatar" class="avatar-large" />
              <h3>用户vdbroqa</h3>
              <div class="user-stats">
                <div>
                  <span>1</span>
                  <span>发帖</span>
                </div>
                <div>
                  <span>1</span>
                  <span>回帖</span>
                </div>
                <div>
                  <span>0</span>
                  <span>粉丝</span>
                </div>
                <div>
                  <span>0</span>
                  <span>关注</span>
                </div>
              </div>
              <el-button type="danger" round style="width: 100%; margin-top: 20px;">
                <i class="el-icon-edit"></i> 我要发布
              </el-button>
            </div>
            <div class="sidebar-section">
              <h4>我的收藏</h4>
              <div v-for="(collection, index) in collections" :key="index" class="collection-item">
                <h5>{{ collection.title }}</h5>
                <p>{{ collection.description }}</p>
                <div class="collection-footer">
                  <span>{{ collection.author }}</span>
                  <el-button size="small" type="success" icon="el-icon-star-on" round>已收藏</el-button>
                </div>
              </div>
              <el-button type="text" style="text-align: center; margin-top: 10px;">查看更多 »</el-button>
            </div>
            <div class="sidebar-section">
              <h4>我的点赞</h4>
              <div v-for="(like, index) in likes" :key="index" class="like-item">
                <h5>{{ like.title }}</h5>
                <p>{{ like.description }}</p>
                <div class="like-footer">
                  <span>{{ like.author }}</span>
                  <el-button size="small" type="primary" round>主帖</el-button>
                  <el-button size="small" type="primary" round>回帖</el-button>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  data() {
    return {
      activeIndex: '1',
      activeTab: 'time',
      categoryTab: 'all',
      posts: [
        {
          user: {
            avatar: 'https://via.placeholder.com/50 ',
            username: '用户vdbroqa',
            fans: 0,
            posts: 1,
          },
          category: '餐饮',
          title: '食堂是不是可以提供免费凉茶？',
          content: '最近气温过高，食堂是不是可以增加多点饮品，比如免费的凉茶？',
          timeAgo: '7分钟前',
          likes: 1,
        },
        {
          user: {
            avatar: 'https://via.placeholder.com/50 ',
            username: '大勇',
            fans: 0,
            posts: 1,
          },
          category: '吐槽',
          title: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          content: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          timeAgo: '4天前',
          likes: 1,
        },
      ],
      collections: [
        {
          title: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          description: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          author: '大勇',
        },
      ],
      likes: [
        {
          title: '食堂是不是可以提供免费凉茶？',
          description: '最近气温过高，食堂是不是可以增加多点饮品，比如免费的凉茶？',
          author: '用户vdbroqa',
        },
      ],
    };
  },
});
</script>

<style scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}

.avatar-large {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}

.post-card {
  background-color: #fff;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ebeef5;
}

.post-header {
  display: flex;
  align-items: center;
  gap: 10px;
}

.post-content {
  margin-top: 10px;
}

.post-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.user-info-card {
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ebeef5;
}

.user-stats {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}

.collection-item,
.like-item {
  background-color: #fff;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ebeef5;
}

.collection-footer,
.like-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}

.sidebar-section {
  margin-top: 20px;
}
</style>