package com.example.hotel.vo;
import java.time.LocalDateTime;
public class CouponVO {
    private Integer id;
    private String description;
    private Integer status;
    private String name;
    private Integer type;
    private Integer target_roomNum;
    private double discount;
//    private String startTime;
//    /**
//     * 失效时间
//     */
//    private String endTime;
//
//    /**
//     * 优惠券状态 是否已经失效 1可用 0失效
//     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    //    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
}
