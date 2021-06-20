<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('zn_prom_goods9')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
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
    </div>

    <zn-prom-goods9-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnPromGoods9Modal from './modules/ZnPromGoods9Modal'
  import '@/assets/less/TableExpand.less'

  export default {
    name: "ZnPromGoods9List",
    mixins:[JeecgListMixin],
    components: {
      ZnPromGoods9Modal
    },
    data () {
      return {
        description: 'zn_prom_goods9管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'序号',
            align:"center",
            dataIndex: 'serialid'
          },
          {
            title:'促销计划编号',
            align:"center",
            dataIndex: 'planid'
          },
          {
            title:'促销计划名称',
            align:"center",
            dataIndex: 'planname'
          },
          {
            title:'促销开始日期时间',
            align:"center",
            dataIndex: 'begindate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'促销结束日期时间',
            align:"center",
            dataIndex: 'enddate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'补货标识',
            align:"center",
            dataIndex: 'flag'
          },
          {
            title:'课类编码',
            align:"center",
            dataIndex: 'cgid'
          },
          {
            title:'部类编码',
            align:"center",
            dataIndex: 'blid'
          },
          {
            title:'商品编码',
            align:"center",
            dataIndex: 'goodsid'
          },
          {
            title:'商品名称',
            align:"center",
            dataIndex: 'goodsname'
          },
          {
            title:'促销售价',
            align:"center",
            dataIndex: 'price'
          },
          {
            title:'采购分货标识',
            align:"center",
            dataIndex: 'manualFlag'
          },
          {
            title:'门店机构编码',
            align:"center",
            dataIndex: 'shopOrgan'
          },
          {
            title:'部类全部机构编码',
            align:"center",
            dataIndex: 'blOrganAll'
          },
          {
            title:'课类全部机构编码',
            align:"center",
            dataIndex: 'klOrganAll'
          },
          {
            title:'部类机构编码',
            align:"center",
            dataIndex: 'blOrgan'
          },
          {
            title:'课类机构编码',
            align:"center",
            dataIndex: 'klOrgan'
          },
          {
            title:'autoId',
            align:"center",
            dataIndex: 'autoId'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/intel9/znPromGoods9/list",
          delete: "/intel9/znPromGoods9/delete",
          deleteBatch: "/intel9/znPromGoods9/deleteBatch",
          exportXlsUrl: "/intel9/znPromGoods9/exportXls",
          importExcelUrl: "intel9/znPromGoods9/importExcel",
          
        },
        dictOptions:{},
      }
    },
    created() {
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      initDictConfig(){
      },
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>