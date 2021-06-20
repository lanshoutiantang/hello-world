<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container  :disabled="formDisabled">
      <a-form :form="form" slot="detail">
        <a-row>
          <template v-if="manmualIf < 0">
            <a-col :span="24"  icon="save" style="font-size: 16px; color: rgba(0, 0, 0, 0.85); font-weight:500; margin-bottom: 30px">
              <a-icon style="font-size: 16px; padding: 4px; color: red" type="question-circle">
              </a-icon>
              提示：建议补货数量需大于等于0！
            </a-col>
          </template>
<!--          <template v-if="manmualIf >= 0 && closeqtyQty+manmualIf >= minorstockQty && (manmualIf%minorderIf == 0 || manmualIf%conversionQty == 0) ">-->
          <template v-if="manmualIf >= 0 ">
<!--            <a-icon style="font-size: 16px; padding: 4px; color: green" type="question-circle">-->
<!--            </a-icon>-->
            <a-col :span="24"  style="font-size: 16px; color: rgba(0, 0, 0, 0.85); font-weight:500;  margin-bottom: 30px ">
              <a-icon style="font-size: 16px; padding: 4px; color: green" type="question-circle">
              </a-icon>
<!--              是否确定保存?建议补货数量最好是该最小补货量{{minorderIf}}的整数倍，提示：暂时不敢保证最小补货量是正确的!-->
              提示：" 建议补货量：{{manmualIf}} " 应是 " 最小订货量：{{minorderIf}} " 的整数倍。
<!--             该商品“最小订货量”值为{{minorderIf}},而当前“建议补货数量”值不是“最小订货量”值的整数倍，如果确定使用该数值请点击"确定",否则请从如下输入框编辑新数值并点击“确定”!-->
<!--              "建议补货数量"应该是"最小订货量"{{minorderIf}}的整数倍,请确定是否保存？提示：暂时不敢保证最小补货量是正确的!-->
            </a-col>
<!--            <a-col :span="24"  style="font-size: 16px; color: red; font-weight:500; margin-bottom: 30px">-->
<!--             保存当前数值请点击“确定”，否则点击“取消”-->
<!--            </a-col>-->
<!--            <a-col :span="24"  style="font-size: 16px; color: rgba(0, 0, 0, 0.85); font-weight:500; margin-bottom: 30px">-->
<!--              建议补货数量最好是该最小补货量{{minorderIf}}的整数倍，提示：暂时不敢保证最小补货量是正确的!-->
<!--            </a-col>-->
          </template>
          <p> </p>
          <a-col :xs="24" :sm="12">
<!--            <a-form-item  label="建议补货量" :labelCol="labelCol" :wrapperCol="wrapperCol">-->
            <a-form-item  label="  建议补货量" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['manualQty',validatorRules.manualQty]"  placeholder="请输入建议补货量"></a-input>
            </a-form-item>
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
      name: 'ZnGoodsForm1',
      components: {
          JSearchSelectTag,
          JFormContainer,
          JDate,
      },
      props: {
          //流程表单data
          formData: {
              type: Object,
              default: () => {
              },
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
      data() {
          return {
              minorderIf:0,
              manmualIf:0,
              closeqtyQty:0,
              minorstockQty:0,
              conversionQty:0,
              form: this.$form.createForm(this),
              model: {},
              labelCol: {
                  xs: {span: 24},
                  sm: {span: 5},
              },
              wrapperCol: {
                  xs: {span: 24},
                  sm: {span: 16},
              },
              confirmLoading: false,
              validatorRules:{
                  manualQty:{
                      rules: [{
                          required: true,
                          message: '不能重复'
                      },
                      //     {
                      //         handler({cellValue, row, column}, callback, target) {
                      //             // cellValue 当前校验的值
                      //             // callback(flag, message) 方法必须执行且只能执行一次
                      //             //          flag = 是否通过了校验，不填写或者填写 null 代表不进行任何操作
                      //             //          message = 提示的类型，默认使用配置的 message
                      //             // target 行编辑的实例对象
                      //             if (cellValue === 55) {
                      //                 callback(false, '不能是abc')  // false = 未通过校验
                      //             } else {
                      //                 callback(true) // true = 通过验证
                      //             }
                      //         }
                      //
                      // },
                      ],

                  },
                          // // 自定义函数校验 handler
                          // handler(type, value, row, column, callback, target) {
                          //     // type 触发校验的类型（input、change、blur）
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
                          // message: '${title}默认提示'

                  },
              url: {
                  add: "/intel/znGoods/add",
                  edit: "/intel/znGoods/edit",
                  queryById: "/intel/znGoods/queryById"
              }
          }
      },
      computed: {
          formDisabled() {
              if (this.formBpm === true) {
                  if (this.formData.disabled === false) {
                      return false
                  }
                  return true
              }
              return this.disabled
          },
          showFlowSubmitButton() {
              if (this.formBpm === true) {
                  if (this.formData.disabled === false) {
                      return true
                  }
              }
              return false
          }
      },
      created() {
          //如果是流程中表单，则需要加载流程表单data
          this.showFlowData();
      },
      methods: {


          isDisabledAuth(code) {
              return disabledAuthFilter(code);
          },
          add() {
              this.edit({});
          },
          edit(record) {
              console.log('123456789')
              console.log(record)
              this.form.resetFields();
              this.model = Object.assign({}, record);
              this.minorderIf=this.model.minorder;
              this.manmualIf=this.model.manualQty;
              this.closeqtyQty=this.model.closeqty;
              this.minorstockQty=this.model.minorstock;
              this.conversionQty=this.model.conversion;
              console.log(this.minorderIf)
              console.log(this.manmualIf)
              console.log(this.closeqtyIf)
              console.log(this.model)
              this.visible = true;
              this.form.setFieldsValue(pick(record, 'manualQty'))
          },
          //     this.form.setFieldsValue(pick(this.model.row,'shopid','blid','cgid',
          //         'xlid','xlname','goodsname',
          //         'venderid','vendername','sendType',
          //         'classtype','normalprice','spec','minstock',
          //         'minorder','closeqty','qty','manualQty','sdate',
          //         'holidaytype','holidayBegindate','holidayEnddate',
          //         'seasontype','hoRate','zs','flag'))
          // },
          // },

          //渲染流程表单数据
          showFlowData() {
              if (this.formBpm === true) {
                  let params = {id: this.formData.dataId};
                  getAction(this.url.queryById, params).then((res) => {
                      if (res.success) {
                          this.edit(res.result);
                      }
                  });
              }
          },
          submitForm() {
              console.log(2255)
              const that = this;
              // 触发表单验证
              this.form.validateFields((err, values) => {
                  if (!err) {
                      that.confirmLoading = true;
                      let httpurl = '';
                      let method = '';
                      if (!this.model.id) {
                          httpurl += this.url.add;
                          method = 'post';
                      } else {
                          httpurl += this.url.edit;
                          method = 'put';
                      }
                      let formData = Object.assign(this.model, values);
                      console.log("表单提交数据", formData)
                      httpAction(httpurl, formData, method).then((res) => {
                          if (res.success) {
                              that.$message.success(res.message);
                              that.$emit('ok');
                          } else {
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

      }
  }
</script>
