<template>
  <div class="complaint-list">
    <h2>投诉列表</h2>
    <div v-if="loading">加载中...</div>
    <div v-else-if="error">{{ error }}</div>
    <ul v-else>
      <li v-for="complaint in complaints" :key="complaint.id">
        {{ complaint.title }} - {{ complaint.status }}
      </li>
    </ul>
    <button @click="fetchData">刷新数据</button>
  </div>
</template>

<script>
export default {
  name: 'ComplaintList',
  data() {
    return {
      complaints: [],
      loading: false,
      error: null,
      newComplaint: {
        title: '',
        content: ''
      }
    }
  },
  created() {
    this.fetchComplaints()
  },
  methods: {
    async fetchComplaints() {
      this.loading = true
      this.error = null
      try {
        const response = await this.$axios.get('/complaints', {
          params: {
            page: 1,
            size: 10
          }
        })
        this.complaints = response.data
      } catch (err) {
        this.error = '获取投诉列表失败: ' + (err.response?.data?.message || err.message)
        console.error('API调用失败:', err)
      } finally {
        this.loading = false
      }
    },
    
    async submitComplaint() {
      if (!this.newComplaint.title || !this.newComplaint.content) {
        this.error = '请填写投诉标题和内容'
        return
      }
      
      this.loading = true
      try {
        await this.$axios.post('/complaints', this.newComplaint)
        this.$message.success('投诉提交成功')
        this.newComplaint = { title: '', content: '' }
        this.fetchComplaints()
      } catch (err) {
        this.error = '提交投诉失败: ' + (err.response?.data?.message || err.message)
        console.error('提交投诉失败:', err)
      } finally {
        this.loading = false
      }
    }
  }
}
</script>

<style scoped>
.complaint-list {
  margin: 20px;
  padding: 20px;
  border: 1px solid #eee;
}
</style>
