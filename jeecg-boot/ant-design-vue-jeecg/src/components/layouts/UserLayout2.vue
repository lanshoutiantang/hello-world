<template>
  <div id="userLayout" :class="['user-layout-wrapper', device]">
    <div class="container">
      <div class="top">
        <div class="header">
          <a href="/">
<!--            <img src="~@/assets/logo.svg" class="logo" alt="logo">-->
            <img src="~@/assets/beijing2.png" class="logo" alt="logo">
<!--            <span class="title">Jeecg Boot</span>-->
          </a>
        </div>
<!--        <div class="desc">-->
<!--          &lt;!&ndash;          Jeecg Boot 是中国最具影响力的 企业级 快速开发平台&ndash;&gt;-->
<!--          &lt;!&ndash;          超市商品补货系统&ndash;&gt;-->
<!--          智能补货系统-->
<!--        </div>-->

      </div>

      <div class="desc">
        <!--          Jeecg Boot 是中国最具影响力的 企业级 快速开发平台-->
        <!--          超市商品补货系统-->
        智能补货系统
      </div>

      <route-view></route-view>

      <div class="footer">
        <div class="links">
          <a href="http://doc.jeecg.com" target="_blank">帮助</a>
          <a href="https://github.com/zhangdaiscott/jeecg-boot" target="_blank">隐私</a>
          <a href="https://github.com/zhangdaiscott/jeecg-boot/blob/master/LICENSE" target="_blank">条款</a>
        </div>
        <div class="copyright">
          Copyright &copy; 2019 <a href="http://www.jeecg.com" target="_blank">JEECG开源社区</a> 出品
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import RouteView from "@/components/layouts/RouteView"
  import { mixinDevice } from '@/utils/mixin.js'

  export default {
    name: "UserLayout",
    components: { RouteView },
    mixins: [mixinDevice],
    data () {
      return {}
    },
    mounted () {
      document.body.classList.add('userLayout')
    },
    beforeDestroy () {
      document.body.classList.remove('userLayout')
    },
  }
</script>

<style lang="less" scoped>
  #userLayout.user-layout-wrapper {
    height: 100%;

    &.mobile {
      .container {
        .main {
          max-width: 368px;
          width: 98%;
        }
      }
    }

    .container {
      width: 100%;
      min-height: 100%;
      background: #f0f2f5 url(~@/assets/beijing1.jpg) no-repeat 50%;    // liu
      /*background: #f0f2f5 url(~@/assets/background.svg) no-repeat 50%;*/
      background-size: 100%;
      padding: 110px 0 144px;
      position: relative;

      a {
        text-decoration: none;
      }

      .top {
        text-align: center;

        .header {
          height: 44px;
          line-height: 44px;

          .badge {
            position: absolute;
            display: inline-block;
            line-height: 1;
            vertical-align: middle;
            margin-left: -12px;
            margin-top: -10px;
            opacity: 0.8;
          }

          .logo {
            /*width: 28px;*/
            /*height: 28px;*/
            height: 200px;    // liu
            /*vertical-align: top;*/
            margin-top: 150px;  // liu
            margin-right: 900px;   // liu
            margin-bottom: 150px;  // liu
            margin-left: 0px;  // liu
            border-style: none;
            /*width: 300px;*/
          }

          .title {
            font-size: 33px;
            color: rgba(0, 0, 0, .85);
            font-family: "Chinese Quote", -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
            font-weight: 600;
            position: relative;
            top: 2px;
          }
        }
        /*.desc {*/
        /*  !*position: absolute;*!*/
        /*  font-size: 35px;*/
        /*  color: rgba(0, 0, 0, 0.45);*/
        /*  margin-top: 12px;*/
        /*  margin-left: 1100px;    // liu*/
        /*  margin-right: 400px;// liu*/
        /*  margin-bottom: 40px;*/
        /*}*/
      }

      .desc {
        /*position: absolute;*/
        font-size: 35px;
        color: rgba(0, 0, 0, 0.45);
        margin-top: 12px;
        margin-left: 1200px;    // liu
        margin-right: 300px;// liu
        margin-bottom: 40px;
      }

      .main {
        min-width: 368px;   // liu
        /*max-width: 368px ;*/
/*        margin-top: 50px;   // liu
        margin-right: 400px;
        margin-left: 1100px;
        margin-bottom: 122px;*/
        margin-left: 1122px;
        width: 368px;
        /*margin: 0 auto;*/
      }

      .footer {
        position: absolute;
        width: 100%;
        bottom: 0;
        padding: 0 16px;
        margin: 48px 0 24px;
        text-align: center;

        .links {
          margin-bottom: 8px;
          font-size: 14px;
          a {
            color: rgba(0, 0, 0, 0.45);
            transition: all 0.3s;
            &:not(:last-child) {
              margin-right: 40px;
            }
          }
        }
        .copyright {
          color: rgba(0, 0, 0, 0.45);
          font-size: 14px;
        }
      }
    }
  }
</style>