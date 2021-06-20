<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="业务区">
<!--              <a-input placeholder="请输入业务区" v-model="queryParam.ywqid"></a-input>-->
              <j-dict-select-tag placeholder="请选择业务区名称" v-model="queryParam.ywqid" dictCode="city"/>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="节日名称">
<!--              <a-input placeholder="请输入节日代码" v-model="queryParam.holidayid"></a-input>-->
              <j-popup placeholder="请选择节日名称" v-model="queryParam.holidayname" code="bbzn_holidayid_name" org-fields="holidayname" dest-fields="holidayname" :field="getPopupField('holidayname')"/>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="小类名称">
<!--                <a-input placeholder="请输入小类编码" v-model="queryParam.xlid"></a-input>-->
                <j-popup placeholder="请选择小类名称" v-model="queryParam.xlname" code="bbzn_holiday_xl" org-fields="xlname" dest-fields="xlname" :field="getPopupField('xlname')"/>
              </a-form-item>
            </a-col>
<!--            <a-col :xl="6" :lg="7" :md="8" :sm="24">-->
<!--              <a-form-item label="商品编码">-->
<!--&lt;!&ndash;                <a-input placeholder="请输入商品编码" v-model="queryParam.goodsid"></a-input>&ndash;&gt;-->
<!--                <j-popup placeholder="请选择商品编码" v-model="queryParam.goodsid" code="bbzn_holiday_xl" org-fields="goodsid" dest-fields="goodsid" :field="getPopupField('goodsid')"/>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="商品名称">
<!--                <a-input placeholder="请输入商品名称" v-model="queryParam.goodsname"></a-input>-->
                <j-popup placeholder="请选择商品名称" v-model="queryParam.goodsname" code="bbzn_holiday_xl" org-fields="goodsname" dest-fields="goodsname" :field="getPopupField('goodsname')"/>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
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

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" v-has="'jie:add1'" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" v-has="'jie:download1'" icon="download" @click="handleExportXls('节日专属商品表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" v-has="'jie:import1'" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button v-has="'jie:down1'" style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
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
          <a v-has="'jie:edit1'" @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a v-has="'jie:delete1'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <zn-holiday-goods-modal ref="modalForm" @ok="modalFormOk"></zn-holiday-goods-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnHolidayGoodsModal from './modules/ZnHolidayGoodsModal'

  export default {
    name: 'ZnHolidayGoodsList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      ZnHolidayGoodsModal
    },
    data () {
      return {
        description: '节日专属商品表管理页面',
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
            title:'业务区',
            align:"center",
            dataIndex: 'ywqid_dictText'
          },
          {
                title:'节日代码',
                align:"center",
                dataIndex: 'holidayid'
            },
            {
                title:'节日名称',
                align:"center",
                dataIndex: 'holidayname'
            },
          {
            title:'小类编码',
            align:"center",
            dataIndex: 'xlid'
          },
            {
                title:'小类名称',
                align:"center",
                dataIndex: 'xlname'
            },
          {
            title:'商品编码',
            align:"center",
            dataIndex: 'goodsid'
          },
          {
            title:'商品名称',
            align:"center",
            dataIndex: 'goodsid_dictText'
          },
          {
            title:'更新日期',
            align:"center",
            dataIndex: 'sdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
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
          list: "/intel/znHolidayGoods/list",
          delete: "/intel/znHolidayGoods/delete",
          deleteBatch: "/intel/znHolidayGoods/deleteBatch",
          exportXlsUrl: "/intel/znHolidayGoods/exportXls",
          importExcelUrl: "intel/znHolidayGoods/importExcel",
          
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