package com.example.hotel.vo;

import java.time.LocalDateTime;

/**
 * 酒店满减金额优惠 eg 满300减100
 */
public class HotelTargetMoneyCouponVO extends CouponVO {
    private String startTime;
    /**
     * 失效时间
     */
    private String endTime;
    private Integer target_roomNum;
    private double discount;

    /**
     * 优惠券状态 是否已经失效 1可用 0失效
     */
    private Integer hotelId;

    private Integer targetMoney;

    private Integer discountMoney;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getTarget_roomNum() {
        return target_roomNum;
    }

    public void setTarget_roomNum(Integer target_roomNum) {
        this.target_roomNum = target_roomNum;
    }
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
