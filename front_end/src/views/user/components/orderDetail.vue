<template>
   <div class = "content">
       <h1 class ="cont_top">
           订单信息
       </h1>
       <div class ="cont_mid">
           <p>
               <span>酒店名称：</span>
               <em></em>
           </p>
           <p>
               <span>地址：</span>
               <em></em>
           </p>
           <p>
               <span>电话：</span>
               <em></em>
           </p>
           <p>
               <span>订单状态：</span>
               <em></em>
           </p>
           <p>
               <span>预定时间：</span>
               <em></em>
           </p>
           <p>
               <span>订单号：</span>
               <em></em>
           </p>

       </div>
       <h1 class ="cont_top">
           入住信息
       </h1>
       <div class ="cont_mid">
           <p>
               <span class = "itemListTitle">房型：
               </span>
               <em class = "itemList"></em>
               <em class = "itemList">
                   <b>住客人数：
                       <i></i>
                   </b>
               </em>
           </p>
           <p>
               <span class = "itemListTitle">早餐信息：
               </span>
               <em class="itemList"></em>
           </p>
           <p>
               <span class="itemListTitle">
                   入住人：
               </span>
               <em class="itemList">
                   <b></b>
               </em>
           </p>
           <p>
               <span class="itemListTitle">入离日期：
               </span> 
               <em class="itemList">
                   <b>入住 <span>
                       </span>
                       离店
                       <span></span> 
                   </b>
               </em>
           </p>
           <p>
               <span class="itemListTitle">订单总价：
                   <em class="itemList">
                   </em>
               </span>
           </p>
       </div>
       <h1 class ="cont_top">
           联系信息
       </h1>
       <div class ="cont_mid">
           <p>
               联系人：
           </p>
           <em>
           </em>
           <p>
               手机号码：
           </p>
           <em>
           </em>
           <p>
               邮箱：
           </p>
           <em>
           </em>
           <p>
               其他要求：
           </p>
           
       </div>
       
   </div>
</template>

<style>
.cont_top {
    height: 37px;
    line-height: 37px;
    color: #612551;
    border-bottom: 1px dashed #e4dfdc;
}
</style>
<script>

import {mapGetters,mapMutations,mapActions} from 'vuex'


export default ({
  name: "orderDetail",
  computed:{
      ...mapGetters(
          [
              'orderDetail'
          ]
      )
  },
  methods:{
      ...mapMutations([
          'set_orderDetail'
      ]),
      fetchOrderDetail(){
          
      }
  },
  watch:{
      $route(to, from){

      }
  },
  // TODO 获取order相关信息
  created(){},
})
</script>
