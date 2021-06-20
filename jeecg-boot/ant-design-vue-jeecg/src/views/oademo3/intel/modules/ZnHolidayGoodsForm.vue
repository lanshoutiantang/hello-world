<template>
  <!--<a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="业务区" :labelCol="labelCol" :wrapperCol="wrapperCol" >
              <j-dict-select-tag
                :disabled="isDisabledAuth('jie:bukebianji')"
                v-decorator="['ywqid']"
                :trigger-change="true"
                type="list"
                dictCode="city"
                placeholder="请选择业务区名称"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidayid']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入节日代码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日名称" :labelCol="labelCol" :wrapperCol="wrapperCol" >
              <j-dict-select-tag
                type="list"
                v-decorator="['holidayid']"
                :disabled="isDisabledAuth('jie:bukebianji')"
                :trigger-change="true"
                dictCode="zn_holidayid_name,holidayname,holidayid"
                placeholder="请选择业务区名称"/>
            </a-form-item>
          </a-col>

          <a-col :span="24">
            <a-form-item label="小类名称" :labelCol="labelCol" :wrapperCol="wrapperCol" >
              <j-search-select-tag
                v-decorator="['xlname']"
                :trigger-change="true"
                type="list"
                :dict="`zn_holiday_xl,xlname,xlid,ywqid=`+`'`+`${this.form.getFieldValue('ywqid')}`+ `'`+`and holidayid=`+`'`+`${this.form.getFieldValue('holidayid')}`+`'`"
                placeholder="请选择业务区名称"/>
            </a-form-item>
          </a-col>-->
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="业务区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" :disabled="isDisabledAuth('jie:bukebianji')" v-decorator="['ywqid']" :trigger-change="true" dictCode="city" placeholder="请选择业务区名称"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日代码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidayid']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入节日代码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="节日名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidayname']" :disabled="isDisabledAuth('jie:bukebianji')"  placeholder="请输入节日名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['xlid']" :disabled="isDisabledAuth('jie:bukebianji')"  placeholder="请输入小类编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="小类名称" :labelCol="labelCol" :wrapperCol="wrapperCol" onchange="ChangeTextNo()">
<!--              <j-popup-->
<!--                v-decorator="['xlname']"-->
<!--                :trigger-change="true"-->
<!--                org-fields="xlname,xlid,holidayname,holidayid,ywqid"-->
<!--                dest-fields="xlname,xlid,holidayname,holidayid,ywqid"-->
<!--                code="bbzn_holiday_xl1"-->
<!--                @callback="popupCallback"/>-->
              <a-input v-decorator="['xlname']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item  label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" :disabled="isDisabledAuth('jie:bukebianji')" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <j-search-select-tag
                v-decorator="['goodsname']"
                :trigger-change="true"
                :dict="`zn_holiday_xl a inner join v_goods b on a.xlid = b.deptid,b.name,b.name,a.xlid=`+`'`+`${this.form.getFieldValue('xlid')}`+`'`"
                placeholder="请输入商品名称"/>
                                :dict="`zn_holiday_xl,xlname,xlid,ywqid=`+`'`+`${this.form.getFieldValue('ywqid')}`+ `'`+`and holidayid=`+`'`+`${this.form.getFieldValue('holidayid')}`+`'`"-->
              <j-popup
                  v-decorator="['goodsname']"
                  :trigger-change="true"
                  org-fields="xlname,xlid,holidayname,holidayid,ywqid,goodsid,goodsname"
                  dest-fields="xlname,xlid,holidayname,holidayid,ywqid,goodsid,goodsname"
                  code="bbzn_holiday_xl2"
                  @callback="popupCallback"/>
            </a-form-item>
          </a-col>

<!--          <a-col :span="24">-->
<!--            <a-form-item label="更新日期"  :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--              <j-date placeholder="请选择更新日期" :disabled="isDisabledAuth('jie:bukebianji')" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form>
    </j-form-container>
  </a-spin>
</template>


<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JFormContainer from '@/components/jeecg/JFormContainer'
  import JDate from '@/components/jeecg/JDate'
  import {disabledAuthFilter} from "../../../../utils/authFilter";
  import {putAction} from "../../../../api/manage";
  import {AxiosInstance as axios} from "axios";
  import * as $ from "vxe-table";
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'


  export default {
    name: 'ZnHolidayGoodsForm',
    components: {
      JFormContainer,
      JDate,
        JSearchSelectTag
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
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
    data () {


        return {
        queryParam: {},
        form: this.$form.createForm(this),
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/intel/znHolidayGoods/add",
          edit: "/intel/znHolidayGoods/edit",
          queryById: "/intel/znHolidayGoods/queryById",
            getData: "/intel/znHolidayGoods/getData"
        }
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
      this.showFlowData();
    },
      mounted(){


      },

      methods: {

        isDisabledAuth(code){
            return disabledAuthFilter(code);},
      add () {
        this.edit({});
      },
      edit(record){
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'ywqid','holidayid','holidayname','xlid','xlname','goodsid','goodsname','sdate'))
        })
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }

        })
      },
          popupCallback(row){
              this.form.setFieldsValue(pick(row,'ywqid','holidayid','holidayname','xlid','xlname','goodsid','goodsname','sdate'))
          },

    }
  }
</script>