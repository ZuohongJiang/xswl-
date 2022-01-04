<template>
    <div class="IntOrder_main">
        <section class="m_wp">
            <section class="left_wp Lfll">
                <div class="cont">
                    <h1 class="cont_top Lpl10 Lfz14">
                        预订信息
                    <span class="Ldib subTitle Lfz12 noPpIntro" style="display: none;">
                    </span>
                    </h1>
                    <div id="orderEleInfo" class="cont_mid Lpt20 Lpl20 Lposr">
                        <p class="Lfz12 timeInput Lfwb">
                            <span>入住时间：</span>
                            <span class="checkInTime Lposr">{{checkInDate}}</span>
                            <span class="middlefs">至</span>
                            <span class="leaveTime Lposr">{{checkOutDate}}</span>
                            <span class="middlefs">共<em class="dayCount">{{days}}</em>晚</span>
                        </p>
                        <p class="roomNum Lfwb Lfz12">
                            <span>房间数量：</span>
                            <span class="Lposr roomCounts activetextS"> <em>{{roomNum}} </em>间</span>
                        </p>
                    </div>
                    <h1 class="cont_top Lpl10 Lfz14 Lmt30"> 入住信息</h1>
                    <div class="cont_mid Lpt20 Lpl10 guestCheckIn">
                        <div class="LoginGuest">
                            <p class="roomNum Lfwb Lfz12 repadl">
                                <label class="sWidth Ltar Ldib">
                                    <span class="guestIcon Lfz12 Ldib">*</span>
                                    <span class="Ltar">入住人：</span>
                                </label>
                                <span class="guestCheckInName">
                                    <input id="lastName" placeholder="姓Last name" type="text" class="reWidth reMarginRight firstName">
                                </span>   
                                <span class="guestCheckInLastName">
                                    <input id="firstName" placeholder="名First name" type="text" class="reWidth lastName">
                                </span>
                            </p>
                            <p class="roomNum Lfwb Lfz12 repadl">
                                <label class="sWidth Ltar Ldib">
                                    <span class="guestIcon Lfz12 Ldib">*</span>
                                    <span class="Ltar">手机号码：</span>
                                </label>
                                <span class="guestCheckInTel">
                                    <input id="phoneNum" maxlength="11" type="text" class="reWidth">
                                </span>
                            </p>
                            <p class="roomNum Lfwb Lfz12 repadl">
                                <label class="sWidth Ltar Ldib">
                                    <span class="Ltar">常用邮箱：</span>
                                </label>
                                <span class="guestCheckEmeil">
                                    <input id="email" type="text" class="reWidth">
                                </span>
                                <span class="inputPrompt">订单提交后，我们会将预订信息发送至您的邮箱</span>
                            </p>
                        </div>
                        <p class="useCoupon Lfz12">
                                <a class="Lfwb otherRest">
                                    <span class="Lml30 Lmr5">其他要求</span>
                                    <i class="Ldib"></i>
                                </a>
                        </p>
                        <div class="CouponItem RestCont" style="display: none;">
                            <p class="Lfz12 minorStyle"> 酒店会尽力满足您的需求，祝您入住愉快！</p>
                            <p class="otherReq">
                                <a class="Lfz12 Ldib Ltal first" id="noSmoke">
                                    <!-- 激活状态加上 .selectActive--><i class="select Ldib Lmr5 selectActive"></i>尽量无烟
                                </a><a class="Lfz12 Ldib Ltal" id="yQuiet">
                                    <!-- 激活状态加上 .selectActive--><i class="select Ldib selectActive"></i>尽量安静
                                </a>
                            </p>
                        </div>
                        <p class="tipsInfo Lfz12 minorStyle Ldn">预订过程中可能少量酒店存在价格调整情况，请以成功提交订单时价为准。</p>
                    </div>
                    <div class="cont_mid">
                        <p class="greenServe checkSelect Ldb Lmt20 Lmb20">
                            <input type="checkbox" id="male" class="">
                            <label for="male">同意 </label><span class="saleACancel">《销售及取消政策》 </span>
                        </p>
                    </div>
                    <div class="contCount Ltar Lpt10 Lpb10">
                        <p class="roomPrice Ldb" id="roomPrice"><em>房费</em><span>CNY <b>{{price}} </b></span><i></i></p>

                        <p class="Ldn show" id="otherPrice"><em>税+服务费</em><span class="serverFee">CNY <b>0.00 </b></span><i></i></p>
                    </div>
                    <div class="orderSubmit Lcfx Lcfx Lposr">
                        <div id="checkEveryPrice" class="ckEryDyPriceCont Lposa" style="display: none;">
                            <table><tbody><tr><td><p class="Ldb Lfll Lfz12 Ltac everyPriceColor everyDate" taxamount="0" id="2022/01/04">01月04日(周二)</p><p class="Ldb Lfll Lfz12 Ltac"><em class="everyPriceColor CurrencyCode">CNY</em><i class="everyPriceFz Price">320</i></p></td></tr></tbody></table>
                        </div>
                        <div class="Ltac">
                            <p class="Ltal allCount cleartac">
                                <span class="Lfz14 Lpr5 orderCountPrice Lfwb"> 订单总价</span><em class="Lfz14 orderCountRmb"> <b>CNY</b><i>{{price}}</i></em>
                                <span class="everyDayPricewp"><i class="everyDayPrice Lml40 ">每日房价</i></span>
                            </p>
                            <p class="Ltal allCount "><em class="changeStyle orderCountOther"> <b>  </b><span class="changeStyle"></span></em><i class="blankWidth Ldib"></i></p><a href="javascript:;" class="Lfz16 Ltac Ldb Lmt5 Lmb10 submitOrderBtn"> 提交订单</a>
                        </div>
                    </div>
                </div>
             
            </section>
            <aside class="right_wp Lflr">
                <div class="cont">
                <!-- 酒店图片-->
                <div id="hotelInfo" class="Ldb hotelHref">
                    <!-- 酒店床型-->
                    <div class="asideImg"><img src="https://img.huazhu.com/cos/ppms/39cd1463-fe52-4b86-95d4-6270e78d3847/2021-12-26-22-40-32-673.JPG" style="width: 310px; height: 200px;"></div>
                    <!-- 酒店地址-->
                    <div class="asideTitle">
                        <h2 class="Lfz14 HotelName">{{hotelName}}</h2>
                        <div class="hotelTypeWrap">
                            <span class="hotelType">

                                

                            <em class="hotelStars"></em><em class="hotelStars"></em><em class="hotelStars"></em><em class="hotelStars"></em></span>
                            <span class="infoShow">四星级</span>
                        </div>
                        <p class="Lfz12 HotelAddress">闵行区淮虹路151弄7号T13</p>
                    </div>

                </div>
                <div id="roomInfoModel" class="asideTitle paddingTop15">
                    <h2 class="Lfz16 RoomTypeName paddingBtm4">雅致大床房</h2>
                    
                    <p class="Lfz12 roomDescription">房间描述：<span>智能客房拥有智能音箱语音指令完成所有客房设备控制需求；房间配备大屏液晶电视支持手机投屏，流畅的宽带及无线网络、恒压热带雨林花洒、智能马桶、舒适金可儿零压床垫、全套精油洗护用品。</span></p>
                    
                </div>
                <!-- 酒店公告-->
                <div id="hotleInfoModel" class="asideTitle paddingTop15 clearBorder">
                    <h2 class="Lfz16 paddingBtm4">酒店公告</h2>
                    <div class="cont_item hotelNotices">
                        <ol></ol>
                    </div>
                    <p class="Lfz12 saleAndCancelPolicy ">取消政策：<span>2022-01-04 17:00前支付，18:00前可免费取消；17:00后支付，支付后1小时内也可免费取消；支付后房间将为您保留整晚。</span></p>
                    <p class="Lfz12 GuaranteePolicy  Ldn">担保政策：<span>支付后入住日18:00前可免费取消，18:00后未办理入住，酒店视情况有权取消订单，支付后房间整晚保留。</span></p>
                    <p class="Lfz12 hotelTelphone">酒店电话：<span>021-58819118</span></p>
                    <p class="Lfz12 notice_all">客服电话：4008-121-121</p>
                </div>

            </div>
            </aside>
                
        </section>
    </div>
</template>

<style>
body {
    font-size: 12px;
    font-family: arial,"microsoft yahei",simsun,sans-self;
    overflow-y: scroll;
}
body, div, span, small, p, em, i, b, th, td, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, form, textarea {
    padding: 0;
    margin: 0;
}
body, div, span, small, p, em, i, b, th, td, dl, dt, dd, ul, ol, li, a, h1, h2, h3, h4, h5, h6, form {
    -webkit-tap-highlight-color: rgba(0,0,0,.1);
    -moz-tap-highlight-color: rgba(0,0,0,.1);
    -ms-tap-highlight-color: rgba(0,0,0,.1);
    tap-highlight-color: rgba(0,0,0,.1);
}
a {
    cursor: pointer;
    text-decoration: none;
}
table {
    border-spacing: 0;
    border-collapse: collapse;
}
input, textarea, select, button {
    font-size: 12px;
    font-family: arial,"microsoft yahei",simsun,sans-self;
}
input, button {
    background-image: none;
}
.Porder_main .m_wp .left_wp, .PNotLoginOrder_main .m_wp .left_wp, .IntOrder_main .m_wp .left_wp {
    display: inline;
}
.m_wp{
    width: 990px;
    margin: 0 auto;
    background-color: #f7f6f5;
}
.IntOrder_main .m_wp .left_wp {
    border-color: #e4dfdc;
}
.Porder_main .left_wp, .PNotLoginOrder_main .left_wp, .IntOrder_main .left_wp {
    padding: 17px 33px 0 33px;
    width: 590px;
    height: auto;
    background-color: #fff;
    border-width: 1px;
    border-style: solid;
    border-color: #e4dfdc;
}
.Porder_main .left_wp .cont, .PNotLoginOrder_main .left_wp .cont, .IntOrder_main .left_wp .cont {
    height: auto;
    margin: 0 auto;
}
.Porder_main .m_wp .left_wp .cont_top, .PNotLoginOrder_main .m_wp .left_wp .cont_top, .IntOrder_main .m_wp .left_wp .cont_top {
    height: 37px;
    line-height: 37px;
    color: #651c4d;
    border-bottom: 2px solid #e4dfdc;
}
.Porder_main .m_wp .left_wp .cont_top .subTitle, .PNotLoginOrder_main .m_wp .left_wp .cont_top .subTitle, .IntOrder_main .m_wp .left_wp .cont_top .subTitle {
    font-weight: normal;
    margin-left: 25px;
}
.Porder_main .m_wp .left_wp .cont_mid .timeInput, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .timeInput, .IntOrder_main .m_wp .left_wp .cont_mid .timeInput {
    vertical-align: middle;
    color: #333;
    line-height: 28px;
}
.Porder_main .m_wp .left_wp .cont_mid p, .PNotLoginOrder_main .m_wp .left_wp .cont_mid p, .IntOrder_main .m_wp .left_wp .cont_mid p {
    padding: 7px 0;
}
.Porder_main .m_wp .left_wp .cont_mid p span, .PNotLoginOrder_main .m_wp .left_wp .cont_mid p span, .IntOrder_main .m_wp .left_wp .cont_mid p span {
    height: 24px;
    line-height: 24px;
    display: inline-block;
}
.IntOrder_main .m_wp .left_wp .cont_mid p .checkInTime, .IntOrder_main .m_wp .left_wp .cont_mid p .leaveTime {
    width: 110px;
    text-align: center;
    font-size: 14px;
}
.IntOrder_main .m_wp .left_wp .cont_mid p .middlefs {
    color: gray;
}
.IntOrder_main .m_wp .left_wp .cont_mid p .roomCounts {
    font-size: 14px;
    font-weight: bold;
}
.IntOrder_main .activetextS {
    color: #6a325b;
}
.IntOrder_main .m_wp .left_wp .cont_top {
    border-bottom: 2px solid #e4dfdc;
}
.IntOrder_main .m_wp .left_wp .cont_mid .LoginGuest {
    margin-bottom: 5px;
}
.Porder_main .m_wp .left_wp .guestCheckIn .sWidth, .PNotLoginOrder_main .m_wp .left_wp .guestCheckIn .sWidth, .IntOrder_main .m_wp .left_wp .guestCheckIn .sWidth {
    width: 80px;
}
.Porder_main .m_wp .left_wp .guestCheckIn .guestIcon, .PNotLoginOrder_main .m_wp .left_wp .guestCheckIn .guestIcon, .IntOrder_main .m_wp .left_wp .guestCheckIn .guestIcon {
    color: #ff0f0f;
}
.Porder_main .m_wp .left_wp .cont_mid .roomNum .reWidth, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .roomNum .reWidth, .IntOrder_main .m_wp .left_wp .cont_mid .roomNum .reWidth {
    width: 160px;
}
.IntOrder_main .m_wp .left_wp .cont_mid p .reMarginRight {
    margin-right: 0;
}
.Porder_main .m_wp .left_wp .cont_mid .roomNum input, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .roomNum input, .IntOrder_main .m_wp .left_wp .cont_mid .roomNum input {
    width: 90px;
}
.IntOrder_main .m_wp .left_wp .cont_mid p input {
    border: 1px solid #eeeae9;
}
.Porder_main .m_wp .left_wp .cont_mid p input, .PNotLoginOrder_main .m_wp .left_wp .cont_mid p input, .IntOrder_main .m_wp .left_wp .cont_mid p input {
    width: 184px;
    height: 28px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    border-radius: 5px;
    line-height: 28px;
    margin: 0 10px;
    padding-left: 10px;
    border: 1px solid #eeeae9;
    color: #333;
    font-weight: bold;
}
.Porder_main .m_wp .left_wp .guestCheckIn .inputPrompt, .PNotLoginOrder_main .m_wp .left_wp .guestCheckIn .inputPrompt, .IntOrder_main .m_wp .left_wp .guestCheckIn .inputPrompt {
    color: #999;
    font-weight: normal;
}
.Porder_main .m_wp .left_wp .cont_mid .repadl, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .repadl, .IntOrder_main .m_wp .left_wp .cont_mid .repadl {
    padding-left: 0;
}
.Porder_main .m_wp .left_wp .cont_mid .useCoupon, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .useCoupon, .IntOrder_main .m_wp .left_wp .cont_mid .useCoupon {
    height: 22px;
    line-height: 22px;
    padding: 5px 0;
}
.Porder_main .m_wp .left_wp .cont_mid .useCoupon a, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .useCoupon a, .IntOrder_main .m_wp .left_wp .cont_mid .useCoupon a {
    color: #651c4d;
    margin-left: 60px;
}
.Porder_main .m_wp .left_wp .cont_mid .useCoupon a span, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .useCoupon a span, .IntOrder_main .m_wp .left_wp .cont_mid .useCoupon a span {
    height: 22px;
    border-bottom: 1px dashed #612551;
    color: #651c4d;
}
.Porder_main .m_wp .left_wp .cont_mid .useCoupon a .useCouponActive, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .useCoupon a .useCouponActive, .IntOrder_main .m_wp .left_wp .cont_mid .useCoupon a .useCouponActive {
    background-position: -17px -70px;
}
.Porder_main .m_wp .left_wp .cont_mid .useCoupon a i, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .useCoupon a i, .IntOrder_main .m_wp .left_wp .cont_mid .useCoupon a i {
    width: 10px;
    height: 10px;
    background-image: url(/icon.png);
    cursor: pointer;
    background-position: -17px -62px;
    vertical-align: middle;
}
.Porder_main .m_wp .left_wp .cont_mid .CouponItem, .PNotLoginOrder_main .m_wp .left_wp .cont_mid .CouponItem, .IntOrder_main .m_wp .left_wp .cont_mid .CouponItem {
    padding: 15px 80px;
    margin-top: 2px;
    margin-bottom: 2px;
    background: #f7f6f5;
}
.IntOrder_main .m_wp .left_wp .cont_mid .tipsInfo {
    color: #999;
    text-align: left;
    padding: 15px 0 15px 90px;
    margin-top: 50px;
}
.IntOrder_main .m_wp .left_wp .cont_mid .checkSelect input {
    width: 12px;
    height: 12px;
    vertical-align: -2px;
    margin-right: 2px;
    margin-left: 20px;
    border: 0 solid #fff;
}
.IntOrder_main .m_wp .left_wp .cont_mid .checkSelect label {
    cursor: pointer;
    color: #666;
}
.IntOrder_main .m_wp .left_wp .cont_mid .checkSelect .saleACancel {
    cursor: pointer;
    color: #651c4d;
}
.IntOrder_main .m_wp .left_wp .contCount {
    border-top: 1px dashed #e4dfdc;
    border-bottom: 1px dashed #e4dfdc;
}
.Porder_main .m_wp .left_wp .contCount, .PNotLoginOrder_main .m_wp .left_wp .contCount, .IntOrder_main .m_wp .left_wp .contCount {
    border-bottom: 1px dashed #e4dfdc;
}
.Porder_main .m_wp .left_wp .contCount p, .PNotLoginOrder_main .m_wp .left_wp .contCount p, .IntOrder_main .m_wp .left_wp .contCount p {
    padding: 2px 0;
}
.Porder_main .m_wp .left_wp .contCount p em, .PNotLoginOrder_main .m_wp .left_wp .contCount p em, .IntOrder_main .m_wp .left_wp .contCount p em {
    text-align: right;
    width: 290px;
    display: inline-block;
    color: #999;
}
.IntOrder_main .m_wp .left_wp .contCount p span {
    min-width: 120px;
    font-size: 14px;
    text-align: left;
    color: #333;
    padding-left: 35px;
}
.IntOrder_main .m_wp .left_wp .contCount p span b {
    font-style: normal;
    font-weight: normal;
    color: #333;
    padding-left: 5px;
}
.IntOrder_main .m_wp .left_wp .contCount p .serverFee {
    min-width: 120px;
}
.IntOrder_main .m_wp .left_wp .contCount p .serverFee b {
    padding-left: 5px;
}
.Porder_main .m_wp .left_wp .orderSubmit, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit, .IntOrder_main .m_wp .left_wp .orderSubmit {
    padding: 13px 0 13px 13px;
}
.IntOrder_main .ckEryDyPriceCont {
    max-width: 495px;
    display: none;
    z-index: 9999;
    top: 40px;
    right: -4px;
}
.IntOrder_main .ckEryDyPriceCont table {
    margin-bottom: 2px;
}
.Porder_main .m_wp .left_wp .orderSubmit .everyPriceColor, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit .everyPriceColor, .IntOrder_main .m_wp .left_wp .orderSubmit .everyPriceColor {
    color: #333;
}
.Porder_main .m_wp .left_wp .orderSubmit p em, .Porder_main .m_wp .left_wp .orderSubmit p i, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit p em, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit p i, .IntOrder_main .m_wp .left_wp .orderSubmit p em, .IntOrder_main .m_wp .left_wp .orderSubmit p i {
    color: #f60;
}
.Porder_main .m_wp .left_wp .orderSubmit p, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit p, .IntOrder_main .m_wp .left_wp .orderSubmit p {
    padding-bottom: 2px;
}

.IntOrder_main .ckEryDyPriceCont table td p {
    padding: 0;
    font-weight: bold;
    width: 100%;
    max-width: 122px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .cleartac {
    text-align: initial;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount {
    text-align: right;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount em {
    min-width: 140px;
    text-align: left;
    display: inline-block;
    padding-left: 35px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount em b {
    font-style: normal;
    width: 42px;
    display: inline-block;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount em i {
    color: #f60;
    font-size: 23px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .everyDayPrice {
    color: #702357;
    font-size: 12px;
    display: inline-block;
    border-bottom: 1px dashed #702357;
    margin-left: 0;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .orderCountOther {
    width: 140px;
    clear: both;
    zoom: 1;
    overflow: hidden;
    margin-right: 26px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .changeStyle {
    color: #f93;
    font-size: 12px;
    padding: 3px 0;
    text-align: left;
    margin-right: 65px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .orderCountOther .changeStyle {
    width: 90px;
    float: left;
    display: inline-block;
    margin: 0;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .orderCountOther b {
    float: left;
    padding: 5px 0 3px 0;
    vertical-align: middle;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount em b {
    font-style: normal;
    width: 42px;
    display: inline-block;
}
.IntOrder_main .m_wp .left_wp .orderSubmit .allCount .blankWidth {
    width: 50px;
}
.Porder_main .m_wp .left_wp .orderSubmit p i, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit p i, .IntOrder_main .m_wp .left_wp .orderSubmit p i {
    font-size: 23px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit a.submitOrderBtn {
    margin-right: 28px;
}
.IntOrder_main .m_wp .left_wp .orderSubmit a {
    background: #ae1363;
    margin-right: 40px;
}
.Porder_main .m_wp .left_wp .orderSubmit a, .PNotLoginOrder_main .m_wp .left_wp .orderSubmit a, .IntOrder_main .m_wp .left_wp .orderSubmit a {
    background: #ae1363;
    width: 162px;
    height: 40px;
    line-height: 40px;
    display: block;
    color: #fff;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    -ms-border-radius: 5px;
    border-radius: 5px;
    float: right;
}

/* right page css */
.Porder_main .m_wp .right_wp, .PNotLoginOrder_main .m_wp .right_wp, .IntOrder_main .m_wp .right_wp {
    display: inline;
}
.Porder_main .right_wp, .PNotLoginOrder_main .right_wp, .IntOrder_main .right_wp {
    padding: 15px;
    background: #f7f6f5;
    width: 300px;
}
.Porder_main .right_wp .cont, .PNotLoginOrder_main .right_wp .cont, .IntOrder_main .right_wp .cont {

    height: auto;
    margin: 0 auto;
}
.IntOrder_main .m_wp .right_wp .asideImg {
    padding-bottom: 10px;
}
.Porder_main .m_wp .right_wp .asideImg, .PNotLoginOrder_main .m_wp .right_wp .asideImg, .IntOrder_main .m_wp .right_wp .asideImg {
    height: 200px;
    padding-bottom: 4px;
}
.Porder_main .m_wp .right_wp .asideTitle, .PNotLoginOrder_main .m_wp .right_wp .asideTitle, .IntOrder_main .m_wp .right_wp .asideTitle {
    padding: 0 0 15px 0;
    color: #666;
    border-bottom: 1px solid #e4dfdc;
}
.IntOrder_main .m_wp .right_wp .hotelTypeWrap {
    padding: 10px 0;
}
.IntOrder_main .m_wp .right_wp .hotelType {
    padding-right: 6px;
}
.IntOrder_main .m_wp .right_wp .hotelStars {
    display: inline-block;
    width: 10px;
    height: 10px;


    vertical-align: middle;
    margin-left: 3px;
    margin-right: 3px;
}
.IntOrder_main .m_wp .right_wp .infoShow {
    font-size: 12px;
    color: #666;
}
.Porder_main .m_wp .right_wp .asideTitle p, .PNotLoginOrder_main .m_wp .right_wp .asideTitle p, .IntOrder_main .m_wp .right_wp .asideTitle p {
    padding: 2px 0;
}
.IntOrder_main .m_wp .right_wp .paddingTop15 {
    padding-top: 15px;
}
.IntOrder_main .m_wp .right_wp .clearBorder {
    border-bottom: 0 solid #f7f6f5;
}
.IntOrder_main .m_wp .right_wp .paddingBtm4 {
    padding-bottom: 4px;
}
 /* 样式 */
 .Ldib {
    display: inline-block;
}
 .Lfwb {
    font-weight: bold;
}
 .Lposr {
    position: relative;
}
.Lposa {
    position: absolute;
}
.Lfll {
    float: left;
}
.Lflr {
    float: right;
}
.Ltar {
    text-align: right;
}
.Ltac {
    text-align: center;
}
.Lpl10 {
    padding-left: 10px;
}
.Lpl20 {
    padding-left: 20px;
}
.Lpt10 {
    padding-top: 10px;
}
.Lpt20 {
    padding-top: 20px;
}
.Lpb10 {
    padding-bottom: 10px;
}
.Lfz12 {
    font-size: 12px;
}
.Lfz14 {
    font-size: 14px;
}
.Lfz16 {
    font-size: 16px;
}
.Lmt5 {
    margin-top: 5px;
}
.Lmt30 {
    margin-top: 30px;
}
.Lmb10 {
    margin-bottom: 10px;
}
.Lml30 {
    margin-left: 30px;
}
.Lmr5 {
    margin-right: 5px;
}
</style>
<script>

import {mapGetters,mapMutations,mapActions} from 'vuex'


export default {
  name: "orderConfirm",
  data(){
      return{
          checkInDate: "",
          checkOutDate: "",
          hotelName: "",
          roomNum: 0,
          roomType: "",
          price: 0,
          days: 0,
          phoneNum:"",
          firstName:"",
          lastName:""
      }
  },
  computed:{
      ...mapGetters(
          [
              'orderDetail'
          ]
      )
  },
  methods:{
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
        console.log(this.$route.params.data)
        const temp = this.$route.params.data;
        this.checkInDate = temp.checkInDate;
        this.checkOutDate = temp.checkOutDate;
        this.hotelName = temp.hotelName;
        this.roomNum = temp.roomNum;
        this.price = temp.price;
        this.days = this.getDays(this.checkInDate, this.checkOutDate);
    }
}
</script>
