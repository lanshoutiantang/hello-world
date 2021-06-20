<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="促销计划编号">
              <a-input placeholder="请输入促销计划编号" v-model="queryParam.planid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="促销计划名称">
              <a-input placeholder="请输入促销计划名称" v-model="queryParam.planname"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="促销开始日期">
                <j-date  placeholder="请选择促销开始日期" v-model="queryParam.begindate"></j-date>
<!--                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择促销开始日期" v-model="queryParam.begindate"></j-date>-->
              </a-form-item>
            </a-col>
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="促销结束日期">
                <j-date  placeholder="请选择促销结束日期" v-model="queryParam.enddate"></j-date>
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
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('促销计划表')">导出</a-button>
      <a-button type="primary" icon="download" @click="moBanExportXls('促销计划表')">导出模板</a-button>
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
          <!--          <a @click="checkStatus(record)">状态</a>-->
          <a-popconfirm title="确定生成吗?" @confirm="() => checkStatus(record)">
            <a>生成</a>
          </a-popconfirm>
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

    <zn-prom-plan-modal ref="modalForm" @ok="modalFormOk"/>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ZnPromPlanModal from './modules/ZnPromPlanModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import '@/assets/less/TableExpand.less'
  import {putAction} from "../../../api/manage";
  import { deleteAction, getAction,downFile,getFileAccessHttpUrl } from '@/api/manage'

  export default {
    name: "ZnPromPlanList",
    mixins:[JeecgListMixin],
    components: {
      JDate,
      ZnPromPlanModal
    },
    data () {
      return {
        description: '促销计划表管理页面',
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
            // {
            //     title:'主键',
            //     sorter: true,
            //     align:"center",
            //     dataIndex: 'id'
            // },
          {
            title:'促销计划编号',
              sorter: true,
            align:"center",
            dataIndex: 'planid'
          },
          {
            title:'促销计划名称',
              sorter: true,
            align:"center",
            dataIndex: 'planname'
          },
          {
            title:'促销开始日期',
              sorter: true,
            align:"center",
            dataIndex: 'begindate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'促销结束日期',
              sorter: true,
            align:"center",
            dataIndex: 'enddate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
            {
                title:'状态',
                sorter: true,
                align:"center",
                dataIndex: 'status_dictText'
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
          list: "/intel/znPromPlan/list",
          delete: "/intel/znPromPlan/delete",
          deleteBatch: "/intel/znPromPlan/deleteBatch",
          exportXlsUrl: "/intel/znPromPlan/exportXls",
          importExcelUrl: "intel/znPromPlan/importExcel",
            moBanExportXls:"intel/znPromPlan/moBanExportXls",
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
        checkStatus(record) {
            console.log(record)
            let params = {planid: record.planid,status: record.status,id: record.id};
            // let params1 = {status: record.status};
            putAction("/intel/znPromPlan/checkStatus", params).then((res)=>{
                if(res.success){
                    console.log(res)
                }
            })
        },
        moBanExportXls(fileName){
            console.log("导出参数")
            if(!fileName || typeof fileName != "string"){
                fileName = "导出文件"
            }
            let param = {...this.queryParam};
            if(this.selectedRowKeys && this.selectedRowKeys.length>0){
                param['selections'] = this.selectedRowKeys.join(",")
            }
            console.log("导出参数",param)
            downFile(this.url.moBanExportXls,param).then((data)=>{
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
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>