package com.example.hotel.junits;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.HotelMultiRoomCouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.HotelTimeCouponVO;
import com.example.hotel.vo.OrderVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hotel
 * @description: 测试优惠券相关功能
 * @author: Li Yongshao
 * @create: 2020-06-28 14:35
 */
@Transactional
public class CouponTests extends HotelApplicationTests{
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponMapper couponMapper;

    /**
     * @Description: 测试获取酒店所有优惠券功能
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    public void testGetAllHotelCoupon(){
        List<Coupon> coupons=couponService.getHotelAllCoupon(1);
        Assert.assertSame("数量错误",3,coupons.size());
        Assert.assertEquals("描述错误","满500-100优惠",coupons.get(0).getDescription());
    }
    /**
     * @Description: 测试优惠券排序功能
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    public void testGetHotelCouponOrdered(){
        List<Coupon> coupons=couponService.getHotelAllCoupon(1);
        List<Coupon> orderedCoupons=couponService.getHotelOrderedCoupon(1);
        int ptr=0;
        for (Coupon coupon:orderedCoupons
             ) {
            if(ptr==0||ptr<=coupon.getCouponType())
                ptr=coupon.getCouponType();
            else if(ptr>coupon.getCouponType())
                Assert.fail("排序错误");
            Assert.assertTrue(coupons.indexOf(coupon)>=0);
        }
    }
    /**
     * @Description: 测试获取匹配优惠券功能
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    public void testGetHotelMatchedCoupon(){
        OrderVO o1=new OrderVO();
        o1.setUserId(6);
        o1.setHotelId(3);
        o1.setPrice(333.0);
        o1.setRoomNum(3);
        o1.setCheckInDate("2020-06-15");
        o1.setCheckOutDate("2020-06-15");
        List<Coupon> os=couponService.getMatchOrderCoupon(o1);
        Assert.assertSame("数量错误",1,os.size());
        Assert.assertSame("ID错误",36,os.get(0).getId());

        OrderVO o2=new OrderVO();
        BeanUtils.copyProperties(o1,o2);
        o2.setHotelId(1);
        o2.setPrice(500.0);
        os=couponService.getMatchOrderCoupon(o2);
        Assert.assertSame("数量错误",3,os.size());
        Assert.assertSame("ID错误",2,os.get(0).getId());
    }
    /**
     * @Description: 测试删除优惠券
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    @Rollback
    public void testAnnulCoupon(){
        couponService.annulCoupon(2);
        Assert.assertNull(couponMapper.selectByCouponId(2));
    }
    /**
     * @Description: 测试增加优惠券
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    @Rollback
    public void testAddCoupon(){
        List<Coupon> os=couponService.getHotelAllCoupon(1);
        int before=os.size();
        HotelTargetMoneyCouponVO o1=new HotelTargetMoneyCouponVO();
        o1.setDescription("test");
        o1.setHotelId(1);
        o1.setType(3);
        o1.setName("name");
        o1.setTargetMoney(500);
        o1.setDiscount(0);
        o1.setStartTime("2020-04-22");
        o1.setEndTime("2021-01-24");
        o1.setDiscountMoney(101);
        couponService.addHotelTargetMoneyCoupon(o1);
        os=couponService.getHotelAllCoupon(1);
        Assert.assertSame("数量错误",before+1,os.size());
        Assert.assertSame("类型错误",3,os.get(os.size()-1).getCouponType());

        before=os.size();
        HotelTimeCouponVO o2=new HotelTimeCouponVO();
        BeanUtils.copyProperties(o1,o2);
        o2.setType(4);
        couponService.addHotelTimeCoupon(o2);
        os=couponService.getHotelAllCoupon(1);
        Assert.assertSame("数量错误",before+1,os.size());
        Assert.assertSame("类型错误",4,os.get(os.size()-1).getCouponType());

        before=os.size();
        HotelMultiRoomCouponVO o3=new HotelMultiRoomCouponVO();
        BeanUtils.copyProperties(o1,o3);
        o3.setType(2);
        o3.setTarget_roomNum(3);
        couponService.addHotelMultiRoomCoupon(o3);
        os=couponService.getHotelAllCoupon(1);
        Assert.assertSame("数量错误",before+1,os.size());
        Assert.assertSame("类型错误",2,os.get(os.size()-1).getCouponType());
    }
}