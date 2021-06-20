<template>
  <a-card :visible="visible">
    <a-form :form="form">
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="机构名称">
        <a-input style="border:0px;" placeholder="" v-decorator="['departName', {}]"/>
      </a-form-item>
      <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="上级部门">
        <a-tree-select
          disabled
          style="width:100%;border: 0px;border: none;outline:none;"
          :dropdownStyle="{maxHeight:'200px',overflow:'auto'}"
          :treeData="treeData"
          v-model="model.parentId"
          placeholder="无">
        </a-tree-select>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="机构编码">
        <a-input style="border:0px;" placeholder="" v-decorator="['orgCode', {}]"/>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="机构类型">
          <a-radio-group :disabled="true" v-decorator="['orgCategory',{}]" placeholder="请选择机构类型">
            <a-radio value="1">
              公司
            </a-radio>
            <a-radio value="2">
              部门
            </a-radio>
            <a-radio value="3">
              岗位
            </a-radio>
          </a-radio-group>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="排序">
        <a-input-number style="border:0px;" v-decorator="[ 'departOrder',{}]"/>
      </a-form-item>
<!--      <a-form-item-->
<!--        :labelCol="labelCol"-->
<!--        :wrapperCol="wrapperCol"-->
<!--        label="超市机构编码">-->
<!--        <a-input placeholder="请输入超市机构编码" v-decorator="['csCode', validatorRules.csCode]" />-->
<!--      </a-form-item>-->
      <template v-if="codeNum == 3">
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="门店编码">
        <a-input placeholder="请输入门店编码" v-decorator="['csShopCode', {}]" />
      </a-form-item>
      </template>
      <template v-else-if="codeNum == 4">
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="部类编码">
        <a-input placeholder="请输入部类编码" v-decorator="['csBlCode', {}]" />
      </a-form-item>
      </template>
      <template v-else-if="codeNum == 5">
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="课类编码">
        <a-input placeholder="请输入课类编码" v-decorator="['csKlCode', {}]" />
      </a-form-item>
      </template>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="手机号">
        <a-input style="border:0px;" placeholder="" v-decorator="['mobile', {}]"/>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="地址">
        <a-input style="border:0px;" placeholder="" v-decorator="['address', {}]"/>
      </a-form-item>
      <a-form-item
        :labelCol="labelCol"
        :wrapperCol="wrapperCol"
        label="备注">
        <a-textarea style="border:0px;" placeholder="" v-decorator="['memo', {}]"/>
      </a-form-item>
    </a-form>
  </a-card>
</template>
<script>
  import pick from 'lodash.pick'
  import {queryIdTree} from '@/api/api'
  import { disabledAuthFilter } from "@/utils/authFilter"
  import {duplicateCheck } from '@/api/api'
  import Template1 from "../../jeecg/JVxeDemo/layout-demo/Template1";

  export default {
    name: 'DeptBaseInfo',
    components: {Template1},
    data() {
      return {
        departTree: [],
          codeNum:0,
        id: '',
        model: {},
        visible: false,
        disable: true,
        treeData: [],
        form: this.$form.createForm(this),
        labelCol: {
          xs: {span: 24},
          sm: {span: 3}
        },
          validatorRules:{
              csCode: {
                  rules: [
                      { required: true, message: '请输入超市机构编码' },
                      { validator: this.validateCsCode }
                  ]
              },
          },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16}
        },
      }
    },
    created() {
      this.loadTreeData();
    },
    methods: {
        validateCsCode(rule, value, callback){
            var params = {
                tableName: 'sys_depart',
                fieldName: 'cs_code',
                fieldVal: value,
                dataId: this.model.id,
            };
            duplicateCheck(params).then((res) => {
                if (res.success) {
                    callback()
                } else {
                    callback("超市机构编码已存在!")
                }
            })
        },
      loadTreeData() {
        queryIdTree().then((res) => {
          if (res.success) {
            for (let i = 0; i < res.result.length; i++) {
              let temp = res.result[i];
              this.treeData.push(temp);
            }
          }

        })
      },
      open(record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.codeNum = record.orgType;
          console.log(this.codeNum);
        console.log("record:");
        console.log(record);
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(record, 'orgCategory','departName', 'parentId', 'orgCode', 'departOrder', 'csShopCode', 'csBlCode' ,'csKlCode', 'mobile', 'fax', 'address', 'memo'));
        });
      },
      clearForm() {
        this.form.resetFields();
        this.treeData = [];
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>