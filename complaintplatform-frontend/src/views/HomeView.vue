
<template>
  <main>
    <div style="font-size:50px; font-weight: bold; color: darkseagreen" >加油</div>
    <div>
      {{data.a}}
      <input type="text" v-model="data.name" />
      <span v-if="data.name === 'niubi'">niubi </span>
      <span v-if="data.name === 'buniubi'">buniubi</span>
      <span v-else>niu</span>
    </div>

    <div>
      <select style="width:200px">
        <option v-for="item in data.fruits" >{{ item }}</option>
      </select>
    </div>

    <div>
      <button @click="click"  :style="data.box">
        点我有奇效
      </button>
      <el-button @click="click" type="primary"  style="margin: 30px">这是element-plus的按钮</el-button>
      <img :src="data.img" style="width:200px; height:200px;">
    </div>

    <div style="font-size: 20px">
      <!-- 由于SVG图标默认不携带任何属性 -->
      <!-- 你需要直接提供它们 -->
      <Edit style="width: 1em; height: 1em; margin-right: 8px" />
      <Share style="width: 1em; height: 1em; margin-right: 8px" />
      <Delete style="width: 1em; height: 1em; margin-right: 8px" />
      <Search style="width: 1em; height: 1em; margin-right: 8px" />


      <el-icon>

        <View />
      </el-icon> 100

      <el-button type="danger" icon="Delete" circle></el-button>
      <el-input
          v-model="data.fruits"
          style="margin-left:50px;width: 500px"
          :rows="2"
          type="textarea"
          placeholder="Please input"
          :suffix-icon="Search"
      />

    </div>

  </main>

</template>




<script setup>
import {onMounted, reactive} from "vue";
import {Delete, Edit, Search, Share, View} from "@element-plus/icons-vue";

const data = reactive(
    {
      a: 123456,
      name: "niubi",
      fruits: [1, 2, 3, 4, 5, 6, 7, 8, 9],
      box: {
        width: '200px',
        height: '100px',
        backgroundColor: "yellow",
      },
      img:'https://img1.baidu.com/it/u=1466043940,779429976&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500'
    }
)
onMounted(()=>{
  console.log("页面加载完成")
})
const show = (value) => {
  alert(value)
}
const click = () => {

  alert("niubi")
}
</script>
