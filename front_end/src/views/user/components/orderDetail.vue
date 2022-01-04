<template>
<section class="mid_page of">
<div class="pageNav of">
    <h4 class="s12">
        <p class="lfloat">
            <em></em>
            订单详情
            <i></i>
            {{orderDetail.hotelName}}
        </p>
    </h4>
</div>
<section class="left_page lfloat">
   <div class = "content">
       <h1 class ="cont_top">
           订单信息
       </h1>
       <div class ="cont_mid">
           <p>
               <span class="ltar s12 itemListTitle">酒店名称：</span>
               <em class="itemList s14">
                   <b>{{orderDetail.hotelName}}
                   </b>
                   </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">地址：</span>
               <em class="itemList s12">
                   <b>{{orderDetail.address}}
                   </b>
                   </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">电话：</span>
               <em class="itemList s12">
                   <b>
                       {{orderDetail.hotelPhone}}
                   </b></em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">订单状态：</span>
               <em class="itemList s12">
                   <b>{{orderDetail.orderState}}</b>
                   </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">预定时间：</span>
               <em class="itemList s12">
                   <b>{{orderDetail.createDate}}
                   </b>
                   </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">订单号：</span>
               <em class="itemList s12">
                   <b>
                       {{orderDetail.id}}
                   </b>
                   </em>
           </p>

       </div>
       <h1 class ="cont_top">
           入住信息
       </h1>
       <div class ="cont_mid">
           <p>
               <span class="ltar s12 itemListTitle">房型：
               </span>
               <em class = "itemList s12 padLeft60"><b>{{orderDetail.roomType}}</b></em>
               <em class = "itemList s12">
                   <b>住客人数：
                       <i>{{orderDetail.peopleNum}}</i>
                   </b>
               </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">早餐信息：
               </span>
               <em class="itemList"><b></b></em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">
                   入住人：
               </span>
               <em class="itemList">
                   <b></b>
               </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">入离日期：
               </span> 
               <em class="itemList">
                   <b>入住 <span>
                       {{orderDetail.checkInDate}}
                       </span>
                       离店
                       {{orderDetail.checkOutDate}}
                       <span></span> 
                   </b>
               </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">订单总价：
               </span>
                   <em class="itemList redColor s14"><b>
                       {{orderDetail.price}}
                       </b>
                   </em>
           </p>
       </div>
       <h1 class ="cont_top">
           联系信息
       </h1>
       <div class ="cont_mid">
           <p>
               <span class="ltar s12 itemListTitle">
               联系人：
               </span>
                <em class="itemList s12">
               <b>
               {{orderDetail.clientName}}
               </b>
                </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">
               手机号码：
               </span>
           </p>
           <em class="itemList s12">
               <b>
               {{orderDetail.phoneNumber}}
               </b>
           </em>
           <p>
               <span class="ltar s12 itemListTitle">
               邮箱：
               </span>
           </p>
           <em>
           </em>
           <p>
               <span class="ltar s12 itemListTitle">
               其他要求：
               </span>
           </p>
           
       </div>
       
   </div>
</section>

<aside class="right_page rfloat">
    <div class="content">
        <div class="asideTitle">
            <h2 class="s16">
                酒店公告
            </h2>
            <div class="cont_item">
            </div>
        </div>
    </div>
</aside>
</section>
</template>

<style>
body {
    font-size: 12px;
    font-family: arial,"microsoft yahei",simsun,sans-self;
    overflow-y: scroll;
}
em, i{
    font-style:normal;
}
b{
    font-weight: bold;
}
.of{
    overflow: hidden;
}
.lfloat{
    float:left;
}
.rfloat{
    float:right;
}
.ltar{
    text-align: right;
}
.cont_top {
    height: 37px;
    line-height: 37px;
    color: #612551;
    border-bottom: 1px dashed #e4dfdc;
}
.left_page{
    padding: 17px 33px 0 33px;
    width: 590px;
    height: auto;
    background-color: #fff;
    border-width: 1px;
    border-style: solid;
    border-color: #e4dfdc;
}
.right_page{
    padding: 15px;
    width: 300px;
}
.right_page .content{
    width: 300px;
    height: auto;
    margin: 0 auto;
}
.cont_mid p{
    color: #333;
    padding: 5px 0;
}
.cont_mid p span{
    color: gray;
}
.cont_mid .itemListTitle{
    width: 75px;
    display: inline-block;
    vertical-align: top;
}
.cont_mid .itemList{
    max-width: 410px;
    display: inline-block;
    margin-left: 9px;
}
.cont_mid .redColor{
    color: #f60;
}
.cont_mid .itemList i{
    padding-right: 5px;
}
.cont_mid .padLeft60{
    padding-right: 60px;
}
.mid_page{
    width: 990px;
    margin: 0 auto;
}
.s12{
    font-size: 12px;
}
.s14{
    font-size: 14px;
}
.s16{
    font-size: 16px;
}
.pageNav{
    line-height: 52px;
    height: 50px;
    color: #666;
}
.pageNav p{
    width: 300px;
}
.pageNav em{
    height: 14px;
    margin-right: 5px;
    background-position: -76px -4px;
    margin-top: -2px;
    width: 12px;
    /* display: inline-block;
    vertical-align: middle; */
    background-image: url(./icon.png);
}
.pageNav i{
    height: 13px;
    width: 8px;
    background-position: -76px -24px;
    margin-left: 4px;
    margin-right: 4px;
    margin-top: -4px;
    /* display: inline-block;
    vertical-align: middle; */
    background-image: url(./icon.png);

}
</style>
<script>

import {mapGetters,mapMutations,mapActions} from 'vuex'


export default {
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
      ...mapActions([
          'getOrderDetail'
          ])
  },
  watch:{
      $route(to, from){

      }
  },
  // TODO 获取order相关信息
//   created(){
//       this.getOrderDetail;
//   },
    mounted(){

        this.getOrderDetail(this.$route.params.orderId);
        // console.log(this.$store);
    }
}
</script>
