<template>
<div>
<div class="nav">
    <a-breadcrumb>
    <a-breadcrumb-item href="">
      <a-icon type="home" />
    </a-breadcrumb-item>
    <a-breadcrumb-item href="">
      <a-icon type="user" />
      <span>我的订单</span>
    </a-breadcrumb-item>
    <a-breadcrumb-item>
      订单详情
    </a-breadcrumb-item>
  </a-breadcrumb>
</div>
<section class="mid_page of">
<!-- <div class="pageNav of">
    <h4 class="s12">
        <p class="lfloat">
            <em></em>
            订单详情
            <i></i>
            {{orderDetail.hotelName}}
        </p>
    </h4>
</div> -->
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
               <em v-bind:class= "orderState" class="itemList s12 color">
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
               <em class="itemList"><b>不含餐饮</b></em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">
                   入住人：
               </span>
               <em class="itemList">
                   <b>{{orderDetail.clientName}}</b>
               </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">入离日期：
               </span> 
               <em class="itemList">
                   <b>入住:
                       <span class="resetColor">{{orderDetail.checkInDate}};</span>
                       <span class="resetColor">离店:</span>
                       <span class="resetColor">{{orderDetail.checkOutDate}}</span>
                       <span class="resetColor"> 共{{days}}晚</span>
                   </b>
               </em>
           </p>
           <p>
               <span class="ltar s12 itemListTitle">订单总价：
               </span>
                   <em class="itemList redColor s14"><b>
                       {{orderDetail.price}} CNY
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
               <em class="itemList s12">
                    <b>{{orderDetail.phoneNumber}}
                    </b>
               </em>
           </p>
    
           <p>
               <span class="ltar s12 itemListTitle">
               邮箱：
               </span>
           </p>
           <em>
           </em>

           
       </div>
       
   </div>
</section>

<!-- <aside class="right_page rfloat">
    <div class="content">
        <div class="asideTitle">
            <h2 class="s16">
                酒店公告
            </h2>
            <div class="cont_item">
            </div>
        </div>
    </div>
</aside> -->
</section>
</div>
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
.nav{
    padding-top: 20px;
    padding-left: 30px;
    padding-bottom: 20px;
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
.reserved{
    color: #1890ff;
}
.executed{
    color: #52c41a;
}
.commented{
    color: grey;
}
.annuled{
    color: #f5222d;
}
.ltar{
    text-align: right;
}
.cont_mid p .resetColor{
    color:#333
}
.cont_top {
    height: 37px;
    line-height: 37px;
    color: #612551;
    border-bottom: 1px dashed #e4dfdc;
}
.left_page{
    padding: 17px 33px 0 33px;
    width: 890px;
    height: auto;
    background-color: #fff;
    border-width: 1px;
    border-style: solid;
    border-color: #e4dfdc;
}
.right_page{
    background: #ada9a5;
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
  data(){
      return {
          days: 1,
      }
  },
  computed:{
      ...mapGetters(
          [
              'orderDetail'
          ]
      ),
      orderState: function(){
          if (this.orderDetail.orderState==="已预订"){
              return {
                  reserved: true
              }
          }
          else if(this.orderDetail.orderState==="已执行"){
              return {
                  executed: true
              }
          }
          else if(this.orderDetail.orderState==="已撤销"){
              return{
                  annuled: true
              }
          }
          else {
              return{
                  commented: true
              }
          }
          
      }
  },
  methods:{
      ...mapMutations([
          'set_orderDetail'
      ]),
      ...mapActions([
          'getOrderDetail'
          ]),
      getDays(date1, date2){
        var d1 = new Date(date1);
        var d2 = new Date(date2);
        var days = (d2.getTime() - d1.getTime()) / (1000 * 3600 * 24);
        return days;
      }
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
        this.days = this.getDays(this.orderDetail.checkInDate, this.orderDetail.checkOutDate);
        // console.log(this.$store);
    }
}
</script>
