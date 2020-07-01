package com.example.hotel.vo;

import java.time.LocalDateTime;

public class HotelMultiRoomCouponVO extends CouponVO {
    private Integer hotelId;
    private Integer discountMoney;
    private String startTime;
    private Integer target_roomNum;
    private double discount;
    /**
     * 失效时间
     */
    private String endTime;

    /**
     * 优惠券状态 是否已经失效 1可用 0失效
     */
    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getHotelId() {
        return hotelId;
    }
    public Integer getTarget_roomNum() {
        return target_roomNum;
    }

    public void setTarget_roomNum(Integer target_roomNum) {
        this.target_roomNum = target_roomNum;
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
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
