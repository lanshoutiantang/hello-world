<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->


    <!-- 操作按钮区域 -->
    <div class="table-operator">   <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="门店">
              <!--              <a-input placeholder="请输入门店" v-model="queryParam.shopid"></a-input>-->
              <j-search-select-tag placeholder="请选择门店名称" v-model="queryParam.shopid" dict="tb_organ,name,organ"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="小类编码">
<!--              <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
              <j-popup placeholder="请选择小类编码" v-model="queryParam.xlid" code="zn_xlid" org-fields="xlid" dest-fields="xlid" :field="getPopupField('xlid')"/>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
<!--            <a-col :xl="4" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="小类名称">-->
<!--                <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="zn_xlid" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="起始时间">
                <j-date placeholder="请选择起始时间" v-model="queryParam.begindate"></j-date>
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="结束时间">
                <j-date placeholder="请选择结束时间" v-model="queryParam.enddate"></j-date>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery1" icon="search">汇总</a-button>
<!--              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>-->
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
<!--              <router-link :to="{path:'/online/cgreport/1348090776998907905',query:{begindate:queryParam.begindate,enddate:queryParam.enddate,shopidd:queryParam.shopid,xlidd:queryParam.xlid}}">-->
<!--                  <button>跳转</button>-->
<!--                <a-button type="primary" style="margin-left: 8px">汇总</a-button>-->
<!--              </router-link>-->
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
      <!-- 查询区域-END -->
      <a-button @click="handleAdd" v-has="'xiaoshou:add'" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" v-has="'xiaoshou:download'" icon="download" @click="handleExportXls('按日期门店小类总销售')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'xiaoshou:import'"  icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'xiaoshou:down'" style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
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
          <a v-has="'xiaoshou:edit'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'xiaoshou:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <tmp-shop-xlid-sum-modal ref="modalForm" @ok="modalFormOk"></tmp-shop-xlid-sum-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TmpShopXlidSumModal from './modules/TmpShopXlidSumModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'

  export default {
    name: 'TmpShopXlidSumList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
        JSearchSelectTag,
      JDate,
      TmpShopXlidSumModal
    },
    data () {
      return {
        description: '按日期门店小类总销售管理页面',
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
            title:'门店',
              sorter: true,
            align:"center",
            dataIndex: 'shopid_dictText'
          },
          {
            title:'小类编码',
              sorter: true,
            align:"center",
            dataIndex: 'xlid'
          },
          {
            title:'小类名称',
              sorter: true,
            align:"center",
            dataIndex: 'xlname'
          },
          {
            title:'总数量',
              sorter: true,
            align:"center",
            dataIndex: 'sumNumber'
          },
          {
            title:'总金额',
              sorter: true,
            align:"center",
            dataIndex: 'sumPrice'
          },
          {
            title:'起始时间',
              sorter: true,
            align:"center",
            dataIndex: 'begindate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'结束时间',
              sorter: true,
            align:"center",
            dataIndex: 'enddate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          // {
          //   title: '操作',
          //   dataIndex: 'action',
          //   align:"center",
          //   fixed:"right",
          //   width:147,
          //   scopedSlots: { customRender: 'action' }
          // }
        ],
        url: {
          list: "/intel/tmpShopXlidSum/list",
            huizong: "/intel/tmpShopXlidSum/shopXlidSumlist",
          delete: "/intel/tmpShopXlidSum/delete",
            count: "/intel/tmpShopXlidSum/count",
          deleteBatch: "/intel/tmpShopXlidSum/deleteBatch",
          exportXlsUrl: "/intel/tmpShopXlidSum/exportXls",
          importExcelUrl: "intel/tmpShopXlidSum/importExcel",

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
        },
        // searchQuery1(){},
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>