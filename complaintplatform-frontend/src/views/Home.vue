<!--网页名:Home 首页-->
<!--域名:/-->
<!--页面布局: 顶部导航栏、左中右行列布局-->
<!--依赖库版本:{-->

<!--    "@element-plus/icons-vue": "^2.3.1",-->
<!--    "element-plus": "^2.10.3",-->
<!--    "vue": "^3.5.17",-->
<!--    "vue-router": "^4.5.1"-->
<!--    "@vitejs/plugin-vue": "^6.0.0",-->
<!--    "sass": "^1.89.2",-->
<!--    "unplugin-auto-import": "^19.3.0",-->
<!--    "unplugin-element-plus": "^0.10.0",-->
<!--    "unplugin-vue-components": "^28.8.0",-->
<!--    "vite": "^7.0.0",-->
<!--    "vite-plugin-vue-devtools": "^7.7.7"}-->
<!--@使用 IDEA 2024.3.5 开发@-->





<template>
  <div id="app">
    <!-- 顶部导航栏 -->
    <el-header style="background-color: darkseagreen; color: white; height: 60px; display: flex; align-items: center;">
      <div style="display: flex; align-items: center; margin-left: 20px;">
        <img src="https://www.runoob.com/wp-content/uploads/2017/01/vue.png " alt="Logo" style="width: 50px; height: 50px;" />
        <span style="font-size: 24px; font-weight: bold; margin-left: 10px;">吐槽平台</span>
      </div>
      <div style="background-color: darkseagreen;margin-left: auto; display: flex; align-items: center;" >
        <el-menu :default-active="activeIndex" style="background-color: darkseagreen;" mode="horizontal" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="1" style="width: 80px"><router-link style="text-decoration: none;" to="/">首页</router-link></el-menu-item>
          <el-menu-item index="2" style="width: 80px"><router-link  style="text-decoration: none;" to="/treeHole">树洞</router-link></el-menu-item>
          <el-menu-item index="2" style="width: 80px;color:black ">联系我们</el-menu-item>
        </el-menu>

        <el-input
            placeholder="搜索你感兴趣的吐槽帖子"
            type="text"
            v-model="searchData"
            style="width: 300px; margin-left: 20px;"
            clearable
        ></el-input>
        <el-button  type="primary" style="margin-left: 10px;margin-right: 10px" >搜索</el-button>
        <el-button type="danger" plain>退出登录</el-button>
        <el-badge :value="99" class="item" style="margin-left: 20px;">
          <i class="el-icon-bell"></i> 我的消息
        </el-badge>
      </div>
    </el-header>

    <!-- 主内容区域 -->
    <el-container style="margin-top: 20px;">
      <el-aside width="200px" style=" min-height: 900px; max-height: 1500px;">
        <el-menu default-active="1" class="el-menu-vertical-demo" style="text-align: right; font-weight: bold; padding-left: 50px;">
          <el-menu-item index="1">
            <router-link to="/" class="menu-link">全部</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/tell" class="menu-link">吐槽宣泄</router-link>
          </el-menu-item>
          <el-menu-item index="3">
            <router-link to="/answer" class="menu-link">答题解惑</router-link>
          </el-menu-item>
          <el-menu-item index="4">
            <router-link to="/idea" class="menu-link">意见建议</router-link>
          </el-menu-item>
          <el-menu-item index="5">
            <router-link to="/bad" class="menu-link">信访投诉</router-link>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-main style="background-color: #fafafa;min-height:900px;max-height: 1500px">
        <el-row :gutter="20" >
          <!-- 左侧帖子列表 -->
          <el-col :span="16" >
<!--            <el-tabs v-model="activeTab" type="card" style="margin-bottom: 20px;" >-->
<!--              <el-tab-pane label="发布时间" name="time"></el-tab-pane>-->
<!--              <el-tab-pane label="点赞数" name="likes"></el-tab-pane>-->
<!--              <el-tab-pane label="跟帖数" name="comments"></el-tab-pane>-->
<!--              <el-tab-pane label="关注度" name="attention"></el-tab-pane>-->
<!--            </el-tabs>-->
            <el-tabs v-model="categoryTab" type="card" style="width: 100%; margin-top: 20px; font-weight: bold">
              <el-tab-pane label="全部" name="all"></el-tab-pane>
              <el-tab-pane label="教育训练" name="education"></el-tab-pane>
              <el-tab-pane label="科研工作" name="research"></el-tab-pane>
              <el-tab-pane label="政治工作" name="politics"></el-tab-pane>
              <el-tab-pane label="管理保障" name="management"></el-tab-pane>
              <el-tab-pane label="纪检监察" name="supervision"></el-tab-pane>
              <el-tab-pane label="研究生教育" name="graduate"></el-tab-pane>
              <el-tab-pane label="其他" name="other"></el-tab-pane>
            </el-tabs>
            <el-tabs type="border-card" class="demo-tabs">
              <el-tab-pane>
                <template #label>
                    <span class="custom-tabs-label">
                      <el-icon  style="margin-right: 4px;"><timer /></el-icon>
                      <span >发布时间</span>
                    </span>
                </template>
                <div v-for="(post, index) in postsForTime" :key="index" class="post-card">
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
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><Star /></el-icon></el-button>
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><ChatLineSquare /></el-icon></el-button>
                    <el-button circle>{{ post.likes }} <el-icon style="color: #2b9bc1;" size="large"><Pointer /></el-icon></el-button>
                  </div>
                </div>
              </el-tab-pane>
              <el-tab-pane>
                <template #label>
                    <span class="custom-tabs-label">
                      <el-icon style="margin-right: 5px;"><Star /></el-icon>
                      <span>点赞数</span>
                    </span>
                </template>
                <div v-for="(post, index) in postsForLikes" :key="index" class="post-card">
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
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><Star /></el-icon></el-button>
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><ChatLineSquare /></el-icon></el-button>
                    <el-button circle>{{ post.likes }} <el-icon style="color: #2b9bc1;" size="large"><Pointer /></el-icon></el-button>
                  </div>
                </div>
              </el-tab-pane>
              <el-tab-pane>
                <template #label>
        <span class="custom-tabs-label">
          <el-icon style="margin-right: 5px;"><ChatDotSquare /></el-icon>
          <span>跟帖数</span>
        </span>
                </template>
                <div v-for="(post, index) in postsForFollow" :key="index" class="post-card">
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
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><Star /></el-icon></el-button>
                    <el-button  circle><el-icon style="color: #2b9bc1;" size="large"><ChatLineSquare /></el-icon></el-button>
                    <el-button circle>{{ post.likes }} <el-icon style="color: #2b9bc1;" size="large"><Pointer /></el-icon></el-button>
                  </div>
                </div>
              </el-tab-pane>
            </el-tabs>

            <div style="margin-top: 20px" class="demo-pagination-block">

              <el-pagination
                  :current-page="currentPage"
                  :page-size="pageSize"
                  :page-sizes="[5, 10, 15]"
                  layout="total, sizes, prev, pager, next, jumper"
                  :total="totalPage"
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
              />
            </div>
          </el-col>

          <!-- 右侧用户信息 -->
          <el-col :span="8">
            <div class="user-info-card" style="background-color: #fafff6">
              <div style="padding-top: 30px;display: flex; flex-direction: column; align-items: center;">
                <img :src="userData.avatar" alt="User Avatar" class="avatar-large" />
                <h3><el-button style="font-weight: bold;font-size: large" text plain @click="open1">{{ userData.username }}</el-button></h3>
              </div>

              <div class="user-stats" style="width: 100%; margin-top: 20px; font-weight: bold">
                <div>
                  <span >{{ userData.posts }}</span>
                  <span>发帖</span>
                </div>
                <div>
                  <span>{{ userData.getBack }}</span>
                  <span>回帖</span>
                </div>
                <div>
                  <span>{{ userData.fans }}</span>
                  <span>粉丝</span>
                </div>
                <div>
                  <span>{{ userData.follow }}</span>
                  <span>关注</span>
                </div>
              </div>
              <el-button type="primary" round style="width: 100%; margin-top: 20px; font-weight: bold" @click="dialogFormVisible = true">
                <i class="el-icon-edit"></i> 我要发布
              </el-button>

              <el-dialog v-model="dialogFormVisible" title="我要发布" width="500">
                <el-form :model="postData" style="margin-left: -100px" label-position="right" label-width="10px">
                  <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="postData.title" autocomplete="off" />
                  </el-form-item>
                  <el-form-item label="类别" :label-width="formLabelWidth">
                    <el-select v-model="postData.category" placeholder="请选择类别">
                      <el-option v-for="item in categoryData" :key="item" :label="item" :value="item" />
                    </el-select>
                  </el-form-item>
                  <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input type="textarea" v-model="postData.content" autocomplete="off" />
                  </el-form-item>
                </el-form>
                <template #footer>
                  <div class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取消</el-button>
                    <el-button type="primary" @click="dialogFormVisible = false">
                      确定
                    </el-button>
                  </div>
                </template>
              </el-dialog>

            </div>
            <div class="sidebar-section">
              <el-divider style="margin-bottom: 0"/>
              <div style="display: flex; justify-content: space-between; align-items: center;">
                <h4>我的收藏</h4>
                <el-button type="text" style="margin-top: 10px;">查看更多 »</el-button>
              </div>
              <div v-for="(collection, index) in collections" :key="index" class="collection-item">
                <h5>{{ collection.title }}</h5>
                <p>{{ collection.description }}</p>
                <div class="collection-footer">
                  <span>{{ collection.author }}</span>
                  <el-button size="small" type="success" icon="el-icon-star-on" round>已收藏</el-button>
                </div>
              </div>

            </div>
            <div class="sidebar-section">
              <el-divider style="margin-bottom: 0"/>
              <div style="display: flex; justify-content: space-between; align-items: center;">
                <h4>我的收藏</h4>
                <el-button type="text" style="margin-top: 10px;">查看更多 »</el-button>
              </div>
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
import {onMounted, reactive} from "vue";
import {Calendar, ChatDotSquare, ChatLineSquare, Pointer, Star, Timer} from "@element-plus/icons-vue";
import { ElMessageBox, ElSwitch } from 'element-plus'
import { h, ref } from 'vue'
// import {Delete, Edit, Search, Share, View} from "@element-plus/icons-vue";


onMounted(()=>{
  console.log("页面加载完成")
})

export default defineComponent({
  name: 'Sidebar',
  components: {Timer, ChatLineSquare, Pointer, ChatDotSquare, Star, Calendar},
  setup() {




    const userInfo = {
      name: '大勇',
      id: '2022022222',
      grade: '大三',
      academy: '计算机学院',
    }

// 构建 message 内容
    const messageVNode = h('div', null, [
      Object.entries(userInfo).map(([key, value]) =>
          h('div', { style: { marginBottom: '8px' } }, `${key}：${value}`)
      ),
    ])

// 显示 MessageBox

    const open1 = () => {
      ElMessageBox({
        title: '个人信息',
        message: messageVNode,
        showCancelButton: false,
      })
    }



    const data = () => ( {

      tableData: [
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove S三个人灌灌灌灌灌灌灌灌灌灌灌灌灌灌从烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦灌灌t, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ],
      activeIndex: ref('1'),
      activeTab: ref('time'),
      categoryTab: ref('all'),

      searchData:ref(''),
      postData:ref({user:{avatar:'',username:'',fans:0,posts:0},category:'',title:'',content:'',timeAgo:'',likes:0}),
      selectData:ref(''),
      categoryData:['吐槽','餐饮','学习','训练'],
      userData:{
        avatar:'https://img1.baidu.com/it/u=1466043940,779429976&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
        username:'大勇',
        fans:457,
        posts:1,
        getBack:2,
        follow:3,
      },
      postsForTime: [
        {
          user: {
            avatar: 'https://ww4.sinaimg.cn/mw690/008apmBfgy1hyd10ozzjnj30u00u0q4k.jpg ',
            username: '用户vdbroqa',
            fans: 24,
            posts: 15,
          },
          category: '餐饮',
          title: '食堂是不是可以提供免费凉茶？',
          content: '最近气温过高，食堂是不是可以增加多点饮品，比如免费的凉茶？',
          timeAgo: '7分钟前',
          likes: 1,
        },
        {
          user: {
            avatar: 'https://img1.baidu.com/it/u=1466043940,779429976&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500 ',
            username: '大勇',
            fans: 457,
            posts: 1,
          },
          category: '吐槽',
          title: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          content: '"吐槽平台"(按时间分布)上线了，欢迎大家使用并吐槽！',
          timeAgo: '4天前',
          likes: 1,
        },
      ],
      postsForLikes: [
        {
          user: {
            avatar: 'https://ww4.sinaimg.cn/mw690/008apmBfgy1hyd10ozzjnj30u00u0q4k.jpg ',
            username: '用户vdbroqa',
            fans: 24,
            posts: 15,
          },
          category: '餐饮',
          title: '食堂是不是可以提供免费凉茶？',
          content: '最近气温过高，食堂是不是可以增加多点饮品，比如免费的凉茶？',
          timeAgo: '7分钟前',
          likes: 1,
        },
        {
          user: {
            avatar: 'https://img1.baidu.com/it/u=1466043940,779429976&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500 ',
            username: '大勇',
            fans: 457,
            posts: 1,
          },
          category: '吐槽',
          title: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          content: '"吐槽平台"(按点赞数分布)上线了，欢迎大家使用并吐槽！',
          timeAgo: '4天前',
          likes: 1,
        },
      ],
      postsForFollow: [
        {
          user: {
            avatar: 'https://ww4.sinaimg.cn/mw690/008apmBfgy1hyd10ozzjnj30u00u0q4k.jpg ',
            username: '用户vdbroqa',
            fans: 24,
            posts: 15,
          },
          category: '餐饮',
          title: '食堂是不是可以提供免费凉茶？',
          content: '最近气温过高，食堂是不是可以增加多点饮品，比如免费的凉茶？',
          timeAgo: '7分钟前',
          likes: 1,
        },
        {
          user: {
            avatar: 'https://img1.baidu.com/it/u=1466043940,779429976&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500 ',
            username: '大勇',
            fans: 457,
            posts: 1,
          },
          category: '吐槽',
          title: '"吐槽平台"上线了，欢迎大家使用并吐槽！',
          content: '"吐槽平台"(按跟帖数分布)上线了，欢迎大家使用并吐槽！',
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
      ]

    });
    const dialogTableVisible = ref(false)
    const dialogFormVisible = ref(false)
    const formLabelWidth = '140px'

    const currentPage = ref(1);
    const totalPage = ref(2);
    const pageSize = ref(10);
    const handleSizeChange = (newPageSize: number) => {
      console.log(`${newPageSize} items per page`)
      pageSize.value = newPageSize
    }
    const handleCurrentChange = (newCurrentPage: number) => {
      console.log(`current page: ${newCurrentPage}`)
      currentPage.value = newCurrentPage
    }
    return {
      handleSizeChange,
      handleCurrentChange,
      dialogFormVisible,
      dialogTableVisible,
      formLabelWidth,
      pageSize,
      currentPage,
      totalPage,
      open1,
      ...data(),
    }
  }
})

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
  width: 75px;
  height: 75px;
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

/* 去除 router-link 的下划线 */
.menu-link {
  text-decoration: none;
  color: #999999; /* 紫色 */
  font-size: 18px;
  font-weight: bold;
  line-height: 1.6; /* 调整行高 */
}

/* 激活状态的样式 */
.el-menu-item.is-active .menu-link {
  color: #000; /* 激活时字体颜色变深 */
}



.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}
.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}

</style>
