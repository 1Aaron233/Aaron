<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级id" prop="classId">
        <el-input
          v-model="queryParams.classId"
          placeholder="请输入班级id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="班级名称" prop="className">
        <el-input
          v-model="queryParams.className"
          placeholder="请输入班级名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属系部" prop="departmentId">
        <!--  下拉列表  label,看到的,正常 异常 value,一点就取到的那个值 0 1 key,v-for循环时所需要的唯一值-->
        <el-select v-model="queryParams.departmentId" placeholder="所属系部" clearable>
          <!--  v-for遍历哪里的值         -->
          <el-option
            v-for="item in departmentList"
            :key="item.departmentId"
            :label="item.departmentName"
            :value="item.departmentId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="班主任姓名" prop="headMaster" label-width="90px">
        <el-input
          v-model="queryParams.headMaster"
          placeholder="请输入班主任姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <!--  下拉列表  label,看到的,正常 异常 value,一点就取到的那个值 0 1 key,v-for循环时所需要的唯一值-->
        <el-select v-model="queryParams.status" placeholder="状态" clearable>
          <!--  v-for遍历哪里的值         -->
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <!-- el-date-picker 时间框 value-format格式 dateRange日期范围，一个数组-->
        <el-date-picker
          v-model="dateRange"
          style="width: 340px"
          value-format="yyyy-MM-dd HH:mm:ss"
          type="datetimerange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ruoyi:classes:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ruoyi:classes:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ruoyi:classes:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ruoyi:classes:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="班级id" align="center" prop="classId" />
      <el-table-column label="班级名称" align="center" prop="className" />
      <el-table-column label="所属系部" align="center" prop="department.departmentName" />
      <el-table-column label="班主任姓名" align="center" prop="headMaster" />
      <el-table-column label="班主任联系电话" align="center" prop="headMasterPhone" />
      <el-table-column label="班级类型" align="center" prop="classType" >
      <!-- slot-scope插槽名称       -->
      <template slot-scope="scope">
        <!--  dict-tag,自定义组件,字典标签, sys_normal_disable:字典管理里面的数据       -->
        <dict-tag :options="dict.type.ruoyi_class_type" :value="scope.row.classType"/>
      </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <!-- slot-scope插槽名称       -->
        <template slot-scope="scope">
          <!--  dict-tag,自定义组件,字典标签, sys_normal_disable:字典管理里面的数据       -->
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注信息" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ruoyi:classes:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            :disabled="scope.row.classId=='1'?true:false"
            v-show="scope.row.classId=='2'?false:true"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ruoyi:classes:remove']"
          >删除</el-button>
        </template>
        <el-button
          size="mini"
          type="text"
          icon="el-icon-lock"
          @click="handleLock(scope.row)"
          v-hasPermi="['ruoyi:classes:lock']"
        >锁定</el-button>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改班级信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级id" prop="classId">
          <el-input v-model="form.classId" placeholder="请输入班级id" :disabled="form.id!=null" />
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="form.className" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="所属系部" prop="department.departmentId">
          <!--  下拉列表  label,看到的,正常 异常 value,一点就取到的那个值 0 1 key,v-for循环时所需要的唯一值-->
          <el-select v-model="form.departmentId" placeholder="所属系部" clearable>
            <!--  v-for遍历哪里的值         -->
            <el-option
              v-for="item in departmentList"
              :key="item.departmentId"
              :label="item.departmentName"
              :value="item.departmentId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班主任姓名" prop="headMaster">
          <el-input v-model="form.headMaster" placeholder="请输入班主任姓名" />
        </el-form-item>
        <el-form-item label="班主任电话" prop="headMasterPhone">
          <el-input v-model="form.headMasterPhone" placeholder="请输入班主任联系电话" />
        </el-form-item>
        <el-form-item label="班级类型" prop="classType">
          <!-- el-radio-group单选组 v-for字典循环        -->
          <el-radio-group v-model="form.classType">
            <el-radio
              v-for="dict in dict.type.ruoyi_class_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <!-- el-radio-group单选组 v-for字典循环        -->
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClasses, getClasses, delClasses, addClasses, updateClasses } from "@/api/ruoyi/classes";
import {listDepartmentAll} from "../../../api/ruoyi/department";

export default {
  name: "Classes",
  dicts: ['sys_normal_disable','ruoyi_class_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 班级信息表格数据
      classesList: [],
      // 系部信息表格数据
      departmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classId: null,
        className: null,
        departmentId: null,
        headMaster: null,
        headMasterPhone: null,
        classType: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classId: [
          { required: true, message: "班级id不能为空", trigger: "blur" }
        ],
        className: [
          { required: true, message: "班级名称不能为空", trigger: "blur" }
        ],
        departmentId: [
          { required: true, message: "所属班级编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDepartmentList();
  },
  methods: {
    /** 查询班级信息列表 */
    getList() {
      this.loading = true;
      listClasses(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.classesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getDepartmentList() {//获取所有系部信息
        listDepartmentAll().then(
          response=> {
            this.departmentList = response.rows;
          }
        )
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        classId: null,
        className: null,
        departmentId: null,
        headMaster: null,
        headMasterPhone: null,
        classType: "0",
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加班级信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClasses(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改班级信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClasses(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClasses(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除班级信息编号为"' + ids + '"的数据项？').then(function() {
        return delClasses(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi/classes/export', {
        ...this.queryParams
      }, `classes_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
