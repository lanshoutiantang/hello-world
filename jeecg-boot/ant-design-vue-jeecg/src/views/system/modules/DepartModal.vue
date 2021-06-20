<template>
  <a-modal
    :title="title"
    :width="800"
    :ok=false
    :visible="visible"
    :confirmLoading="confirmLoading"
    :okButtonProps="{ props: {disabled: disableSubmit} }"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="机构名称"
          :hidden="false"
          hasFeedback >
<!--          <a-input id="departName" placeholder="请输入机构/部门名称" v-decorator="['departName', validatorRules.departName ]"/>-->

<!--          <template v-if="isDepart">
            <a-form-item >
              <j-search-select-tag   v-decorator="['departName', validatorRules.departName]"  :trigger-change="true"    placeholder="请做出你的选择"  :dict="orgCode">
              </j-search-select-tag>
            </a-form-item>
          </template>
          <template v-else>
            <a-input id="departName" placeholder="请输入机构/部门名称" v-decorator="['departName', validatorRules.departName]"/>
&lt;!&ndash;                <j-tree-dict id="departName" v-decorator="['departName', validatorRules.departName]" placeholder="请输入机构/部门名称" parentCode="A01" />&ndash;&gt;
          </template>-->

          <template v-if="isDepart">
            <a-form-item >
              <j-search-select-tag   v-decorator="['departName', validatorRules.departName]"  :trigger-change="true"    placeholder="请做出你的选择"
                                     :dict="`sys_category,name,code,pid=`+`'`+`${pid}`+`'`" >
              </j-search-select-tag>
            </a-form-item>
          </template>
          <template v-else>
            <a-input id="departName" placeholder="请输入机构/部门名称" v-decorator="['departName', validatorRules.departName]"/>
          </template>


        </a-form-item>
        <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" :hidden="seen" label="上级部门" hasFeedback>
        <a-tree-select
          style="width:100%"
          :dropdownStyle="{maxHeight:'200px',overflow:'auto'}"
          :treeData="departTree"
          v-model="model.parentId"
          placeholder="请选择上级部门"
          :disabled="condition">
        </a-tree-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="机构类型">
          <template v-if="seen">
          <a-radio-group v-decorator="['orgCategory',validatorRules.orgCategory]" placeholder="请选择机构类型">
            <a-radio value="1">
              公司
            </a-radio>
          </a-radio-group>
          </template>
          <template v-else>
            <a-radio-group v-decorator="['orgCategory',validatorRules.orgCategory]" placeholder="请选择机构类型">
              <a-radio value="2">
                部门
              </a-radio>
              <a-radio value="3">
                岗位
              </a-radio>
            </a-radio-group>
          </template>
        </a-form-item>
        <!--      <a-form-item-->
        <!--        :labelCol="labelCol"-->
        <!--        :wrapperCol="wrapperCol"-->
        <!--        label="超市机构编码">-->
        <!--        <a-input placeholder="请输入超市机构编码" v-decorator="['csCode', validatorRules.csCode]" />-->
        <!--      </a-form-item>-->
        <template v-if="codeNum == 3 && !isDepart ">
          <a-form-item
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            label="门店编码">
            <a-input placeholder="请输入门店编码" v-decorator="['csShopCode',validatorRules.csShopCode]" />
          </a-form-item>
        </template>
<!--        <template v-else-if="codeNum == 4">-->
<!--          <a-form-item-->
<!--            :labelCol="labelCol"-->
<!--            :wrapperCol="wrapperCol"-->
<!--            label="部类编码">-->
<!--            <a-input placeholder="请输入部类编码" v-decorator="['csBlCode']" />-->
<!--          </a-form-item>-->
<!--        </template>-->
<!--        <template v-else-if="codeNum == 5">-->
<!--          <a-form-item-->
<!--            :labelCol="labelCol"-->
<!--            :wrapperCol="wrapperCol"-->
<!--            label="课类编码">-->
<!--            <a-input placeholder="请输入课类编码" v-decorator="['csKlCode']" />-->
<!--          </a-form-item>-->
<!--        </template>-->
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="电话">
          <a-input placeholder="请输入电话" v-decorator="['mobile',validatorRules.mobile]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="传真">
          <a-input placeholder="请输入传真" v-decorator="['fax', {}]"  />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="地址">
          <a-input placeholder="请输入地址" v-decorator="['address', {}]"  />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="排序">
          <a-input-number v-decorator="[ 'departOrder',{'initialValue':0}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-textarea placeholder="请输入备注" v-decorator="['memo', {}]"  />
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import { queryIdTree } from '@/api/api'
  import pick from 'lodash.pick'
  import ATextarea from 'ant-design-vue/es/input/TextArea'
  import {getAction} from "../../../api/manage";
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'
  import JTreeDict from "@/components/jeecg/JTreeDict"

  export default {
    name: "SysDepartModal",
    components: { ATextarea,JSearchSelectTag,JTreeDict },
    data () {
      return {
          pid:"",
          isDepart:false,
          selectedParentId:'',
          orgCode:'',
          orgCodeValue:'',
          codeNum:0,
        departTree:[],
        orgTypeData:[],
        phoneWarning:'',
        departName:"",
        title:"操作",
        seen:false,
        visible: false,
        condition:true,
        disableSubmit:false,
        model: {},
        menuhidden:false,
        menuusing:true,
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
            csShopCode:{rules: [{ required: true, message: '请输入门店编码!' }]},
        departName:{rules: [{ required: true, message: '请输入机构/部门名称!' }]},
        orgCode:{rules: [{ required: true, message: '请输入机构编码!' }]},
         mobile:{rules: [{validator:this.validateMobile}]}
        },
        url: {
          add: "/sys/sysDepart/add",
            getOrgLength: "/sys/sysDepart/length",
        },
        dictDisabled:true,
      }
    },
    created () {
    },
    methods: {
      loadTreeData(){
        var that = this;
        queryIdTree().then((res)=>{
          if(res.success){
            that.departTree = [];
            for (let i = 0; i < res.result.length; i++) {
              let temp = res.result[i];
              that.departTree.push(temp);
            }
          }

        })
      },
      add (depart) {
        if(depart){
          this.seen = false;
          this.dictDisabled = false;
        }else{
          this.seen = true;
          this.dictDisabled = true;
        }
        this.edit(depart);
      },
      edit (record) {
          this.form.resetFields();
          this.model = Object.assign({}, {});
          this.visible = true;
          this.loadTreeData();
          this.model.parentId = record!=null?record.toString():null;
          if(this.seen){
            this.model.orgCategory = '1';
          }else{
            this.model.orgCategory = '2';
              this.codeNum = record.orgType;
              console.log(this.codeNum);
          }
          if(this.model.parentId != null){
              let params = {parentId: this.model.parentId};
              getAction(this.url.getOrgLength,params).then((length)=>{
                  this.codeNum = length.result+1;
                  console.log(this.codeNum);
              })
              console.log(this.model.parentId)
          }
          console.log("record:");
          this.$nextTick(() => {
            this.form.setFieldsValue(pick(this.model,'orgCategory','departName','departNameEn','departNameAbbr','departOrder','description','orgType','orgCode','csShopCode', 'csBlCode' ,'csKlCode','mobile','fax','address','memo','status','delFlag'))
          });
      },
      close () {
        this.$emit('close');
        this.disableSubmit = false;
        this.visible = false;
      },

      // handleOk () {
      //   const that = this;
      //   // 触发表单验证
      //   this.form.validateFields((err, values) => {
      //     if (!err) {
      //       that.confirmLoading = true;
      //       let formData = Object.assign(this.model, values);
      //       //时间格式化
      //       console.log(formData)
      //       httpAction(this.url.add,formData,"post").then((res)=>{
      //         if(res.success){
      //           that.$message.success(res.message);
      //           that.loadTreeData();
      //           that.$emit('ok');
      //         }else{
      //           that.$message.warning(res.message);
      //         }
      //       }).finally(() => {
      //         that.confirmLoading = false;
      //         that.close();
      //       })
      //
      //     }
      //   })
      // },

        /*handleOk () {

            const that = this;
            // 触发表单验证
            this.form.validateFields((err, values) => {
                if (!err) {
                    that.confirmLoading = true;
                    let formData = Object.assign(this.model, values)
                    let params = {
                        formData,
                        orgCodeValue:this.model.departName
                    }


                    httpAction(this.url.add,params,"post").then((res)=>{
                        if(res.success){
                            that.$message.success(res.message);
                            that.loadTreeData();
                            that.$emit('ok');
                        }else{
                            that.$message.warning(res.message);
                        }
                    }).finally(() => {
                        that.confirmLoading = false;
                        that.close();
                        // that.$nextTick(() => {
                        //     this.form.setFieldsValue(pick(this.model,'orgCategory','departName','departNameEn','departNameAbbr','departOrder','description','orgType','orgCode','csCode','mobile','fax','address','memo','status','delFlag'))
                        // });
                    })

                }
            })
        },*/

        handleOk () {

            const that = this;
            // 触发表单验证
            this.form.validateFields((err, values) => {
                if (!err) {
                    that.confirmLoading = true;
                    let formData = Object.assign(this.model, values)
                    let params = {
                        formData,
                        departID:this.model.departName
                    }

                    httpAction(this.url.add,params,"post").then((res)=>{
                        if(res.success){
                            that.$message.success(res.message);
                            that.loadTreeData();
                            that.$emit('ok');
                        }else{
                            that.$message.warning(res.message);
                        }
                    }).finally(() => {
                        that.confirmLoading = false;
                        that.close();

                    })

                }
            })
        },

      handleCancel () {
        this.close()
      },
      validateMobile(rule,value,callback){
        if (!value || new RegExp(/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/).test(value)){
          callback();
        }else{
          callback("您的手机号码格式不正确!");
        }

      }
    }
  }
</script>

<style scoped>

</style>