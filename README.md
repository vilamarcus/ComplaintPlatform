[TOC]

# 吐槽平台后端项目

## 项目概述
这是一个基于Spring Boot的吐槽平台后端服务，提供RESTful API接口。

## 开发环境要求
- JDK 21
- Maven 3.9.10
- MySQL 8.0+
- 开发工具：推荐使用VSCode或IntelliJ IDEA

## 数据库配置

### 1. 创建数据库
执行以下SQL命令创建数据库(可在MySQL命令行或客户端工具中执行):
```sql
-- 创建名为complaint_platform的数据库
-- 使用utf8mb4字符集以支持完整Unicode字符(包括emoji)
-- 使用utf8mb4_unicode_ci排序规则
CREATE DATABASE complaint_platform CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 修改数据库连接配置
配置文件路径: `complaintplatform-backend/src/main/resources/application.properties`

需要修改的配置项:
```
# 数据库连接URL
# localhost:3306 - MySQL服务器地址和端口
# complaint_platform - 数据库名称(需与创建的数据库名一致)
# useSSL=false - 禁用SSL连接(开发环境)
# serverTimezone=UTC - 设置时区为UTC
# characterEncoding=utf8 - 设置字符编码为UTF-8
spring.datasource.url=jdbc:mysql://localhost:3306/complaint_platform?useSSL=false&serverTimezone=UTC&characterEncoding=utf8

# 数据库用户名(默认为root，可根据实际情况修改)
spring.datasource.username=root

# 数据库密码(替换为你的MySQL密码)
spring.datasource.password=your_password
```

配置说明:
1. 数据库名称: 修改URL中的`complaint_platform`为你创建的数据库名
2. 用户名: 修改`spring.datasource.username`为你的MySQL用户名
3. 密码: 修改`spring.datasource.password`为对应用户的密码

## 项目启动
1. 克隆项目
2. 进入后端项目目录:
```bash
cd complaintplatform-backend
```
3. 构建项目:
```bash
mvn clean install
```
4. 运行项目:
```bash
mvn spring-boot:run
```

## 技术栈
- Spring Boot 3.3.13
- Spring Data JPA
- MyBatis
- MySQL 8.3
- Lombok
- Swagger UI (API文档)

## 接口开发流程

### 1. 定义实体(Entity)
在`com.example.complaintplatform_backend.entity`包中创建Java类：
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createTime;
}
```

### 2. 创建DAO接口
DAO层有两种实现方式：

#### 方式一：使用注解方式
在`com.example.complaintplatform_backend.dao`包中定义接口，直接在方法上使用SQL注解：
```java
@Mapper
public interface ComplaintDao {
    // 使用@Insert注解定义插入语句
    @Insert("INSERT INTO complaint(title, content) VALUES(#{title}, #{content})")
    int insert(Complaint complaint);
    
    // 使用@Select注解定义查询语句
    @Select("SELECT * FROM complaint")
    List<Complaint> selectAll();
}
```
特点：
- 简单SQL语句可以直接写在注解中
- 适合简单的CRUD操作
- 代码和SQL在一起，便于维护

#### 方式二：使用Mapper XML映射
1. 创建DAO接口：
```java
@Mapper
public interface ComplaintDao {
    int insert(Complaint complaint);
    List<Complaint> selectAll();
}
```

2. 在resources/mapper目录下创建对应的XML文件：
```xml
<mapper namespace="com.example.complaintplatform_backend.dao.ComplaintDao">
    <insert id="insert">
        INSERT INTO complaint(title, content)
        VALUES(#{title}, #{content})
    </insert>
    
    <select id="selectAll" resultType="com.example.complaintplatform_backend.entity.Complaint">
        SELECT * FROM complaint
    </select>
</mapper>
```
特点：
- 复杂SQL更易于编写和维护
- 支持动态SQL
- 适合复杂查询场景
- SQL与Java代码分离

#### 如何选择：
- 简单SQL：推荐使用注解方式
- 复杂SQL：推荐使用XML方式
- 可以混合使用，部分方法用注解，部分用XML映射

### 3. Service层
**职责**：
- 实现业务逻辑
- 调用DAO层进行数据访问
- 处理事务管理
- 数据校验和转换

**调用关系**：
Controller → Service → DAO

**实现要求**：
1. 在`com.example.complaintplatform_backend.service`包中创建@Service类
2. 通过构造函数注入DAO依赖
3. 实现具体的业务方法
4. 参考代码中的`ExampleService.java`实现

### 4. Controller层
**职责**：
- 接收HTTP请求
- 参数校验和转换
- 调用Service层处理业务
- 返回统一格式的响应

**调用关系**：
HTTP请求 → Controller → Service

**实现要求**：
1. 在`com.example.complaintplatform_backend.controller`包中创建@RestController类
2. 使用@RequestMapping定义API路径
3. 通过构造函数注入Service依赖
4. 使用@GetMapping/@PostMapping等注解定义API方法
5. 参考代码中的`ExampleController.java`实现

### 5. 层次调用关系
```
HTTP请求
   ↓
Controller层 (处理请求/响应)
   ↓
Service层 (业务逻辑)
   ↓
DAO层 (数据访问) --可能--> mapper
   ↓
数据库
```

各层代码实现请参考项目中对应的示例类：
- Entity: `Example.java`
- DAO: `ExampleDao.java` 
- Service: `ExampleService.java`
- Controller: `ExampleController.java`
- mapper: `ExampleMapper.xml`

---

# 吐槽平台前端开发指南

## 1. Axios接口调用

### 配置axios实例
在`main.js`中全局配置：
```javascript
import axios from 'axios'

Vue.prototype.$axios = axios.create({
  baseURL: process.env.VUE_APP_API_URL || '/api',
  timeout: 10000
})
```

### 组件中使用
```javascript
export default {
  methods: {
    async fetchComplaints() {
      try {
        const response = await this.$axios.get('/complaints')
        this.complaints = response.data
      } catch (error) {
        console.error('接口调用失败:', error)
      }
    }
  }
}
```

## 2. Vuex状态管理

### store/index.js 配置
```javascript
export default new Vuex.Store({
  state: {
    complaints: []
  },
  mutations: {
    SET_COMPLAINTS(state, complaints) {
      state.complaints = complaints
    }
  },
  actions: {
    async fetchComplaints({ commit }) {
      const res = await this._vm.$axios.get('/complaints')
      commit('SET_COMPLAINTS', res.data)
    }
  }
})
```

### 组件中使用
```javascript
export default {
  computed: {
    ...mapState(['complaints'])
  },
  created() {
    this.$store.dispatch('fetchComplaints')
  }
}
```

## 3. Vue Router路由

### 添加新路由
```javascript
const routes = [
  {
    path: '/complaints',
    component: () => import('@/views/ComplaintList.vue')
  }
]
```

## 4. 接口代理配置

### 开发环境配置
在`vue.config.js`中添加：
```javascript
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
}
```