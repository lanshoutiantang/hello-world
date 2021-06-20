<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    switchFullscreen
    @ok="handleOk"
    :okButtonProps="{ class:{'jee-hidden': disableSubmit} }"
    @cancel="handleCancel"
    cancelText="取消">
    <zn-goods-form1 ref="realForm" @ok="submitCallback" :disabled="disableSubmit"></zn-goods-form1>
  </j-modal>
</template>

<script>

  import ZnGoodsForm1 from './ZnGoodsForm1'
  export default {
    name: 'ZnGoodsModal1',
    components: {
      ZnGoodsForm1
    },
    data () {
      return {
        title:'',
        width:800,
        visible: false,
        disableSubmit: false
      }
    },
    methods: {
      add () {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.add();
        })
      },
      edit (record) {
        this.visible=true
        this.$nextTick(()=>{
          this.$refs.realForm.edit(record);
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
          console.log(225)
        this.$refs.realForm.submitForm();
          console.log(2258)
      },
      submitCallback(){
        this.$emit('ok');
        this.visible = false;
      },
      handleCancel () {
        this.close()
        console.log(256)
      }
    }
  }
</script>