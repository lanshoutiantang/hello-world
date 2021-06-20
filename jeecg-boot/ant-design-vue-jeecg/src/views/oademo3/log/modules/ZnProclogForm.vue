<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
<!--          <a-col :span="24">
            <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择更新日期" v-decorator="['updatetime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD" style="width: 100%"/>
&lt;!&ndash;              <j-date placeholder="请选择更新日期" v-decorator="['updatetime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>&ndash;&gt;
            </a-form-item>
          </a-col>-->
<!--          <a-col :span="24">-->
<!--            <a-form-item label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--              <a-input v-decorator="['shopid']" placeholder="请输入门店名称"></a-input>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="24">
            <a-form-item  label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
&lt;!&ndash;              <j-search-select-tag v-decorator="['shopid']" :multiple="true" :trigger-change="true"   dict="v_zn_md_yt_ywq,shopname,shopid" />&ndash;&gt;
&lt;!&ndash;              <j-select-multiple v-model="jselectMultiple.value" :options="jselectMultiple.options"/>&ndash;&gt;
              <j-multi-select-tag  type="list_multi" v-decorator="['shopid']" :trigger-change="true" dictCode="shop" placeholder="请选择下拉多选"/>
&lt;!&ndash;              <j-select-multiple-tag    dict="v_shopname1,shop,shopid"&ndash;&gt;
            </a-form-item>
          </a-col>-->
          <a-col :span="24">
            <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择更新日期" v-decorator="['date']" :trigger-change="true" :show-time="false" date-format="YYYY-MM-DD" style="width: 100%"/>
              <!--              <j-date placeholder="请选择更新日期" v-decorator="['updatetime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>-->
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item  label="门店名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--              <j-search-select-tag v-decorator="['shopid']" :multiple="true" :trigger-change="true"   dict="v_zn_md_yt_ywq,shopname,shopid" />-->
              <!--              <j-select-multiple v-model="jselectMultiple.value" :options="jselectMultiple.options"/>-->
              <j-multi-select-tag  type="list_multi" v-decorator="['shop']" :trigger-change="true" dictCode="shop" placeholder="请选择下拉多选"/>
              <!--              <j-select-multiple-tag    dict="v_shopname1,shop,shopid"-->
            </a-form-item>
          </a-col>

<!--          <a-row :gutter="24">-->
<!--            <a-col :span="12">-->
<!--              <a-form-item label="字典下拉(多选)">-->
<!--                <j-multi-select-tag-->
<!--                  v-model="formData.selMuti"-->
<!--                  dictCode="sex"-->
<!--                  placeholder="请选择">-->
<!--                </j-multi-select-tag>-->
<!--              </a-form-item>-->
<!--            </a-col>-->
<!--            <a-col :span="12">多选组合(v-model)：{{ formData.selMuti }}</a-col>-->
<!--          </a-row>-->
<!--          <a-col :span="24">-->
<!--            <a-form-item label="补货是否正常完成" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
<!--              <a-textarea v-decorator="['comments']" rows="4" placeholder="请输入补货是否正常完成"/>-->
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
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import JSelectMultiple from '@/components/jeecg/JSelectMultiple'
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"


  export default {
    name: 'ZnProclogForm',
    components: {
        JMultiSelectTag,
        JSelectMultiple,
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
          add: "/log/znProclog/add",
          edit: "/log/znProclog/edit",
          queryById: "/log/znProclog/queryById"
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
          this.form.setFieldsValue(pick(this.model,'updatetime','shopid','comments','shop','date'))
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
              this.$confirm({
                  title: "确认提交",
                  content: "是否提交所选补货门店及日期?",
                  onOk: function () {
                      httpAction(httpurl,formData,method).then((res)=>{
                          if(res.success){
                              that.$message.success(res.message);
                              that.$emit('ok');
                          }else{
                              that.$message.warning(res.message);
                          }
                      }).finally(() => {
                          console.log('5555555')
                          that.confirmLoading = false;
                      })
                  },
                  onCancel() {
                      that.confirmLoading = false;
                  },
              });
            // httpAction(httpurl,formData,method).then((res)=>{
            //   if(res.success){
            //     that.$message.success(res.message);
            //     that.$emit('ok');
            //   }else{
            //     that.$message.warning(res.message);
            //   }
            // }).finally(() => {
            //   that.confirmLoading = false;
            // })
          }
         
        })
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'updatetime','shopid','comments','shop','date'))
      },
    }
  }
</script>