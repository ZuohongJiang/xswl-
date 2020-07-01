package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     *
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if(coupon.getCouponType()!=4)
            return false;
        if (orderVO.getCheckInDate() == null || orderVO.getCheckOutDate() == null)
            return false;
        LocalDate orderCheckInDate = LocalDate.parse(orderVO.getCheckInDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                , orderCheckOutDate = LocalDate.parse(orderVO.getCheckOutDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime orderCheckInDateTime = orderCheckInDate.atStartOfDay(), orderCheckOutDateTime = orderCheckOutDate.atStartOfDay();
        return orderCheckInDateTime.isAfter(coupon.getStartTime()) && orderCheckOutDateTime.isBefore(coupon.getEndTime());
    }
}
