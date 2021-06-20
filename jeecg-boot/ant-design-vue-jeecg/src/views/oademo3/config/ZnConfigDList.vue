<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="5" :lg="7" :md="8" :sm="24">
            <a-form-item  label="课类名称">
              <j-search-select-tag  placeholder="请选择课类名称"  v-model="queryParam.cgid" dict="klcode_name,klname,klid"/>
            </a-form-item>
          </a-col>
<!--          <a-col :xl="5" :lg="7" :md="8" :sm="24">-->
<!--            <a-form-item  label="补货类别">-->
<!--              <j-dict-select-tag   placeholder="请选择补货类别"  v-model="queryParam.classtype" dictCode="classtype"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :xl="5" :lg="7" :md="8" :sm="24">-->
<!--            <a-form-item  label="补货周期标识">-->
<!--              <j-dict-select-tag   placeholder="请选择补货周期标识"  v-model="queryParam.weekflag" dictCode="weekflag"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :xl="3" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
<!--      <a-button type="primary" icon="download" @click="handleExportXls('特殊商品补货参数')">导出</a-button>-->
<!--      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">-->
<!--        <a-button type="primary" icon="import">导入</a-button>-->
<!--      </a-upload>-->
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

          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
<!--          <a-dropdown>-->
<!--            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>-->
<!--            <a-menu slot="overlay">-->
<!--              <a-menu-item>-->
<!--                <a @click="handleDetail(record)">详情</a>-->
<!--              </a-menu-item>-->
<!--              <a-menu-item>-->
<!--                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">-->
<!--                  <a>删除</a>-->
<!--                </a-popconfirm>-->
<!--              </a-menu-item>-->
<!--            </a-menu>-->
<!--          </a-dropdown>-->
        </span>

      </a-table>
    </div>

    <zn-config-d-modal ref="modalForm" @ok="modalFormOk"></zn-config-d-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnConfigDModal from './modules/ZnConfigDModal'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'

  export default {
    name: 'ZnConfigDList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JSearchSelectTag,
      ZnConfigDModal
    },
    data () {
      return {
        description: '特殊商品补货参数管理页面',
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
            title:'补货类别',
              sorter: true,
            align:"center",
            dataIndex: 'classtype_dictText'
          },
          {
            title:'课类名称',
              sorter: true,
            align:"center",
            dataIndex: 'cgid_dictText'
          },
          {
            title:'补货日',
              sorter: true,
            align:"center",
            dataIndex: 'orderdate'
          },
          {
            title:'安全库存天数',
              sorter: true,
            align:"center",
            dataIndex: 'safedays'
          },
          {
            title:'到货周期',
              sorter: true,
            align:"center",
            dataIndex: 'arrivaldays'
          },
          {
            title:'每次补几天的货',
              sorter: true,
            align:"center",
            dataIndex: 'repldays'
          },
          // {
          //   title:'周转规则',
          //   align:"center",
          //   dataIndex: 'roundRule'
          // },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/config/znConfigD/list",
          delete: "/config/znConfigD/delete",
          deleteBatch: "/config/znConfigD/deleteBatch",
          exportXlsUrl: "/config/znConfigD/exportXls",
          importExcelUrl: "config/znConfigD/importExcel",
          
        },
        dictOptions:{},
      }
    },
    created() {
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>