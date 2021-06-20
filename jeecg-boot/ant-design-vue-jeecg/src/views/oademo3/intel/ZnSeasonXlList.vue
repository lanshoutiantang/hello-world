<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="业务区名称">
              <j-dict-select-tag placeholder="请选择业务区名称" v-model="queryParam.ywqid" dictCode="city"/>
<!--              <j-search-select-tag  placeholder="请选择业务区名称"  v-model="queryParam.ywqid" dict="v_zn_md_yt_ywq-->
<!--,ywqname,ywqid"/>-->
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类编码">
              <j-search-select-tag placeholder="请选择小类编码" v-model="queryParam.xlid" dict="zn_season_xl,xlid,xlid"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类名称">
<!--              <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
              <j-search-select-tag placeholder="请选择小类名称" v-model="queryParam.xlid" dict="zn_season_xl,xlname,xlid"/>
            </a-form-item>
          </a-col>
<!--          <template v-if="toggleSearchStatus">-->
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="季节开始日期">-->
<!--                <a-input placeholder="请输入季节开始日期" v-model="queryParam.begindate"></a-input>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="季节结束日期">-->
<!--                <a-input placeholder="请输入季节结束日期" v-model="queryParam.enddate"></a-input>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
<!--          </template>-->
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
<!--              <a @click="handleToggleSearch" style="margin-left: 8px">-->
<!--                {{ toggleSearchStatus ? '收起' : '展开' }}-->
<!--                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>-->
<!--              </a>-->
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

<!--    &lt;!&ndash; 操作按钮区域 -->
    <div class="table-operator">
<!--      <a-button @click="handleAdd" v-has="'jie:add'" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" v-has="'jie:download'" icon="download" @click="handleExportXls('季节性商品表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'jie:import'" icon="import">导入</a-button>
      </a-upload>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batcDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'jie:down'" style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>

<!--      <template v-if="selectedRowIds.length > 0">
        <a-popconfirm
          v-if="showRemove"
          :title="`确定要删除这 ${selectedRowIds.length} 项吗?`"
          @confirm="trigger('remove')"
        >
          <a-button icon="minus" :disabled="disabled">删除</a-button>
        </a-popconfirm>
        <template v-if="showClearSelection">
          <a-button icon="delete" @click="trigger('clearSelection')">清空选择</a-button>
        </template>
      </template>-->
    </div>

    <!-- table区域-begin -->
<!--    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>-->

<!--      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a v-has="'jie:edit'"  @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'jie:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>-->

<!--    </div>-->

    <j-vxe-table
      toolbar
      :toolbarConfig="toolbarConfig"
      bordered
      row-number
      keep-source
      :height="570"
      :loading="loading"
      :columns="columns"
      :dataSource="dataSource"
      :pagination="pagination"
      :cell-style="columnStyle"
      @pageChange="handlePageChange"
      @edit-closed="handleEditClosed"
      @selectRowChange="handleSelectRowChange"
    >
              <span slot="action" slot-scope=" record">
<!--          <a v-has="'xiaoshou:edit'" @click="handleEdit(record)">编辑</a>-->

                <!--          <a-divider type="vertical" />-->
                <!--          <a-dropdown>-->
                <!--            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>-->
                <!--            <a-menu slot="overlay">-->
                <!--              <a-menu-item>-->
                <a @click="handleDetail(record)">详情</a>
                <a-divider type="vertical" />
                <a-popconfirm title="确定删除吗？" @confirm="handleDele(record)">
                   <a>删除</a>
                </a-popconfirm>
<!--                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
&lt;!&ndash;                  <a v-has="'xiaoshou:delete'">删除</a>&ndash;&gt;
                </a-popconfirm>-->
                <!--              </a-menu-item>-->
                <!--              <a-menu-item>-->
                <!--                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">-->
                <!--                  <a v-has="'xiaoshou:delete'">删除</a>-->
                <!--                </a-popconfirm>-->
                <!--              </a-menu-item>-->
                <!--            </a-menu>-->
                <!--          </a-dropdown>-->
        </span>


    </j-vxe-table>
    <zn-season-xl-modal ref="modalForm" @ok="modalFormOk"></zn-season-xl-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnSeasonXlModal from './modules/ZnSeasonXlModal'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import JDate from '@/components/jeecg/JDate.vue'
  import { deleteAction, downFile,getFileAccessHttpUrl } from '@/api/manage'
  import { getAction, postAction, putAction } from '@api/manage'
  import { JVXETypes } from '@/components/jeecg/JVxeTable'
  // import { JVXETypes } from'./ZnSeasonXlForm'
  import Vue from 'vue'
  // import {validateCheckRule} from "../../../../utils/util";


  export default {
    name: 'ZnSeasonXlList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      JDictSelectTag,
      ZnSeasonXlModal,
        JSearchSelectTag,
    },
    data () {
      return {

          //表头
          columns:[],
          //列设置
          settingColumns:[],
          queryParam: {},
          // 工具栏的按钮配置
          toolbarConfig: {
              // add 新增按钮；remove 删除按钮；clearSelection 清空选择按钮
              btn: ['remove', 'clearSelection']
          },
          // 是否正在加载
          loading: false,
          // 分页器参数
          pagination: {
              // 当前页码
              current: 1,
              // 每页的条数
              pageSize: 12,
              // 可切换的条数
              pageSizeOptions: ['10', '20', '30', '100', '200'],
              // 数据总数（目前并不知道真实的总数，所以先填写0，在后台查出来后再赋值）
              total: 0,
          },
          // 选择的行
          selectedRows: [],
          /* table选中keys*/
          selectedRowKeys: [],
          /* table选中records*/
          selectionRows: [],
          // 数据源，控制表格的数据
          dataSource: [],
          description: '季节性商品表管理页面',
        // 表头
          defColumns: [
          // {
          //   title: '#',
          //   dataIndex: '',
          //   key:'rowIndex',
          //   width:60,
          //   align:"center",
          //   customRender:function (t,r,index) {
          //     return parseInt(index)+1;
          //   }
          // },
          {
            title:'业务区名称',
              sorter: true,
              sortable: true,
            align:"center",
              key: 'ywqid_dictText',
            dataIndex: 'ywqid_dictText'
          },
          {
            title:'小类编码',
              sorter: true,
              sortable: true,
            align:"center",
              key: 'xlid',
            dataIndex: 'xlid'
          },
            {
                title:'小类名称',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'xlname',
                dataIndex: 'xlname'
            },
          {
            title:'季节开始日期',
              sorter: true,
              sortable: true,
              align:"center",
              key: 'begindate',
            dataIndex: 'begindate',
              type: JVXETypes.input,
              validateRules: [
                  // {required: true, message: '请输入季节开始日期'},
                  // // 如果想要更简洁，还可以缩写下面的参数名，只写首字母
                  // {validator: (r, v, c) => validateCheckRule('zn_mmdd', v, c)}
                  {
                      required: false, // 必填
                      message: '请输入季节开始日期' // 显示的文本
                  },
                  {
                      pattern: /^(?:(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/, // 正则
                      message: '请输入MM-DD的日期格式'
                  },
              ]
          },
          {
            title:'季节结束日期',
              sorter: true,
              sortable: true,
              align:"center",
              key: 'enddate',
            dataIndex: 'enddate',
              type: JVXETypes.input,
              validateRules: [
                  // {required: true, message: '请输入季节开始日期'},
                  // // 如果想要更简洁，还可以缩写下面的参数名，只写首字母
                  // {validator: (r, v, c) => validateCheckRule('zn_mmdd', v, c)}
                  {
                      required: false, // 必填
                      message: '请输入季节开始日期' // 显示的文本
                  },
                  {
                      pattern: /^(?:(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/, // 正则
                      message: '请输入MM-DD的日期格式'
                  },
              ]
          },
          {
            title:'季节性首次到货日期',
              sorter: true,
              sortable: true,
              align:"center",
              key: 'arrdate',
            dataIndex: 'arrdate'
          },
          // {
          //   title:'日均销量的折扣比例1',
          //     sorter: true,
          //   align:"center",
          //   dataIndex: 'rate1'
          // },
          // {
          //   title:'日均销量的折扣比例2',
          //      sorter: true,
          //    align:"center",
          //    dataIndex: 'rate2'
          //  },
          // {
          //   title:'季节时间达到的比例1',
          //     sorter: true,
          //   align:"center",
          //   dataIndex: 'period1'
          // },
          // {
          //   title:'季节时间达到的比例2',
          //     sorter: true,
          //   align:"center",
          //   dataIndex: 'period2'
          //  },
          {
            title:'数据更新日期',
              sorter: true,
              sortable: true,
              align:"center",
              key: 'sdate',
            dataIndex: 'sdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
            // {
            //     title:'部类全部机构编码',
            //     align:"center",
            //     dataIndex: 'blOrganAll'
            // },
            // {
            //     title:'课类全部机构编码',
            //     align:"center",
            //     dataIndex: 'klOrganAll'
            // },
          // {
              //   title: '操作',
              //   dataIndex: 'action',
              //   align:"center",
              //   fixed:"right",
              //   width:147,
              //   scopedSlots: { customRender: 'action' }
              // }
              {
                  title: '操作',
                  key: 'action',
                  dataIndex: 'action',
                  type: JVXETypes.slot,
                  fixed: 'right',
                  minWidth: '20px',
                  align: 'center',
                  slotName: 'action',
              }
        ],
        url: {
          list: "/intel/znSeasonXl/list",
          delete: "/intel/znSeasonXl/delete",
          deleteBatch: "/intel/znSeasonXl/deleteBatch",
          exportXlsUrl: "/intel/znSeasonXl/exportXls",
          importExcelUrl: "intel/znSeasonXl/importExcel",
            getData: '/intel/znSeasonXl/getData',
            // 模拟保存单行数据（即时保存）
            saveRow: '/intel/znSeasonXl/immediateSaveRow',
            dele: "/intel/znSeasonXl/dele",
        },
        dictOptions:{},
      }
    },
    created() {
        this.initColumns();
        this.disableMixinCreated=true;
        //this.defColumns = colAuthFilter(this.columns,'bh:');
        this.loadData();
        this.initDictConfig();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
        onSelectChange(selectedRowKeys, selectionRows) {
            this.selectedRowKeys = selectedRowKeys;
            this.selectionRows = selectionRows;
        },
        columnStyle({row, column, rowIndex, columnIndex}) {
            if (column.title == '季节开始日期') {
                return 'background:#E6E6E6;color:blue;cursor: pointer';
            }
            if (column.title == '季节结束日期') {
                return 'background:#E6E6E6;color:blue;cursor: pointer';
            }
        },
      initDictConfig(){
      },
        // 当分页参数变化时触发的事件
        handlePageChange(event) {
            // 重新赋值
            this.pagination.current = event.current
            this.pagination.pageSize = event.pageSize
            // 查询数据
            this.loadData()
        },
        // 加载数据
        loadData() {
            // 封装查询条件
            let formData = {
                ywqid: this.queryParam.ywqid,
                xlid: this.queryParam.xlid,
                pageNo: this.pagination.current,
                pageSize: this.pagination.pageSize
            }
            // 调用查询数据接口
            this.loading = true
            getAction(this.url.getData, formData).then(res => {
                if (res.success) {
                    // 后台查询回来的 total，数据总数量
                    this.pagination.total = res.result.total
                    // 将查询的数据赋值给 dataSource
                    this.dataSource = res.result.records
                    // 重置选择
                    this.selectedRows = []
                } else {
                    this.$error({title: '主表查询失败', content: res.message})
                }
            }).finally(() => {
                // 这里是无论成功或失败都会执行的方法，在这里关闭loading
                this.loading = false
            })
        },
        // 触发单元格删除事件
        handleTableRemove(event) {

            // 把 event.deleteRows 传给后台进行删除（注意：这里不会传递前端逻辑新增的数据，因为不需要请求后台删除）
            console.log('待删除的数据: ', event.deleteRows)
            // 也可以只传ID，因为可以根据ID删除
            let deleteIds = event.deleteRows.map(row => row.id)
            console.log('待删除的数据ids: ', deleteIds)

            // 模拟请求后台删除
            this.loading = true
            window.setTimeout(() => {
                this.loading = false
                this.$message.success('删除成功')
                // 假设后台返回删除成功，必须要调用 confirmRemove() 方法，才会真正在表格里移除（会同时删除选中的逻辑新增的数据）
                event.confirmRemove()
            }, 1000)
        },
        // 单元格编辑完成之后触发的事件
        handleEditClosed(event) {
            // this.$message.info(`""保存失败：`)
            // warn(`"${column.title}"保存失败：` + res.message)
            let {$table, row, column} = event
            console.log(event)
            let field = column.property
            console.log(field)
            let cellValue = row[field]
            console.log(cellValue)
            // 判断单元格值是否被修改
            // $table.
            console.log($table.isUpdateByRow(row, field))
            if ($table.isUpdateByRow(row, field)) {
                // 校验当前行
                $table.validate(row).then((errMap) => {
                    // 校验通过
                    if (!errMap) {
                            // 【模拟保存】
                            let hideLoading = this.$message.loading(`正在保存"${column.title}"`, 0)
                            console.log(hideLoading)
                            console.log('即时保存数据：', row)


                            putAction(this.url.saveRow, row).then(res => {
                                if (res.success) {
                                    this.$message.success(`"${column.title}"保存成功！`)
                                    // 局部更新单元格为已保存状态
                                    $table.reloadRow(row, null, field)
                                } else {
                                    this.$message.warn(`"${column.title}"保存失败：` + res.message)
                                }
                            }).finally(() => {
                                hideLoading()
                            })

                        }

                })
            }
        },
        // 当选择的行变化时触发的事件
        handleSelectRowChange(event) {
            this.selectedRows = event.selectedRows
            // this.$message.info(`""保存失败：`)
        },
        initColumns(){
            //权限过滤（列权限控制时打开，修改第二个参数为授权码前缀）
            //this.defColumns = colAuthFilter(this.defColumns,'testdemo:');

            var key = this.$route.name+":colsettings";
            let colSettings= Vue.ls.get(key);
            if(colSettings==null||colSettings==undefined){
                let allSettingColumns = [];
                this.defColumns.forEach(function (item,i,array ) {
                    allSettingColumns.push(item.dataIndex);
                })
                this.settingColumns = allSettingColumns;
                this.columns = this.defColumns;

            }else{
                this.settingColumns = colSettings;
                const cols = this.defColumns.filter(item => {
                    if(item.key =='rowIndex'|| item.dataIndex=='action'){
                        return true;
                    }
                    if (colSettings.includes(item.dataIndex)) {
                        return true;
                    }
                    return false;
                })
                this.columns =  cols;
            }
        },
        handleDele(record){
            console.log(record)
            console.log(record.row.id)
            // console.log(record.id)
            let parmas ={id:record.row.id}
            deleteAction(this.url.dele,parmas).then((res)=>{
                if (res.success) {
                    this.$message.success(res.message);
                    this.loadData();
                } else {
                    this.$message.warning(res.message);
                }
            })

        },
        batcDel: function () {
            this.selectedRowIds = selectedRows.map(row => row.id)
            if(!this.url.deleteBatch){
                this.$message.error("请设置url.deleteBatch属性!")
                return
            }
            if (this.selectedRowKeys.length <= 0) {
                this.$message.warning('请选择一条记录！');
                return;
            } else {
                var ids = "";
                for (var a = 0; a < this.selectedRowKeys.length; a++) {
                    ids += this.selectedRowKeys[a] + ",";
                }
                console.log(ids)
                var that = this;
                this.$confirm({
                    title: "确认删除",
                    content: "是否删除选中数据?",
                    onOk: function () {
                        that.loading = true;
                        deleteAction(that.url.deleteBatch, {ids: ids}).then((res) => {
                            if (res.success) {
                                that.$message.success(res.message);
                                that.loadData();
                                that.onClearSelected();
                            } else {
                                that.$message.warning(res.message);
                            }
                        }).finally(() => {
                            that.loading = false;
                        });
                    }
                });
            }
        },

    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>