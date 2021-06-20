<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container  :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-search-select-tag v-decorator="['shopid']" :trigger-change="true"   dict="v_zn_md_yt_ywq,shopname,shopid" />
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="部类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-search-select-tag v-decorator="['blid']" :disabled="isDisabledAuth('bh:bukebianji')"  dict="blcode_name,blname,blid" />
            </a-form-item>
          </a-col>
<!--          <a-col :span="24"-->
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="课类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-search-select-tag v-decorator="['cgid']" :disabled="isDisabledAuth('bh:bukebianji')"  dict="klcode_name,klname,klid" />
            </a-form-item>
          </a-col>

            <a-form-item v-has="'bh:code'" label="小类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['xlname']"
                :disabled="isDisabledAuth('bh:bukebianji')"
                :trigger-change="true"
                org-fields="xlname,xlid"
                dest-fields="xlname,xlid"
                code="zn_xlid"/>
            </a-form-item>

          <a-col :xs="24" :sm="12">
            <a-form-item label="商品名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-popup
                v-decorator="['goodsname']"
                :trigger-change="true"
                org-fields="goodsname,goodsid"
                dest-fields="goodsname,goodsid"
                code="zn_goodsid"/>
            </a-form-item>
          </a-col>

          <a-col :xs="24" :sm="12">
            <a-form-item label="供应商编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['venderid']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入供应商编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="供应商名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['vendername']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入供应商名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="送货类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sendType']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入送货类型"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="补货类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['classtype']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入补货类型"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="正常售价" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['normalprice']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入正常售价"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="规格" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['spec']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入规格"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="最小库存" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['minstock']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入最小库存"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="最小起订量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['minorder']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入最小起订量"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="昨日库存" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['closeqty']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入昨日库存"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="理论补货量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['qty']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入理论补货量"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="建议补货量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['manualQty']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入建议补货量"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="补货标识" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['flag']" :disabled="isDisabledAuth('bh:bukebianji')" :trigger-change="true" dictCode="flag" placeholder="请选择补货标识"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="补货日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sdate']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入补货日期"></a-input>
            </a-form-item>
          </a-col>

          <a-col :xs="24" :sm="12">
            <a-form-item  label="节日类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['holidaytype']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入节日类型"></a-input>
            </a-form-item>
          </a-col>


          <a-col :xs="24" :sm="12">
            <a-form-item label="节日开始日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择节日开始日期" :disabled="isDisabledAuth('bh:bukebianji')" v-decorator="['holidayBegindate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="节日结束日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择节日结束日期" :disabled="isDisabledAuth('bh:bukebianji')" v-decorator="['holidayEnddate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="季节类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['seasontype']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入季节属性"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="节日安全系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['hoRate']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入节日安全系数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="春节指数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['zs']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入春节指数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item  label="补货方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['flag']" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入补货方式" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="comment" :disabled="isDisabledAuth('bh:bukebianji')" placeholder="请输入补货方式" style="width: 100%"/>
            </a-form-item>
          </a-col>



          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'"  label="更新日期时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择更新日期时间" :disabled="isDisabledAuth('bh:bukebianji')" v-decorator="['sdate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="业务区机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['ywqOrgan']" v-has="'bh:code'" placeholder="请输入业务区机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="门店机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['shopOrgan']" v-has="'bh:code'" placeholder="请输入门店机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="部类机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['blOrgan']" v-has="'bh:code'" placeholder="请输入部类机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="课类机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['klOrgan']" v-has="'bh:code'" placeholder="请输入课类机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="部类全部机构编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['blOrganAll']" v-has="'bh:code'" placeholder="请输入部类全部机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item v-has="'bh:code'" label="课类全部机构编码"  :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['klOrganAll']" v-has="'bh:code'" placeholder="请输入课类全部机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item v-has="'bh:code'" label="课类全部机构编码"  :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['sendNum']" v-has="'bh:code'" placeholder="请输入课类全部机构编码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item v-has="'bh:code'" label="真实定价"  :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['trueprice']" v-has="'bh:code'" placeholder="请输入真实价格"></a-input>
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
  import JDate from '@/components/jeecg/JDate'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import {disabledAuthFilter} from "../../../../utils/authFilter";

  export default {
    name: 'ZnGoodsForm',
    components: {
        JSearchSelectTag,
      JFormContainer,
      JDate,
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
          add: "/intel/znGoods/add",
          edit: "/intel/znGoods/edit",
          queryById: "/intel/znGoods/queryById"
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
            console.log(record)
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
          this.form.setFieldsValue(pick(this.model.row,'shopid','blid','cgid',
              'xlid','xlname','goodsname',
              'venderid','vendername','sendType',
              'classtype','normalprice','spec','minstock',
              'minorder','closeqty','qty','manualQty','sdate',
              'holidaytype','holidayBegindate','holidayEnddate',
              'seasontype','hoRate','zs','flag','comment','sendNum','trueprice'))
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
      /*popupCallback(row){
        this.form.setFieldsValue(pick(row,'shopid','blid','cgid','xlid','xlname','goodsname',
            'venderid','vendername','sendType','classtype','normalprice','spec','minstock',
            'minorder','closeqty','mannulQty','sdate','holidaytype','holidayBegindate','holidayEnddate',
            'seasontype','rate','displayFlag','storeqty','configQty'))
      },*/
    },
  }
</script>
