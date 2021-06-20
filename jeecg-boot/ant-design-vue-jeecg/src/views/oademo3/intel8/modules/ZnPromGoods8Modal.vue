<template>
  <j-modal
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">


      <a-tabs v-model="activeKey" @change="handleChangeTabs">
<!--        <a-tabs v-model="activeKey" @change="handleChangeTabs">-->
      <!--主表区域 -->
       <a-tab-pane  tab="商品" :key="refKeys[0]" :forceRender="true">
            <a-form :form="form">
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
                       <a-form-item label="促销开始日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
                         <j-date placeholder="请选择促销开始日期时间" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['begindate']" :trigger-change="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
                       </a-form-item>
                     </a-col>
                     <a-col :xs="24" :sm="12">
                       <a-form-item label="促销结束日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
                         <j-date placeholder="请选择促销结束日期时间" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['enddate']" :trigger-change="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
                       </a-form-item>
                     </a-col>

<!--                     <a-col :xs="24" :sm="12">-->
<!--                       <a-form-item label="课类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--                         <a-input v-decorator="['cgid']" placeholder="请输入课类编码"></a-input>-->
<!--                       </a-form-item>-->
<!--                     </a-col>-->
<!--                     <a-col :xs="24" :sm="12">-->
<!--                       <a-form-item label="部类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--                         <a-input v-decorator="['blid']" placeholder="请输入部类编码"></a-input>-->
<!--                       </a-form-item>-->
<!--                     </a-col>-->

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
<!--                         <a-input v-decorator="['manualFlag']" :disabled="isDisabledAuth('cuxiao:bukebianji')" placeholder="请输入采购分货标识"></a-input>-->
                         <j-dict-select-tag type="list" :disabled="isDisabledAuth('cuxiao:bukebianji')" v-decorator="['manualFlag']" :trigger-change="true" dictCode="flag3" placeholder="请选择采购分货标识"/>
                       </a-form-item>
                     </a-col>
                <a-col :xs="24" :sm="12">
                  <a-form-item v-has="'cuxiao:code'" label="补货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
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

        </a-tab-pane>
      <!--子表单区域 -->
        <a-tab-pane tab="分获数量" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="znPromDetail8Table.loading"
            :columns="znPromDetail8Table.columns"
            :dataSource="znPromDetail8Table.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>

      </a-tabs>

    </a-spin>
  </j-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
   import { VALIDATE_NO_PASSED, validateFormAndTables } from '@/utils/JEditableTableUtil'
  import JDate from '@/components/jeecg/JDate'
  import {disabledAuthFilter} from "../../../../utils/authFilter";
  import { colAuthFilterJEditableTable } from "@/utils/authFilter"

  export default {
    name: 'ZnPromGoods8Modal',
    mixins: [JEditableTableMixin],
    components: {
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
        refKeys: ['znPromGoods8','znPromDetail8', ],
        //   refKeys: ['znPromDetail8','znPromGoods8', ],
        // tableKeys:['znPromGoods8', ],
        // activeKey: 'znPromDetail8',
          tableKeys:['znPromDetail8', ],
          activeKey: 'znPromGoods8',
        // zn_prom_detail8
        znPromDetail8Table: {
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
          add: "/intel8/znPromGoods8/add",
          edit: "/intel8/znPromGoods8/edit",
           znPromGoods8: {
            list: '/intel8/znPromGoods8/queryById'
           },
          znPromDetail8: {
            list: '/intel8/znPromGoods8/queryZnPromDetail8ByMainId'
          },
        }
      }
    },
      created() {
          this.znPromDetail8Table.columns = colAuthFilterJEditableTable(this.znPromDetail8Table.columns,'cuxiao:goods:');
      },
    methods: {
        isDisabledAuth(code){
            return disabledAuthFilter(code);
        },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'serialid','planid','planname','begindate','enddate','flag','cgid','blid','xlid','goodsid','goodsname','price','manualFlag','shopOrgan','blOrganAll','klOrganAll','blOrgan','klOrgan','autoId')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.znPromDetail8.list, params, this.znPromDetail8Table)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          znPromDetail8List: allValues.tablesValue[0].values,
        }
      },
       /** 确定按钮点击事件 */
            handleOk() {
              /** 触发表单验证 */
              this.getAllTable().then(tables => {
                return validateFormAndTables(this.form, tables)
              }).then(allValues => {
                if (typeof this.classifyIntoFormData !== 'function') {
                  throw this.throwNotFunction('classifyIntoFormData')
                }
                console.log("this.classifyIntoFormData",typeof this.classifyIntoFormData)
                let formData = this.classifyIntoFormData(allValues)

                // 发起请求
                return this.request(formData)
              }).catch(e => {
                if (e.error === VALIDATE_NO_PASSED) {
                  // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                  this.activeKey = e.index == null ? this.refKeys[0] : this.refKeys[e.index+1]
                } else {
                  console.error(e)
                }
              })
            },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'serialid','planid','planname','begindate','enddate','flag','cgid','blid','xlid','goodsid','goodsname','price','manualFlag','shopOrgan','blOrganAll','klOrganAll','blOrgan','klOrgan','autoId'))
     },

    }
  }
</script>

<style scoped>
</style>