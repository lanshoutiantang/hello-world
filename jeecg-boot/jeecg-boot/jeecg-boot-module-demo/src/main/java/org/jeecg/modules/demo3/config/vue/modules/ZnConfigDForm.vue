<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="补货类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['classtype']" placeholder="请输入补货类别"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="课别或采购线" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['cgid']" placeholder="请输入课别或采购线"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货日" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderdate']" placeholder="请输入补货日"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="安全库存天数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['safedays']" placeholder="请输入安全库存天数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="到货周期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['arrivaldays']" placeholder="请输入到货周期" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="每次补几天的货" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['repldays']" placeholder="请输入每次补几天的货" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="周转规则" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['roundRule']" placeholder="请输入周转规则"></a-input>
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

  export default {
    name: 'ZnConfigDForm',
    components: {
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
          add: "/config/znConfigD/add",
          edit: "/config/znConfigD/edit",
          queryById: "/config/znConfigD/queryById"
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
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'classtype','cgid','orderdate','safedays','arrivaldays','repldays','roundRule'))
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
        this.form.setFieldsValue(pick(row,'classtype','cgid','orderdate','safedays','arrivaldays','repldays','roundRule'))
      },
    }
  }
</script>