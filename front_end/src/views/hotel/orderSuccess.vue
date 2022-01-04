+<template>
 <div class="os">
  <a-result 
    status="success"
    title="预订成功！"
  >
<!-- 
    <template #extra>
      <a-button key="console" >
        回到首页
      </a-button>
       <a-button icon="search" key="buy">
        查看订单详情
      </a-button>
    </template> -->
  </a-result>
  <v-card
                                                    width="300px"
                                                    class="mx-auto hotel-card"
                                            >
                                           
                                                <v-img
                                                        v-bind:src="getHotelPicUrl(hotelId)"
                                                        height="300px"
                                                ></v-img>
                                   
                                                <v-card-title class="font-weight-black text--secondary">
                                                    {{hotelName}}
                                                </v-card-title>
                                                <v-card-subtitle class="mb-n3">
                                                    入住时间：{{checkInDate}} 下午14：00后 
                                                </v-card-subtitle>
                                                
  
                                            </v-card>
 </div>
</template>
<style>
.os{
    background: "front_end/src/assets/witewall_3.png";
    height: auto;
    /* background-color: rgba(0, 0, 0, 0.4); */
}
</style>
<script>
import {mapGetters,mapMutations,mapActions} from 'vuex'
export default {
  data() {
    return {
          checkInDate: "",
          checkOutDate: "",
          hotelName: "",
          roomNum: 0,
          roomType: "",
          price: 0,
          days: 0,
          phoneNum:"",
          name: "",
          hotelId: 0
        };
  },
    mounted() {
        const temp = this.$route.params.data;
        this.checkInDate = temp.checkInDate;
        this.checkOutDate = temp.checkOutDate;
        this.hotelName = temp.hotelName;
        this.roomNum = temp.roomNum;
        this.price = temp.price;
        
        this.hotelId =  temp.hotelId;
  },
  methods:{
   // 获取酒店对应的图片
    getHotelPicUrl(id){
            return require("../../assets/hotel/" + id + ".jpg")
    }
  }
};
</script>