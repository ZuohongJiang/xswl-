package com.example.hotel.enums;

public enum BizRegion {
    XiDan("西单"),
    XinJieKou("新街口"),
    FuZiMiao("夫子庙"),
    AoTiZhongXin("奥体中心"),
    JiangNingWanDa("江宁万达"),
    XueZeLu("学则路"),
    XianLin("仙林大学城"),
    LuKouAirport("禄口机场");

    private String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
