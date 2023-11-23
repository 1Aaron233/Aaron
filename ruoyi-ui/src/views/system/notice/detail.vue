<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="公告标题" prop="noticeTitle">
            <el-input v-model="form.noticeTitle" placeholder="请输入公告标题" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公告类型" prop="noticeType">
            <el-select v-model="form.noticeType" placeholder="请选择公告类型">
              <el-option
                v-for="dict in dict.type.sys_notice_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="状态">
            <el-radio-group v-model="form.status">
              <el-radio
                v-for="dict in dict.type.sys_notice_status"
                :key="dict.value"
                :label="dict.value"
              >{{dict.label}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容">
            <editor v-model="form.noticeContent" :min-height="192"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import {getNotice} from "../../../api/system/notice";

export default {
  name: "Notice",
  dicts: ['sys_notice_status', 'sys_notice_type'],
  data() {
    return {
      // 表单参数
      form: {
        noticeId: undefined,
        noticeTitle: undefined,
        noticeType: undefined,
        noticeContent: undefined,
        status: "0"
      }
    };
  },
  created() {
    this.getInfo();
  },
  methods: {
    /** 查询公告列表 */
    getInfo() {
      const noticeId = this.$route.params && this.$route.params.noticeId
      getNotice(noticeId).then(response => {
        this.form = response.data;
      });
    }
  }
};
</script>
