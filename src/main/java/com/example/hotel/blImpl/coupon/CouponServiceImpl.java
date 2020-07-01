package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.po.Order;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final TimeCouponStrategyImpl timeCouponStrategy;
    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
        strategyList.add(new MultiRoomCouponStrategyImpl());
    }


    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {


        if (orderVO == null) return null;
        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }
        //冲突优惠券实现，若满减和多间优惠同时可用，则只能用多间优惠。
        boolean hasMultiRoom=false;
        for(int i=0;i<availAbleCoupons.size();i++){
            if(availAbleCoupons.get(i).getCouponType()==2){
                hasMultiRoom=true;
                break;
            }
        }
        if(hasMultiRoom==true){
            for(int i=0;i<availAbleCoupons.size();i++){
                if(availAbleCoupons.get(i).getCouponType()==3){
                    availAbleCoupons.remove(i);
                    i=0;
                    continue;
                }
            }
        }
        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
//        for(int j=0;j<hotelCoupons.size();j++)
////        for(int i=0;i<hotelCoupons.size()-1;i++){
////            if(hotelCoupons.get(i).getCouponType()<hotelCoupons.get(i-1).getCouponType()){
////                Coupon temp=hotelCoupons.get(i);
////                hotelCoupons.set(i,hotelCoupons.get(i+1));
////                hotelCoupons.set(i+1,temp);
////            }
////        }
//        List <Coupon> res=new ArrayList<>();
//        for(int i=0;i<hotelCoupons.size();i++){
//            if(hotelCoupons.get(i).getCouponType()==2){
//                res.add(hotelCoupons.get(i));
//            }
//        }
//        for(int i=0;i<hotelCoupons.size();i++){
//            if(hotelCoupons.get(i).getCouponType()==3){
//                res.add(hotelCoupons.get(i));
//            }
//        }
//        for(int i=0;i<hotelCoupons.size();i++){
//            if(hotelCoupons.get(i).getCouponType()==4){
//                res.add(hotelCoupons.get(i));
//            }
//        }
        return hotelCoupons;
    }
    public List<Coupon> getHotelOrderedCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
//        for(int j=0;j<hotelCoupons.size();j++)
////        for(int i=0;i<hotelCoupons.size()-1;i++){
////            if(hotelCoupons.get(i).getCouponType()<hotelCoupons.get(i-1).getCouponType()){
////                Coupon temp=hotelCoupons.get(i);
////                hotelCoupons.set(i,hotelCoupons.get(i+1));
////                hotelCoupons.set(i+1,temp);
////            }
////        }
        List <Coupon> res=new ArrayList<>();
        for(int i=0;i<hotelCoupons.size();i++){
            if(hotelCoupons.get(i).getCouponType()==2){
                res.add(hotelCoupons.get(i));
            }
        }
        for(int i=0;i<hotelCoupons.size();i++){
            if(hotelCoupons.get(i).getCouponType()==3){
                res.add(hotelCoupons.get(i));
            }
        }
        for(int i=0;i<hotelCoupons.size();i++){
            if(hotelCoupons.get(i).getCouponType()==4){
                res.add(hotelCoupons.get(i));
            }
        }
        return res;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setTarget_roomNum(couponVO.getTarget_roomNum());
        coupon.setCouponType(couponVO.getType());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        LocalDate couponStartDate = LocalDate.parse(couponVO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), couponEndDate = LocalDate.parse(couponVO.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime couponStartDateTime = couponStartDate.atStartOfDay(), couponEndDateTime = couponEndDate.atStartOfDay();
        coupon.setStartTime(couponStartDateTime);
        coupon.setEndTime(couponEndDateTime);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTimeCoupon(HotelTimeCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setTarget_roomNum(couponVO.getTarget_roomNum());
        coupon.setCouponType(couponVO.getType());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        LocalDate couponStartDate = LocalDate.parse(couponVO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), couponEndDate = LocalDate.parse(couponVO.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime couponStartDateTime = couponStartDate.atStartOfDay(), couponEndDateTime = couponEndDate.atStartOfDay();
        coupon.setStartTime(couponStartDateTime);
        coupon.setEndTime(couponEndDateTime);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        //6/11
        //coupon.setStartTime(couponVO.getStartTime());
        //coupon.setEndTime(couponVO.getEndTime());
        return couponVO;
    }

    @Override
    public CouponVO addHotelMultiRoomCoupon(HotelMultiRoomCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setTarget_roomNum(couponVO.getTarget_roomNum());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setDiscount(couponVO.getDiscount());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        LocalDate couponStartDate = LocalDate.parse(couponVO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), couponEndDate = LocalDate.parse(couponVO.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDateTime couponStartDateTime = couponStartDate.atStartOfDay(), couponEndDateTime = couponEndDate.atStartOfDay();
        coupon.setStartTime(couponStartDateTime);
        coupon.setEndTime(couponEndDateTime);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    /*
    删除优惠券
     */
    public ResponseVO annulCoupon(Integer id){
        Coupon coupon = couponMapper.selectByCouponId(id);
        couponMapper.annulCoupon(id);
        couponMapper.deleteCancelled();
        //hotelService.updateRoomInfo(order.getHotelId(), order.getRoomType(), (-order.getRoomNum()));
        return ResponseVO.buildSuccess(true);
    }

}

