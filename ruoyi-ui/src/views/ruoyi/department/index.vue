<template>
  <div class="app-container">
<!--    el打头的就是element-ui的样式，整个一个搜索-->
<!-- :model,跟一个数据进行绑定.宽度68px正好填满4个字. ref,引用. inline,内联结构,一行. v-show:如果为true则显示  -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!-- label,显示的名字.  prop,对应的实体属性,这里没有什么实际的意义-->
      <el-form-item label="系部id" prop="departmentId">
        <!-- v-model双向绑定,改一个,两个都变   placeholder,水印  clearable,可清除的标记  @keyup.enter.native,打回车按键与搜索执行同样功能    -->
        <el-input
          v-model="queryParams.departmentId"
          placeholder="请输入系部id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系部名称" prop="departmentName">
        <el-input
          v-model="queryParams.departmentName"
          placeholder="请输入系部名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入电话"
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
<!-- type="primary",按钮样式     -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
<!--行元素，几个按钮-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
<!-- el-button,按钮.  type="primary",蓝颜色的主要按钮  icon图标 plain扁平化风格 size大小 @click点击时间  v-hasPermi权限 -->
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ruoyi:department:add']"
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
          v-hasPermi="['ruoyi:department:edit']"
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
          v-hasPermi="['ruoyi:department:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ruoyi:department:export']"
        >导出</el-button>
      </el-col>

<!--right-toolbar,右侧工具   .sync,与子组件有关      -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
<!--表格和表格里面的列元素-->
<!--:指v-bind:的缩写,把数据绑定过来    -->
<!--  @selection-change选择事件,一点方法就会执行  -->
    <el-table v-loading="loading" :data="departmentList" @selection-change="handleSelectionChange">
<!-- 第一列type="selection"标识单多选     -->
<!-- align="center"  对齐方式   -->
<!--  width:宽度    -->
<!-- prop:对应的是数据库中实体类中的数据     -->
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" min-width="55" prop="id" />-->
      <el-table-column label="系部id" align="center" prop="departmentId" />
      <el-table-column label="系部名称" align="center" prop="departmentName" />
      <el-table-column label="地址" align="left" prop="address" />
      <el-table-column label="电话" align="left" prop="phone" />
      <el-table-column label="状态" align="center" prop="status">
<!-- slot-scope插槽名称       -->
        <template slot-scope="scope">
          <!--  dict-tag,自定义组件,字典标签, sys_normal_disable:字典管理里面的数据       -->
          <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
        </template>
      </el-table-column>
<!--      操作栏-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
<!--   插槽 -->
        <template slot-scope="scope">
<!--   修改 size大小,type样式,icon图标,@click点击     -->
          <!--   权限字符串,有标记可以出现,没有则消失.但是因为前端可以绕开,所有一般前后端都会同时整好.         -->
<!--     scope.row,代表的是当前行的所有数据,不管有没有显示     -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ruoyi:department:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ruoyi:department:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--分页，内置的一个组件-->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改合用的对话框 title,绑定data里面的title-->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
<!-- 表单在里面 :model与表单数据绑定  :rules表单验证规则  -->
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="系部id" prop="departmentId" >
          <el-input v-model="form.departmentId" placeholder="请输入系部id" :disabled="form.id!=null" />
        </el-form-item>
        <el-form-item label="系部名称" prop="departmentName">
          <el-input v-model="form.departmentName" placeholder="请输入系部名称" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="岗位状态" prop="status">
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
<!--      底部-->
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<!--代码-->
<script>
//导入了五个api方法
import { listDepartment, getDepartment, delDepartment, addDepartment, updateDepartment } from "@/api/ruoyi/department";

export default {
  name: "Department",
  // 引入字典
  dicts: ['sys_normal_disable'],
  // 数据中心，当前页面能用到的数据
  data() {
    return {
      // 遮罩层，等待的时候转的那个圈
      loading: true,
      // 选中数组，页面上选的这些编号
      ids: [],
      // 非单个禁用，控制页面上到底是单个还是多个，修改选多个时不可用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件，控制搜索框是否显示
      showSearch: true,
      // 总条数，分页用
      total: 0,
      // 系部表格数据，数组（核心）
      departmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数，点击搜索时要将参数送出去，要有一个地方放，这个对象就是放数据的
      queryParams: {
        // 分页
        pageNum: 1,
        pageSize: 10,
        departmentId: null,
        departmentName: null,
        address: null,
        phone: null,
        status: null,
      },
      // 表单参数，点击按钮后弹出来的框里面的参数
      form: {},
      // 表单校验
      // 规则是个数组,可以有多个,每个都是一个对象,里面 required是否必填 message提示信息 trigger验证时机 blur光标移出
      rules: {
        departmentId: [
          { required: true, message: "系部id不能为空", trigger: "blur" }
        ],
        departmentName: [
          { required: true, message: "系部名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  // 生命周期函数
  created() {
    this.getList();
  },
  // 方法
  methods: {
    /** 查询系部列表 */
    getList() {
      // 先有小圆圈
      this.loading = true;
      listDepartment(this.queryParams).then(response => {
        this.departmentList = response.rows;
        this.total = response.total;
        // 数据加载完后转圈消失
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      //关闭弹窗
      this.open = false;
      //刷新数据
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        departmentId: null,
        departmentName: null,
        address: null,
        phone: null,
        status: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      // ruoyi工具js,reset all the fields and remove validation result
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // 将页数归一,然后进行搜索
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    // 把这个名字传过去,可以把这个方法清空
    resetQuery() {
      // 把前面那个参数清空
      this.resetForm("queryForm");
      // 执行查询
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {

      // 把选中的数组编号遍历全部放到map里面
      this.ids = selection.map(item => item.id)
      //单选和多选的标记
      this.single = selection.length!==1
      // console.log(this.single);
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      //reset清空方法
      this.reset();
      //打开弹窗
      this.open = true;
      //数据中心中变量被修改
      this.title = "添加系部";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      //先把表单清空,弄干净
      this.reset();
      //拿到编辑好的id或者当前被选择的id集合
      const id = row.id || this.ids
      // 前真后假
      getDepartment(id).then(response => {
        // 双向赋予
        this.form = response.data;
        this.open = true;
        this.title = "修改系部";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // $表示内置的方法,用作区分  validate验证 refs引用，form上面那个表单
      this.$refs["form"].validate(valid => {
        // 如果验证通过后
        if (valid) {
          // form.id存在则为修改,不存在则为添加
          if (this.form.id != null) {
            // api跑了一趟数据库
            updateDepartment(this.form).then(response => {
              // 弹出信息
              this.$modal.msgSuccess("修改成功");
              // 关闭弹窗
              this.open = false;
              // 自动刷新
              this.getList();
            });
          } else {
            addDepartment(this.form).then(response => {
              // 弹出信息
              this.$modal.msgSuccess("新增成功");
              // 关闭弹窗
              this.open = false;
              // 自动查找
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      // 真前假后
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除选中的记录?').then(function() {
        return delDepartment(ids);
      }).then(() => {
        //重新更新成最新的列表
        this.getList();
        // 出现信息
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      //下载方法 三个参数(url 查询条件 execl名字)
      this.download('ruoyi/department/export', {
        ...this.queryParams
      }, `department_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
