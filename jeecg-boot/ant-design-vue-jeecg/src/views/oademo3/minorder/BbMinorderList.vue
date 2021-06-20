<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="业态">
<!--              <a-input placeholder="请输入业态" v-model="queryParam.ytid"></a-input>-->
              <j-search-select-tag placeholder="请选择业态" v-model="queryParam.yt" dict="bbyt,yt,yt"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="门店名称">
<!--              <a-input placeholder="请输入门店名称" v-model="queryParam.shopname"></a-input>-->
              <j-search-select-tag placeholder="请选择门店名称" v-model="queryParam.shopid" dict="v_shopname1,shop,shopid"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="商品编码">
              <a-input placeholder="请输入商品编码" v-model="queryParam.goodsidid"></a-input>
            </a-form-item>
          </a-col>
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

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
<!--      <a-button type="primary" icon="download" @click="handleExportXls('商品最小订货量')">导出</a-button>-->
<!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
<!--        <a-button type="primary" icon="import">导入</a-button>-->
<!--      </a-upload>-->
<!--      <a-dropdown v-if="selectedRowKeys.length > 0">-->
<!--        <a-menu slot="overlay">-->
<!--          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>-->
<!--        </a-menu>-->
<!--        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>-->
<!--      </a-dropdown>-->
    </div>

    <!-- table区域-begin -->
<!--    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
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
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>-->
    <j-vxe-table
      toolbar
      :toolbarConfig="toolbarConfig"
      bordered
      row-number
      keep-source
      :height="770"
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
           <a @click="handleDetail(record)">详情</a>
<!--           <a-divider type="vertical" />-->
<!--           <a-popconfirm title="确定删除吗？" @confirm="handleDele(record)">-->
<!--              <a>删除</a>-->
<!--           </a-popconfirm>-->
        </span>


    </j-vxe-table>
    <bb-minorder-modal ref="modalForm" @ok="modalFormOk"></bb-minorder-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import BbMinorderModal from './modules/BbMinorderModal'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import JDictSelectTag from '@/components/dict/JDictSelectTag.vue'
  import { deleteAction, downFile,getFileAccessHttpUrl } from '@/api/manage'
  import { getAction, postAction, putAction } from '@api/manage'
  import { JVXETypes } from '@/components/jeecg/JVxeTable'
  import Vue from 'vue'


  export default {
    name: 'BbMinorderList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JDictSelectTag,
        JSearchSelectTag,
      BbMinorderModal
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
              pageSize: 15,
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
          description: '商品最小订货量管理页面',
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
            title:'门店编码',
            align:"center",
              sortable: true,
              key: 'shopid',
            dataIndex: 'shopid'
          },
          {
            title:'门店名称',
            align:"center",
              sortable: true,
              key: 'shopname',
            dataIndex: 'shopname'
          },
          {
            title:'商品编码',
            align:"center",
              sortable: true,
              key: 'goodsid',
            dataIndex: 'goodsid'
          },
          {
            title:'商品名称',
            align:"center",
              sortable: true,
              key: 'name',
            dataIndex: 'name'
          },
          {
            title:'件装数',
            align:"center",
              sortable: true,
              key: 'conversion',
            dataIndex: 'conversion'
          },
          {
            title:'最小订货量',
            align:"center",
              sortable: true,
              key: 'minorderqty',
              type: JVXETypes.input,
            dataIndex: 'minorderqty'
          },
          {
            title:'最小库存量',
            align:"center",
              sortable: true,
              key: 'minstockqty',
              type: JVXETypes.input,
            dataIndex: 'minstockqty'
          },
          {
            title: '操作',
              key: 'action',
            dataIndex: 'action',
              type: JVXETypes.slot,
            align:"center",
            fixed:"right",
            width:147,
              slotName: 'action',
          }
        ],
        url: {
          list: "/minorder/bbMinorder/list",
          delete: "/minorder/bbMinorder/delete",
          deleteBatch: "/minorder/bbMinorder/deleteBatch",
          exportXlsUrl: "/minorder/bbMinorder/exportXls",
          importExcelUrl: "minorder/bbMinorder/importExcel",
            getData: 'minorder/bbMinorder/getData',
            // 模拟保存单行数据（即时保存）
            saveRow: 'minorder/bbMinorder/immediateSaveRow',
            dele: "minorder/bbMinorder/dele",
          
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
            if (column.property == 'minorderqty') {
                return 'background:#E6E6E6;color:blue;cursor: pointer';
            }
            if (column.property == 'minstockqty') {
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
                yt: this.queryParam.yt,
                shopid: this.queryParam.shopid,
                goodsidid : this.queryParam.goodsidid,
                goodsid: this.queryParam.goodsid,
                pageNo: this.pagination.current,
                pageSize: this.pagination.pageSize
            }
            // 调用查询数据接口
            if(this.queryParam.goodsidid != null){
                formData.goodsid = '*' + this.queryParam.goodsidid + '*';
            }
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
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>