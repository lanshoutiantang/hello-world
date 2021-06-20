<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <!--          <a-col :xl="10" :lg="11" :md="12" :sm="24">-->
          <!--            <a-form-item label="日期">-->
          <!--              <j-date placeholder="请选择开始日期" class="query-group-cust" v-model="queryParam.sdate_begin"></j-date>-->
          <!--              <span class="query-group-split-cust"></span>-->
          <!--              <j-date placeholder="请选择结束日期" class="query-group-cust" v-model="queryParam.sdate_end"></j-date>-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->
          <a-col :xl="5" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:shopname'" label="门店名称">
              <!--              <a-input placeholder="请输入门店编码" v-model="queryParam.shopid"></a-input>-->
<!--              <j-search-select-tag  placeholder="请选择门店名称"  v-model="queryParam.shopid" dict="tb_organ,name,organ"/>-->
              <j-search-select-tag  placeholder="请选择门店名称"  v-model="queryParam.shopid" dict="v_shopname1,shop,shopid"/>
            </a-form-item>
          </a-col>
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:blname'" label="部类名称">
              <j-search-select-tag  placeholder="请选择部类名称" v-model="queryParam.blid" dict="blcode_name,blname,blid"/>
              <!--              <j-popup placeholder="请选择部类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
            </a-form-item>
          </a-col>
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item v-has="'bh:klname'" label="课类名称">
              <j-search-select-tag  placeholder="请选择课类名称" v-model="queryParam.cgid" dict="klcode_name,klname,klid"/>
              <!--              <j-popup placeholder="请选择课类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
            </a-form-item>
          </a-col>
          <!--          <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
          <!--            <a-form-item v-has="'bh:meiyong'" label="课类名称">-->
          <!--              <j-search-select-tag v-has="'bh:meiyong'" placeholder="请选择课类名称" v-model="queryParam.klid" dict="klcode_name,klname,klid"/>-->
          <!--              &lt;!&ndash;              <j-popup placeholder="请选择课类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>&ndash;&gt;-->
          <!--            </a-form-item>-->
          <!--          </a-col>-->
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类名称">
              <!--              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
              <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>
            </a-form-item>
          </a-col>
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类编码">
                            <a-input placeholder="请输入小类编码" v-model="queryParam.xl"></a-input>
<!--              <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
            </a-form-item>
          </a-col>
          <!--          <template v-if="toggleSearchStatus">-->
          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="商品名称">
              <j-popup placeholder="请选择商品名称" v-model="queryParam.goodsname" code="zn_goodsid" org-fields="goodsname" dest-fields="goodsname" :field="getPopupField('goodsname')"/>
            </a-form-item>
          </a-col>

          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="供应商名称">
              <!--              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
              <j-popup placeholder="请选择供应商名称" v-model="queryParam.vendername" code="bbzn_vender" org-fields="vendername" dest-fields="vendername" :field="getPopupField('vendername')"/>
            </a-form-item>
          </a-col>

          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="商品类别">
              <!--              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
<!--              <j-popup placeholder="请选择商品类别" v-model="queryParam.classtype" code="bbzn_vender" org-fields="vendername" dest-fields="vendername" :field="getPopupField('vendername')"/>-->
              <j-dict-select-tag v-model="queryParam.classtype" placeholder="请选择商品类别" dictCode="goods_classtype"/>
            </a-form-item>
          </a-col>


          <!--          </template>-->

          <a-col :xl="4" :lg="7" :md="8" :sm="24">
            <a-form-item label="补货标识">
              <!--              <j-popup placeholder="请选择商品名称" v-model="queryParam.goodsname" code="zn_goodsid" org-fields="goodsname" dest-fields="goodsname" :field="getPopupField('goodsname')"/>-->
              <j-dict-select-tag v-model="queryParam.flag" placeholder="请选择补货标识" dictCode="flag0"/>
            </a-form-item>
          </a-col>
          <a-col :xl="3" :lg="7" :md="8" :sm="24">
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
      <a-button @click="handleAdd"   type="primary" icon="plus">新增</a-button>
      <!--      v-has="'bh:download'"-->
      <a-button type="primary" icon="download"  @click="handleExportXls('门店补货商品表')">导出</a-button>
      <a-button type="primary" icon="import" :disabled="disabled"  @click="submitBh">提交补货</a-button>
<!--      <a-button type="primary" icon="download"  @click="buHuo1ExportXls('门店补货商品表')">导出一</a-button>-->
<!--      <a-button type="primary" icon="download"  @click="buHuo2ExportXls('门店补货商品表')">导出二</a-button>-->
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'bh:import'" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'bh:down'"  style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>
    <div v-show="showSubmit">
      <a-spin  tip="Loading...">
        <div  class="spin-content">
          补货数据正在提交，请勿进行其他操作...
        </div>
      </a-spin>
    </div>
    <!-- table区域-begin -->
    <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <!--<i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>-->
      <a-popover title="自定义列" trigger="click" placement="leftBottom">
        <template slot="content">
          <a-checkbox-group @change="onColSettingsChange" v-model="settingColumns" :defaultValue="settingColumns">
            <a-row>
              <template v-for="(item,index) in defColumns">
                <template v-if="item.key!='rowIndex'&&item.dataIndex!='action'">
                  <a-col :span="12"><a-checkbox :value="item.dataIndex">{{ item.title }}</a-checkbox></a-col>
                </template>
              </template>
              <span style="float:right">
              <a-button type="primary" @click="changeColumns">确定</a-button>
              </span>
            </a-row>
          </a-checkbox-group>
        </template>
        <a><a-icon type="setting" />自定义列</a>
      </a-popover>
      </div>

     <!-- <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"z
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="tableData"
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
          <a v-has="'bh:edit'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'bh:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>
      </a-table>-->
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

    <zn-goods-modal ref="modalForm" @ok="modalFormOk"></zn-goods-modal>
    <zn-goods-modal1 ref="modalForm1" @ok="modalFormOk"></zn-goods-modal1>

  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnGoodsModal from './modules/ZnGoodsModal'
  import ZnGoodsModal1 from './modules/ZnGoodsModal1'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import {colAuthFilter} from "../../../utils/authFilter";
  import JDate from '@/components/jeecg/JDate.vue'
  import { deleteAction, downFile,getFileAccessHttpUrl } from '@/api/manage'
  import { getAction, postAction, putAction } from '@api/manage'
  import { JVXETypes } from '@/components/jeecg/JVxeTable'
  import Vue from 'vue'
  import { colAuthFilterJEditableTable } from "@/utils/authFilter"
  import { ACCESS_TOKEN } from "@/store/mutation-types"
  import { filterObj } from '@/utils/util';
  export default {
      inject:["reload"],
    name: 'ZnGoodsList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JDate,
        JSearchSelectTag,
        ZnGoodsModal,
        ZnGoodsModal1
    },
    data () {
      return {
          disabled:false,
          isChange: false,
          showSubmit:false,

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
              pageSize: 100,
              // 可切换的条数
              pageSizeOptions: ['10', '20', '30', '100', '200'],
              // 数据总数（目前并不知道真实的总数，所以先填写0，在后台查出来后再赋值）
              total: 0,
          },
          // 选择的行
          selectedRows: [],
          // 数据源，控制表格的数据
         dataSource: [],
        description: '门店补货商品表管理页面',

        defColumns: [
            /*{
                title: '#',
                key: 'rowIndex',
                dataIndex: '',
                width: 60,
                align: "center",
                customRender: function (t, r, index) {
                    return parseInt(index) + 1;
                }
            },*/
          {

            title:'门店名称',
            sorter: true,
              sortable: true,
            align:"center",
            key: 'shopid_dictText',
              dataIndex: 'shopid_dictText'

          },
            {
                title:'部类名称',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'blid_dictText',
                dataIndex: 'blid_dictText'

            },
            {
                title:'课类名称',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'cgid_dictText',
                dataIndex: 'cgid_dictText'
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

                title:'小类编码',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'xlid',
                dataIndex: 'xlid'

            },
            {
                title:'商品名称',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'goodsname',
                dataIndex: 'goodsname'
            },
            {
                title:'供应商编码',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'venderid',
                dataIndex: 'venderid'
            },
            {
                title:'供应商名称',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'vendername',
                dataIndex: 'vendername'
            },
            {
                title:'送货类型',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'sendType',
                dataIndex: 'sendType'
            },
          {
            title:'补货类型',
              sorter: true,
              sortable: true,
            align:"center",
              key: 'classtype',
              dataIndex: 'classtype'
          },
          {
            title:'昨日日终库存数量',
              sorter: true,
              sortable: true,
            align:"center",
              key: 'closeqty',
              dataIndex: 'closeqty'
          },
            {
                title:'自动补货数量',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'qty',
                dataIndex: 'qty'

            },
            {
                title:'最小订货量',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'minorder',
                dataIndex: 'minorder'

            },
            {
                title:'手动补货数量',
                sorter: true,
                sortable: true,
                align:"center",
                key: 'manualQty',
                dataIndex: 'manualQty',
                type: JVXETypes.input,
                // :disabled="isDisabledAuth('bh:bukebianji')"

            },
          {
            title:'补货标识',
              sorter: true,
              sortable: true,
            align:"center",
              key: 'flag_dictText',
              dataIndex: 'flag_dictText'
          },
            {
                title:'备注',
                sortable:true,
                align:"center",
                key:'comment',
                dataIndex:'comment',
                type: JVXETypes.selectMultiple

            },

          {
            title:'更新日期时间',
            align:"center",
              sorter: true,
              sortable: true,
              order: 'desc',
              key: 'sdate',
              dataIndex: 'sdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
            // {
            //     title: '操作',
            //     key: 'action',
            //     type: JVXETypes.slot,
            //     fixed: 'right',
            //     minWidth: '20px',
            //     align: 'center',
            //     slotName: 'action',
            // }
            {
                title: '操作',
                key: 'action',
                dataIndex: 'action',
                type: JVXETypes.slot,
                fixed: 'right',
                minWidth: '100px',
                align: 'center',
                slotName: 'action',

            }


        ],

        url: {
          list: "/intel/znGoods/list",
          delete: "/intel/znGoods/delete",
          deleteBatch: "/intel/znGoods/deleteBatch",
          exportXlsUrl: "/intel/znGoods/exportXls",
          buHuo1ExportXls: "/intel/znGoods/buHuo1ExportXls",
          buHuo2ExportXls: "/intel/znGoods/buHuo2ExportXls",
          importExcelUrl: "intel/znGoods/importExcel",
          getData: '/intel/znGoods/getData',
            // 模拟保存单行数据（即时保存）
          saveRow: '/intel/znGoods/immediateSaveRow',
            savaComment: '/intel/znGoods/immediateSavaComment',
            submitBh: '/intel/znGoods/submitBh',


        },
        dictOptions:{},
      }
    },


    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
      created() {
          this.defColumns = colAuthFilterJEditableTable(this.defColumns,'zngoods:goods:');
          this.initColumns();
          this.disableMixinCreated=true;
          //this.defColumns = colAuthFilter(this.columns,'bh:');
          this.loadData();
          this.initDictConfig();
      },
    methods: {
        submitBh(event){
            var submitBtn = event.currentTarget
            const that = this
            // 封装查询条件

            let formData = {
                // shopid: this.queryParam.shopid,
                // blid: this.queryParam.blid,
                // cgid: this.queryParam.cgid,
                // xlname: this.queryParam.xlname,
                // goodsname: this.queryParam.goodsname,
                // flag: this.queryParam.flag,
                shopid: this.queryParam.shopid,
                blid: this.queryParam.blid,
                cgid: this.queryParam.cgid,
                xlname: this.queryParam.xlname,
                xl: this.queryParam.xl,
                xlid: this.queryParam.xlid,
                goodsname: this.queryParam.goodsname,
                vendername: this.queryParam.vendername,
                classtype: this.queryParam.classtype,
                flag: this.queryParam.flag,
            }
            if(this.queryParam.xl != null){
                this.queryParam.xlid ='*' + this.queryParam.xl + '*';
            }
            this.$confirm({
                title: '再次确定提交',
                content: '本次上传补货数据： ' + this.pagination.total + '条',
                onOk() {
                    that.showSubmit = true;
                    submitBtn.disabled = true;
                    that.$message.loading('正在提交补货数据，请勿进行其他操作！')
                    that.axios.put(that.url.submitBh,formData).then(res => {
                        if (res.success) {
                            that.$message.success("数据传送成功！");
                            submitBtn.disabled = false;
                            that.showSubmit = false;
                        }else {
                            that.$message.error(res.message);
                            submitBtn.disabled = false;
                            that.showSubmit = false;
                        }
                    })
                },
                onCancel() {
                },
            });

        },
        changeColumns(){
            this.reload();
        },

        columnStyle({row, column, rowIndex, columnIndex}) {
            if (column.property == 'manualQty') {
                return 'background:#E6E6E6;color:blue;cursor: pointer';
            }
            if (column.property == 'comment') {
                return 'background:#E6E6E6;color:blue;cursor: pointer';
            }

        },
      initDictConfig(){
      },
        buHuo1ExportXls(fileName){
            console.log("导出参数")
            if(!fileName || typeof fileName != "string"){
                fileName = "导出文件"
            }
            let param = {...this.queryParam};
            if(this.selectedRowKeys && this.selectedRowKeys.length>0){
                param['selections'] = this.selectedRowKeys.join(",")
            }
            console.log("导出参数",param)
            downFile(this.url.buHuo1ExportXls,param).then((data)=>{
                if (!data) {
                    this.$message.warning("文件下载失败")
                    return
                }
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    window.navigator.msSaveBlob(new Blob([data],{type: 'application/vnd.ms-excel'}), fileName+'.xls')
                }else{
                    let url = window.URL.createObjectURL(new Blob([data],{type: 'application/vnd.ms-excel'}))
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download', fileName+'.xls')
                    document.body.appendChild(link)
                    link.click()
                    document.body.removeChild(link); //下载完成移除元素
                    window.URL.revokeObjectURL(url); //释放掉blob对象
                    console.log(res)
                }
            })

        },
        buHuo2ExportXls(fileName){
            console.log("导出参数")
            if(!fileName || typeof fileName != "string"){
                fileName = "导出文件"
            }
            let param = {...this.queryParam};
            if(this.selectedRowKeys && this.selectedRowKeys.length>0){
                param['selections'] = this.selectedRowKeys.join(",")
            }
            console.log("导出参数",param)
            downFile(this.url.buHuo2ExportXls,param).then((data)=>{
                if (!data) {
                    this.$message.warning("文件下载失败")
                    return
                }
                if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    window.navigator.msSaveBlob(new Blob([data],{type: 'application/vnd.ms-excel'}), fileName+'.xls')
                }else{
                    let url = window.URL.createObjectURL(new Blob([data],{type: 'application/vnd.ms-excel'}))
                    let link = document.createElement('a')
                    link.style.display = 'none'
                    link.href = url
                    link.setAttribute('download', fileName+'.xls')
                    document.body.appendChild(link)
                    link.click()
                    document.body.removeChild(link); //下载完成移除元素
                    window.URL.revokeObjectURL(url); //释放掉blob对象
                    console.log(res)
                }
            })
        },// 当分页参数变化时触发的事件
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
                shopid: this.queryParam.shopid,
                blid: this.queryParam.blid,
                cgid: this.queryParam.cgid,
                xlname: this.queryParam.xlname,
                xl: this.queryParam.xl,
                xlid: this.queryParam.xlid,
                goodsname: this.queryParam.goodsname,
                vendername: this.queryParam.vendername,
                classtype: this.queryParam.classtype,
                flag: this.queryParam.flag,
                pageNo: this.pagination.current,
                pageSize: this.pagination.pageSize
            }
            // 调用查询数据接口
            console.log(this.queryParam.xl)
            console.log(this.queryParam.xlid)
            if(this.queryParam.xl != null){
                this.queryParam.xlid ='*' + this.queryParam.xl + '*'; 
            }
            this.loading = true
            console.log(formData)
            console.log(formData.flag)
            console.log(formData.classtype)
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
/*            console.log(cellValue)
            console.log( this.row.minorder)
            console.log( row.shopid)
            console.log( row.minorder)
            console.log(column.title)
            console.log(column.property)
            console.log(event.column.title)
            console.log(event.column.property)
            console.log(event.title)*/
            // 判断单元格值是否被修改
            // $table.
            console.log($table.isUpdateByRow(row, field))
            if ($table.isUpdateByRow(row, field)) {
                // 校验当前行
                $table.validate(row).then((errMap) => {
                    // 校验通过
                    if (!errMap) {
                        var that = this
                        // 判断是否手动补货数量字段'manualQty'
                        if(column.property === 'manualQty' ){
                        if(row.manualQty >= 0){
                            if(row.manualQty%row.minorder == 0){

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

                            } else {
                               /* this.$confirm({
                                    title: '是否继续保存',
                                    content: '建议补货数量最好是该最小补货量'+row.minorder+'的整数倍，提示：暂时不敢保证最小补货量是正确的',
                                    onOk: function () {
                                        putAction(that.url.saveRow, row).then(res => {
                                            if (res.success) {
                                                that.$message.success(`"${column.title}"保存成功！`)
                                                // 局部更新单元格为已保存状态
                                                $table.reloadRow(row, null, field)
                                            } else {
                                                that.$message.warn(`"${column.title}"保存失败：` + res.message)
                                            }
                                        })
                                    },
                                    onCancel() {
                                       that.loadData()
                                    },
                                    // on
                                })*/

                                // let hideLoading = this.$message.loading('建议补货数量最好是该最小补货量'+row.minorder+'的整数倍，提示：暂时不敢保证最小补货量是正确的')
                                // console.log(hideLoading)
                                // console.log('即时保存数据：', row)
                                this.$refs.modalForm1.edit(row);
                                // this.$refs.modalForm1.edit(row).finally(this.loadData());
                                // this.$refs.modalForm1.title = "编辑";
                                this.$refs.modalForm1.disableSubmit = false;
                                this.loadData()



                            }

                        }else {
                            // this.$notification.config({
                            //     message: '提示',
                            //     description: `您尚未归属部门,请确认账号信息`,
                            //     duration:3
                            // })
                            // this.$confirm({
                            //     title: '保存失败',
                            //     content: '建议补货数量大于等于0'
                            // })
                            // handleEdit: function (record) {
                            // console.log(row)
                            // function f() {this.$refs.modalForm1.edit(row);
                            //     // this.$refs.modalForm1.edit(row).finally(this.loadData());
                            //     // this.$refs.modalForm1.title = "编辑";
                            //     this.$refs.modalForm1.disableSubmit = false;
                            // }
                            this.$refs.modalForm1.edit(row);
                            // this.$refs.modalForm1.edit(row).finally(this.loadData());
                            // this.$refs.modalForm1.title = "编辑";
                            this.$refs.modalForm1.disableSubmit = false;
                            this.loadData()

                            // this.$nextTick(()=>{
                            //     this.loadData()
                            // })
                            // this.loadData()
                            // },
                            // this.$error({
                            //     title: '保存失败',
                            //     content: '建议补货数量大于等于0'
                            // })

                                // Promise.resolve without an argument throws an error in LG WebOS 2
                            //     var promise = Promise.resolve(undefined);
                            // promise.then(this.loadData());

                            // this.$info('555555')
                            // this.$message.info('保存失败，建议补货数量大于等于0 ')
                            // this.loadData()
                            // this.$info('保存失败，建议补货数量大于等于0 并且必须是该最小补货量'+row.minorder+'的整数倍，提示：暂时不敢保证最小补货量是正确的')
                            // this.$message.info()
                            // console.log(this.$info)
                            // this.$info.bind('555555')
                            // this.$info('小心！','error')
                        }


                        }
                        // 判断是否为备注字段'comment'
                        if(column.property === 'comment'){
                            let hideLoading = this.$message.loading(`正在保存"${column.title}"`, 0)
                            console.log(hideLoading)
                            console.log('即时保存数据：', row)

                            putAction(this.url.savaComment,row).then(res => {
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

                    }
                })
            }
        },

        // 当选择的行变化时触发的事件
        handleSelectRowChange(event) {
            this.selectedRows = event.selectedRows
            // this.$message.info(`""保存失败：`)
        },
        //列设置更改事件
        onColSettingsChange (checkedValues) {
            var key = this.$route.name+":colsettings";
            Vue.ls.set(key, checkedValues, 7 * 24 * 60 * 60 * 1000)
            this.settingColumns = checkedValues;
            const cols = this.defColumns.filter(item => {
                if(item.key =='rowIndex'|| item.dataIndex=='action'){
                    return true
                }
                if (this.settingColumns.includes(item.dataIndex)) {
                    return true
                }
                return false
            })
            this.columns =  cols;

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
        // /** 当点击了编辑（修改）按钮时调用此方法 */
        // edit(record) {
        //     if (typeof this.editBefore === 'function') this.editBefore(record)
        //     this.visible = true
        //     this.activeKey = this.refKeys[0]
        //     this.form.resetFields()
        //     this.model = Object.assign({}, record)
        //     if (typeof this.editAfter === 'function') this.editAfter(this.model)
        // },
        // reload(){
        //
        //           this.$router.go(0);
        // }


    },




  }

</script>
<style scoped>
  @import '~@assets/less/common.less';
  .spin-content {
    border: 1px solid #91d5ff;
    background-color: #e6f7ff;
    padding: 30px;
  }
</style>