package com.example.hotel.vo;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
public class HotelTimeCouponVO extends CouponVO {
    private Integer hotelId;
    private String startTime;
    private String endTime;
    private Integer discountMoney;
    private Integer target_roomNum;
    private double discount;
    public Integer getHotelId() {
        return hotelId;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
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
