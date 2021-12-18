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
               <span>地址</span>
               <em></em>
           </p>
           <p>
               <span>电话</span>
               <em></em>
           </p>
           <p>
               <span>订单状态</span>
               <em></em>
           </p>
           <p>
               <span>预定时间</span>
               <em></em>
           </p>
           <p>
               <span>订单号</span>
               <em></em>
           </p>

       </div>
       <h1 class ="cont_top">
           入住信息
       </h1>
       <div class ="cont_mid">
       </div>
       <h1 class ="cont_top">
           联系信息
       </h1>
       <div class ="cont_mid">
       </div>
       
   </div>
</template>

<script>




export default ({
  name: "orderDetail",
  watch:{
      $route(to, from){

      }
  }
})
</script>
