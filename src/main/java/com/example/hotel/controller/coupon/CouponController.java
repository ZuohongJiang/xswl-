package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelTargetMoney")
    public ResponseVO addHotelTargetMoneyCoupon(@RequestBody HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {
        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(hotelTargetMoneyCouponVO);

        return ResponseVO.buildSuccess(couponVO);
    }
    @PostMapping("/hotelTime")
    public ResponseVO addHotelTimeCoupon(@RequestBody HotelTimeCouponVO hotelTimeCouponVO){
        CouponVO couponVO=couponService.addHotelTimeCoupon(hotelTimeCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }
    @PostMapping("/hotelMultiRoom")
    public ResponseVO addHotelMultiRoomCoupon(@RequestBody HotelMultiRoomCouponVO hotelMultiRoomCouponVO){
        CouponVO couponVO=couponService.addHotelMultiRoomCoupon(hotelMultiRoomCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        ResponseVO test=ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
        return test;
    }
    @GetMapping("/hotelOrderedCoupons")
    public ResponseVO getHotelOrderedCoupons(@RequestParam Integer hotelId) {
        ResponseVO test=ResponseVO.buildSuccess(couponService.getHotelOrderedCoupon(hotelId));
        return test;
    }
    @GetMapping("/{couponid}/annulCoupon")
    public ResponseVO annulCoupon(@PathVariable int couponid){
        return couponService.annulCoupon(couponid);
    }
    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }
//    @GetMapping("/{couponid}/annulCoupon")
//    public ResponseVO annulCoupon(@PathVariable int orderid){
//        return orderService.annulOrder(orderid);
//    }

}
