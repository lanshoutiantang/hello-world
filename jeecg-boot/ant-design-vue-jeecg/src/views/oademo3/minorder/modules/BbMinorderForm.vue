<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
<!--          <a-col :span="24">
            <a-form-item label="业态编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
&lt;!&ndash;              <a-input v-decorator="['ytid']"  placeholder="请输入业态编码"></a-input>&ndash;&gt;
              <j-search-select-tag v-decorator="['yt']" placeholder="请输入业态编码" :trigger-change="true"   dict="bbyt,yt,yt" />
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="门店编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopid']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入门店编码"></a-input>
<!--              <j-search-select-tag v-decorator="['shopid']" :trigger-change="true"   dict="v_zn_md_yt_ywq,shopid,shopid" />-->
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <a-input v-decorator="['shopname']" placeholder="请输入门店名称"></a-input>-->
<!--              <j-search-select-tag v-decorator="['shopname']" :trigger-change="true"   dict="v_zn_md_yt_ywq,shopname,shopname" />-->
              <j-popup
                v-decorator="['shopname']"
                :trigger-change="true"
                org-fields="shopname,shopid"
                dest-fields="shopname,shopid"
                code="zn_shopname"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="商品编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['goodsid']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入商品编码"></a-input>
            </a-form-item>
          </a-col>
<!--          <a-col :span="24">-->
<!--            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--              <a-input v-decorator="['name']" placeholder="请输入商品名称"></a-input>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="24">
            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['name']"
                :trigger-change="true"
                org-fields="name,goodsid"
                dest-fields="name,goodsid"
                code="bbview_goods"
                @callback="popupCallback"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="件装数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['conversion']" placeholder="请输入件装数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="最小订单数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['minorderqty']" placeholder="请输入最小订单数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="最小库存数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['minstockqty']" placeholder="请输入最小库存数量" style="width: 100%"/>
            </a-form-item>
          </a-col>
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
    name: 'BbMinorderForm',
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
        validatorRules: {
        },
        url: {
          add: "/minorder/bbMinorder/add",
          edit: "/minorder/bbMinorder/edit",
          queryById: "/minorder/bbMinorder/queryById"
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
        this.form.setFieldsValue(pick(this.model.row,'yt','shopid','shopname','goodsid','name','conversion','minorderqty','minstockqty'))
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
        this.form.setFieldsValue(pick(row,'yt','shopid','shopname','goodsid','name','conversion','minorderqty','minstockqty'))
      },
    }
  }
</script>