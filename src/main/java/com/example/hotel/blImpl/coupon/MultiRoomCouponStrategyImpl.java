package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MultiRoomCouponStrategyImpl implements CouponMatchStrategy {
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        //先判断空指针，再判断可用时间
        if (orderVO.getRoomNum() == null) return false;
        LocalDate orderCheckInDate = LocalDate.parse(orderVO.getCheckInDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                , orderCheckOutDate = LocalDate.parse(orderVO.getCheckOutDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime orderCheckInDateTime = orderCheckInDate.atStartOfDay(), orderCheckOutDateTime = orderCheckOutDate.atStartOfDay();
        return coupon.getCouponType() == 2 && orderVO.getRoomNum() >= coupon.getTarget_roomNum()&&orderCheckInDateTime.isAfter(coupon.getStartTime()) && orderCheckOutDateTime.isBefore(coupon.getEndTime());
    }
}
