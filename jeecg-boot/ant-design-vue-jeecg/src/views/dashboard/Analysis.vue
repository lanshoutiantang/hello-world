<template>
  <div>
    <index-chart v-if="indexStyle==1"></index-chart>
    <index-bdc v-if="indexStyle==2"></index-bdc>
    <index-task v-if="indexStyle==3"></index-task>
    <div style="width: 100%;text-align: right;margin-top: 20px">
      请选择首页样式：
      <a-radio-group v-model="indexStyle">
        <a-radio :value="0">修改密码</a-radio>
        <a-radio :value="1">统计图表</a-radio>
        <a-radio :value="2">统计图表2</a-radio>
        <a-radio :value="3">任务表格</a-radio>
      </a-radio-group>
    </div>

    <a-button @click="firstUpdatePassword" type="primary" icon="plus">跳转</a-button>
    <user-passwo  @success="loginSelectOk"></user-passwo>
    <user-passwo ref="userPasswo"></user-passwo>
  </div>
</template>

<!--<script language="JavaScript" type="text/javascript">-->

<!--    window.location.href="http://www.dayanmei.com/";-->

<!--</script>-->

<script>
  import IndexChart from './IndexChart'
  import IndexTask from "./IndexTask"
  import IndexBdc from './IndexBdc'
  import UserPasswo from "./UserPasswo";
  import { timeFix } from "@/utils/util"
  import {getAction, putAction} from "../../api/manage";

  export default {
    name: "Analysis",
    components: {
        UserPasswo,
      IndexChart,
      IndexTask,
      IndexBdc
    },
    data() {
      return {
        indexStyle:1
        //   userPassword1:0
      }

    },
    created() {
        // this.$refs.userPasswo.show()

    },
    methods: {
        loginSelectOk(){
            this.loginSuccess()
        },
        loginSuccess () {
            // update-begin- author:sunjianlei --- date:20190812 --- for: 登录成功后不解除禁用按钮，防止多次点击
            // this.loginBtn = false
            // update-end- author:sunjianlei --- date:20190812 --- for: 登录成功后不解除禁用按钮，防止多次点击
            this.$router.push({ path: "/dashboard/analysis" }).catch(()=>{
                console.log('登录跳转首页出错,这个错误从哪里来的')
            })
            this.$notification.success({
                message: '欢迎',
                description: `${timeFix()}，欢迎回来`,
            });
        },

        // firstUpdatePassword(record) {
        //     console.log(record)
        //     let params = {planid: record.planid,status: record.status,id: record.id};
        //     // let params1 = {status: record.status};
        //     putAction("/intel/znPromPlan/checkStatus", params).then((res)=>{
        //         if(res.success){
        //             console.log(res)
        //         }
        //     })
        // },
        firstUpdatePassword() {
            getAction("/sys/user/firstUpdatePassword").then((res)=>{
                console.log(res);
                console.log(res[0]);
                console.log(res[1]);
                // var pass = r.password;
                // console.log(pass);
                if(res[1] == '123456'){
                    console.log('22');
                    // this.$router.push({ path: "/dashboard/UserPasswo" })
                    //     let username = this.userInfo().username
                    //     console.log(username);
                        this.$refs.userPasswo.show(res[0])
                }else {

                }
                if(res.success){
                    console.log(res)
                }
            })
            // putAction().then((res)=>{
            //     if(res.success){
            //         console.log(res)
            //     }
            //
            // })
        },

    }
  }
</script>