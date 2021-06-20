<template>
  <div class="page-header-index-wide">
    <a-row :gutter="24">
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="您当前所在位置" >
          <span style="font-size:26px">{{shopName}}</span>
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>

        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="商品总数" >
          <div style="width: 50px">
            <span style="font-size:32px">{{goodsCount}}</span>
          </div>
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>

        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="系统建议补货商品数">
          <div>
            <span style="font-size:26px">{{qty}}</span>
          </div>
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>

        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="需要补货商品总价值预算">
          <div>
            <span style="font-size:26px">{{sumPrice}}</span>
          </div>
          <a-tooltip title="指标说明" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>

        </chart-card>
      </a-col>
    </a-row>

    <a-card :loading="loading" :bordered="false" :body-style="{padding: '0'}">
      <div class="salesCard">
        <a-tabs default-active-key="1" size="large" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">

          <a-tab-pane loading="true" tab="补货数量" key="1">
            <a-row>
              <a-col :xl="16" :lg="12" :md="12" :sm="24" :xs="24">
                <bar title="补货数量排行榜" :dataSource="barData"/>
              </a-col>
              <a-col :xl="8" :lg="12" :md="12" :sm="24" :xs="24">
                <rank-list title="需要补货商品数量排行榜" :list="rankList"/>
              </a-col>
            </a-row>
          </a-tab-pane>

        </a-tabs>
      </div>
    </a-card>

    <a-row>
      <a-col :span="24">
        <a-card :loading="loading" :bordered="false" title="最近一周访问量统计" :style="{ marginTop: '24px' }">
          <a-row>
            <a-col :span="6">
              <head-info title="今日IP" :content="loginfo.todayIp"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="environment" style="font-size: 24px"  />
              </a-spin>
            </a-col>
            <a-col :span="6">
              <head-info title="今日访问" :content="loginfo.todayVisitCount"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="team" style="font-size: 24px"  />
              </a-spin>
            </a-col>
            <a-col :span="6">
              <head-info title="总访问量" :content="loginfo.totalVisitCount"></head-info>
            </a-col>
            <a-col :span="2">
              <a-spin class='circle-cust'>
                <a-icon slot="indicator" type="rise" style="font-size: 24px"  />
              </a-spin>
            </a-col>
          </a-row>
          <line-chart-multid :fields="visitFields" :dataSource="visitInfo"></line-chart-multid>
        </a-card>
      </a-col>
    </a-row>
    <user-passwo ref="userPasswo"></user-passwo>
  </div>
</template>

<script>
  import ChartCard from '@/components/ChartCard'
  import ACol from "ant-design-vue/es/grid/Col"
  import ATooltip from "ant-design-vue/es/tooltip/Tooltip"
  import MiniArea from '@/components/chart/MiniArea'
  import MiniBar from '@/components/chart/MiniBar'
  import MiniProgress from '@/components/chart/MiniProgress'
  import RankList from '@/components/chart/RankList'
  import Bar from '@/components/chart/Bar'
  import LineChartMultid from '@/components/chart/LineChartMultid'
  import HeadInfo from '@/components/tools/HeadInfo.vue'


  import Trend from '@/components/Trend'
  import { getLoginfo,getVisitInfo } from '@/api/api'
  import {getAction} from "../../api/manage";
  import UserPasswo from "./UserPasswo.vue";

  const rankList = []

  const barData = []

  export default {
    name: "IndexChart",
    components: {
        UserPasswo,
      ATooltip,
      ACol,
      ChartCard,
      MiniArea,
      MiniBar,
      MiniProgress,
      RankList,
      Bar,
      Trend,
      LineChartMultid,
      HeadInfo
    },
      data() {
          return {
              loading: true,
              center: null,
              rankList,
              barData,
              loginfo: {},
              visitFields: ['ip', 'visit'],
              visitInfo: [],
              indicator: <a-icon type="loading" style="font-size: 24px" spin/>,
              shopName: '',
              goodsCount: null,
              qty: null,
              sumPrice: null,
              goodsLists: [],
              numLists: []
          }
      },
    created() {
      setTimeout(() => {
        this.loading = !this.loading
      }, 1000)
      this.initLogInfo();
    },

      mounted: function () {

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

          this.$http.get('/dashboard/indexChart').then(response => {
              this.shopName = response.shopname;
              this.goodsCount = response.shopGoodsCount;
              this.qty = response.qty;
              this.goodsLists = response.goodsNameList;
              this.numLists = response.numList;
              this.sumPrice = response.priceCount;
              for (let i = 0; i < 7; i++) {
                  rankList.push({
                      name: this.goodsLists[i],
                      total: this.numLists[i]
                  })
              }
              for (let i = 0; i < 7; i += 1) {
                  barData.push({
                      x: `${i+1}`,
                      y: this.numLists[i]
                  })
              }

          }, response => {
              console.log("没有请求到");
          });
      },
    methods: {
      initLogInfo () {
        getLoginfo(null).then((res)=>{
          if(res.success){
            Object.keys(res.result).forEach(key=>{
              res.result[key] =res.result[key]+""
            })
            this.loginfo = res.result;
          }
        })
        getVisitInfo().then(res=>{
          if(res.success){
             this.visitInfo = res.result;
           }
         })
      },

    }
  }
</script>

<style lang="less" scoped>
  .circle-cust{
    position: relative;
    top: 28px;
    left: -100%;
  }
  .extra-wrapper {
    line-height: 55px;
    padding-right: 24px;

    .extra-item {
      display: inline-block;
      margin-right: 24px;

      a {
        margin-left: 24px;
      }
    }
  }

  /* 首页访问量统计 */
  .head-info {
    position: relative;
    text-align: left;
    padding: 0 32px 0 0;
    min-width: 125px;

    &.center {
      text-align: center;
      padding: 0 32px;
    }

    span {
      color: rgba(0, 0, 0, .45);
      display: inline-block;
      font-size: .95rem;
      line-height: 42px;
      margin-bottom: 4px;
    }
    p {
      line-height: 42px;
      margin: 0;
      a {
        font-weight: 600;
        font-size: 1rem;
      }
    }
  }
</style>