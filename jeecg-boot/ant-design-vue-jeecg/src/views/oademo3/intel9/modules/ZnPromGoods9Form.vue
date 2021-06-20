<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :xs="24" :sm="12">
            <a-form-item label="促销计划编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planid']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入促销计划编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="促销计划名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['planname']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入促销计划名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="促销开始日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择促销开始日期" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['begindate']" :trigger-change="true"  style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="促销结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择促销结束日期" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['enddate']" :trigger-change="true"  style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsname']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入商品名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="促销售价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['price']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入促销售价" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="采购分货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['manualFlag']" :trigger-change="true" dictCode="flag3" placeholder="请选择采购分货标识"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'"  label="补货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['flag']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入补货标识"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="序号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['serialid']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入序号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="小类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['xlid']" placexholder="请输入小类编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="门店机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopOrgan']" placeholder="请输入门店机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="部类全部机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['blOrganAll']" placeholder="请输入部类全部机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="课类全部机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['klOrganAll']" placeholder="请输入课类全部机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="部类机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['blOrgan']" placeholder="请输入部类机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'cuxiao:code'" label="课类机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['klOrgan']" placeholder="请输入课类机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  v-has="'cuxiao:code'" label="autoId" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['autoId']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入autoId" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="分货数量" :key="refKeys[0]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[0]"
          :loading="znPromDetail9Table.loading"
          :columns="znPromDetail9Table.columns"
          :dataSource="znPromDetail9Table.dataSource"
          :maxHeight="500"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'
  // import { colAuthFilterJEditableTable } from "@/utils/authFilter"
  import { colAuthFilterJEditableTable } from "@/utils/authFilter"
  import {disabledAuthFilter} from "../../../../utils/authFilter";


  export default {
    name: 'ZnPromGoods9Form',
    mixins: [JEditableTableMixin],
    components: {
      JFormContainer,
      JDate,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['znPromDetail9', ],
        tableKeys:['znPromDetail9', ],
        activeKey: 'znPromDetail9',
        // zn_prom_detail9
        znPromDetail9Table: {
          loading: false,
          dataSource: [],
          columns: [
              {
                  title: '创建人',
                  key: 'createBy',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '创建日期',
                  key: 'createTime',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '更新人',
                  key: 'updateBy',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '更新日期',
                  key: 'updateTime',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '所属部门',
                  key: 'sysOrgCode',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '促销计划编号',
                  key: 'planid',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '促销计划名称',
                  key: 'planname',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '促销开始日期时间',
                  key: 'begindate',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '促销结束日期时间',
                  key: 'enddate',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '门店编码',
                  key: 'shopid',
                  // type: FormTypes.input,
                  type: FormTypes.sel_search,
                  dictCode:"tb_organ,name,organ",
                  width:"300px",
                  // width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '小类编码',
                  key: 'xlid',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '课类编码',
                  key: 'cgid',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '部类编码',
                  key: 'blid',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '商品编码',
                  key: 'goodsid',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '商品名称',
                  key: 'goodsname',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '促销售价',
                  key: 'price',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '采购分货数量',
                  key: 'planqty',
                  type: FormTypes.inputNumber,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: 'manualFlag',
                  key: 'manualFlag',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '补货标识',
                  key: 'flag',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '门店机构编码',
                  key: 'shopOrgan',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '部类全部机构编码',
                  key: 'blOrganAll',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '课类全部机构编码',
                  key: 'klOrganAll',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '部类机构编码',
                  key: 'blOrgan',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: '课类机构编码',
                  key: 'klOrgan',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: 'autoId',
                  key: 'autoId',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: 'sdate',
                  key: 'sdate',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
              {
                  title: 'idGoods',
                  key: 'idGoods',
                  type: FormTypes.hidden,
                  width:"200px",
                  placeholder: '请输入${title}',
                  defaultValue: '',
              },
          ]
        },
        url: {
          add: "/intel9/znPromGoods9/add",
          edit: "/intel9/znPromGoods9/edit",
          queryById: "/intel9/znPromGoods9/queryById",
          znPromDetail9: {
            list: '/intel9/znPromGoods9/queryZnPromDetail9ByMainId'
          },
        }
      }
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：false流程表单 true普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },

    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
      //如果是流程中表单，则需要加载流程表单data
        this.znPromDetail9Table.columns = colAuthFilterJEditableTable(this.znPromDetail9Table.columns,'cuxiao:goods:');
      this.showFlowData();
    },
    methods: {
        isDisabledAuth(code){
            return disabledAuthFilter(code);
        },
      addBefore(){
        this.form.resetFields()
        this.znPromDetail9Table.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'serialid','planid','planname','begindate','enddate','flag','cgid','blid','goodsid','goodsname','price','manualFlag','shopOrgan','blOrganAll','klOrganAll','blOrgan','klOrgan','autoId')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.znPromDetail9.list, params, this.znPromDetail9Table)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          znPromDetail9List: allValues.tablesValue[0].values,
        }
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          })
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'serialid','planid','planname','begindate','enddate','flag','cgid','blid','goodsid','goodsname','price','manualFlag','shopOrgan','blOrganAll','klOrganAll','blOrgan','klOrgan','autoId'))
     },

    }
  }
</script>

<style scoped>
</style>