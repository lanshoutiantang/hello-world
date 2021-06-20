<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-item label="补货类别" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <a-input v-decorator="['classtype']" placeholder="请输入补货类别"></a-input>-->
              <j-dict-select-tag type="list"  v-decorator="['classtype']" :trigger-change="true" dictCode="classtype1" placeholder="请选择补货类别"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="课类名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
<!--              <a-input v-decorator="['cgid']" placeholder="请输入课别或采购线"></a-input>-->
<!--              <j-search-select-tag  placeholder="请选择课类名称" v-model="queryParam.cgid" dict="klcode_name,klname,klid"/>-->
              <j-search-select-tag type="list"  v-decorator="['cgid']" :trigger-change="true" dict="klcode_name,klname,klid" placeholder="请选择课类名称"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="补货日" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['orderdate',validatorRules.orderdate]" placeholder="请输入补货日，0为不补货，1为补货"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="安全库存天数" v-if="" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['safedays',validatorRules.safedays]" placeholder="请输入安全库存天数，大于等于0" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="到货周期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['arrivaldays',validatorRules.arrivaldays]" placeholder="请输入到货周期，大于0" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item  label="每次补几天的货" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['repldays',validatorRules.repldays]" placeholder="请输入每次补几天的货，大于0" style="width: 100%"/>
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
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import {duplicateCheck} from "../../../../api/api";

  export default {
    name: 'ZnConfigDForm',
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
                  rules: [
                  //     {
                  //     required: true,
                  //     // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                  //     // pattern:/^1000000$|^(\d|[1-9]\d)(\.\d+)*$/,
                  //     // pattern:/^\\d+$/,
                  //     pattern:/^([0-9]*[1-9][0-9]*$)|0/,
                  //     message: '请输入非负整数!',
                  // },
                      { required: true, message: '请输入部门角色编码!'},
                      // { min: 0, max: 64, message: '长度不超过 64 个字符', trigger: 'blur' },
                      { validator: this.validateRolesssCode}
                    /*      {
                              // handler({cellValue, row, column}, callback, target) {
                              //     // cellValue 当前校验的值
                              //     // callback(flag, message) 方法必须执行且只能执行一次
                              //     //          flag = 是否通过了校验，不填写或者填写 null 代表不进行任何操作
                              //     //          message = 提示的类型，默认使用配置的 message
                              //     // target 行编辑的实例对象
                              //     console.log(55555)
                              //     if (cellValue == 55) {
                              //         callback(false, '不能是abc')  // false = 未通过校验
                              //     } else {
                              //         callback(true) // true = 通过验证
                              //     }
                              // },
                              // handler(type, value, row, column, callback, target) {
                              //     // （input、change、blur）
                              //     // value 当前校验的值
                              //     // callback(flag, message) 方法必须执行且只能执行一次
                              //     //          flag = 是否通过了校验，不填写或者填写 null 代表不进行任何操作
                              //     //          message = 提示的类型，默认使用配置的 message
                              //     // target 行编辑的实例对象
                              //
                              //     if (type === 'blur') {
                              //
                              //         if (value === 'abc') {
                              //             callback(false, '${title}不能是abc') // false = 未通过，可以跟自定义提示
                              //             return
                              //         }
                              //
                              //         let { values } = target.getValuesSync({ validate: false })
                              //         let count = 0
                              //         for (let val of values) {
                              //             if (val['dbFieldName'] === value) {
                              //                 if (++count >= 2) {
                              //                     callback(false, '${title}不能重复')
                              //                     return
                              //                 }
                              //             }
                              //         }
                              //         callback(true) // true = 通过验证
                              //     } else {
                              //         callback() // 不填写或者填写 null 代表不进行任何操作
                              //     }
                              // },
                              // message: '请输入!',

                      },*/
                  ],
              },
              arrivaldays:{
                  rules: [{
                      required: true,
                      // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                      pattern:/^[0-9]*[1-9][0-9]*$/,
                      message: '请输入正整数!',
                  }],
              },
              repldays:{
                  rules: [{
                      required: true,
                      // pattern:/^([1-9]\\d*(\\.[0-9]*[1-9])?)|(0\\.[0-9]*[1-9])|0$/,
                      pattern:/^[0-9]*[1-9][0-9]*$/,
                      message: '请输入正整数!',
                  }],
              },
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
        validateRolesssCode(rule, value, callback){
            // let pattern = /^[a-z|A-Z][a-z|A-Z|\d|_|-]{0,}$/;
            if(value == 5){
                callback('编码必须以字母开头，可包含数字、下划线、横杠');
            } else {
                callback();
            }
            // else {
            //     var params = {
            //         tableName: "zn_config_d",
            //         fieldName: "safedays",
            //         fieldVal: value,
            //         dataId: this.model.id
            //     };
            //     duplicateCheck(params).then((res)=>{
            //         if(res.success){
            //             callback();
            //         }else{
            //             callback(res.message);
            //         }
            //     })
            // }
        },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        console.log(this.model.repldays);
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