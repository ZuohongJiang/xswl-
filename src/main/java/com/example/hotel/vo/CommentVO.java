package com.example.hotel.vo;

public class CommentVO {
    private Integer id;
    private Integer userId;
    private Integer hotelId;
    private Integer orderId;
    private String createDate;
    private String commentContent;
    private Double rate;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public Integer getOrderId(){return orderId;}

    public String getCreateDate() {
        return createDate;
    }

    public String getcommentContent() {
        return commentContent;
    }

    public Double getRate(){
        return rate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setOrderId(Integer orderId){this.orderId = orderId;}

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setcommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
