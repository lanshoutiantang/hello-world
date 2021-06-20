<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--                            <a-input v-decorator="['shopid']"  placeholder="请选择门店编码"></a-input>-->
              <j-search-select-tag v-decorator="['shopid']" :trigger-change="true"  placeholder="请选择门店名称"   dict="v_shopname1,shopname,shopid" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="供应商编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <a-input v-decorator="['venderid']" placeholder="请输入供应商编码"></a-input>-->
              <j-search-select-tag v-decorator="['venderid']" :trigger-change="true" :disabled="isDisabledAuth('bh:bukebianji')"  placeholder="请选择供应商编码"   dict="v_vender,vendername,venderid" />
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="供应商名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['vendername']"
                :trigger-change="true"
                org-fields="vendername,venderid"
                dest-fields="vendername,venderid"
                code="bbzn_vender"
                @callback="popupCallback"/>
<!--              <a-input v-decorator="['vendername']" placeholder="请输入供应商名称"></a-input>-->
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--              <a-input v-decorator="['classtype']" placeholder="请输入补货类别"></a-input>-->
              <j-dict-select-tag type="list"  v-decorator="['classtype']" :trigger-change="true" dictCode="classtype" placeholder="请选择补货类别"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货日" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderdate',validatorRules.orderdate]" placeholder="请输入补货日，0为不补货，1为补货"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货周期标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--              <a-input v-decorator="['weekflag']" placeholder="请输入补货周期标识"></a-input>-->
              <j-dict-select-tag type="list"  v-decorator="['weekflag']" :trigger-change="true" dictCode="weekflag" placeholder="请选择补货周期标识"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="安全库存天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['safedays',validatorRules.safedays]" placeholder="请输入安全库存天数，大于等于0" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="到货周期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['arrivaldays',validatorRules.arrivaldays]" placeholder="请输入到货周期，大于0" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="开始周" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['startweek']" placeholder="请输入开始周" style="width: 100%"/>
            </a-form-item>
          </a-col>
<!--          <a-col :span="24">-->
<!--            <a-form-item label="周转规则" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--              <a-input v-decorator="['roundRule']" placeholder="请输入周转规则"></a-input>-->
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
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import {disabledAuthFilter} from "../../../../utils/authFilter";

  export default {
    name: 'ZnConfigVForm',
    components: {
        JSearchSelectTag,
      JFormContainer,
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
          validatorRules:{
              orderdate:{
                  rules: [{
                      required: true,
                      // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                      // pattern:/^1000000$|^(\d|[1-9]\d)(\.\d+)*$/,
                      // pattern:/^\\d+$/,
                      pattern:/^[0-1]{7}$/,
                      message: '请输入0或1，长度为7!',
                  }],
              },
              safedays:{
                  rules: [{
                      required: true,
                      // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                      // pattern:/^1000000$|^(\d|[1-9]\d)(\.\d+)*$/,
                      // pattern:/^\\d+$/,
                      pattern:/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                      message: '请输入非负整数!',
                  }],
              },
              arrivaldays:{
                  rules: [{
                      required: true,
                      // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                      pattern:/^[0-9]*[1-9][0-9]*$/,
                      message: '请输入正整数!',
                  }],
              },
          },
        url: {
          add: "/config/znConfigV/add",
          edit: "/config/znConfigV/edit",
          queryById: "/config/znConfigV/queryById"
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
    methods: {
        isDisabledAuth(code){
            return disabledAuthFilter(code);
        },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'shopid','venderid','vendername','classtype','orderdate','weekflag','safedays','arrivaldays','startweek','roundRule'))
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
        this.form.setFieldsValue(pick(row,'shopid','venderid','vendername','classtype','orderdate','weekflag','safedays','arrivaldays','startweek','roundRule'))
      },
    }
  }
</script>