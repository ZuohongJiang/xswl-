package com.example.hotel.junits;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: hotel
 * @description: 测试房间相关功能
 * @author: Li Yongshao
 * @create: 2020-06-27 21:16
 */
@Transactional
public class HotelInfoTests extends HotelApplicationTests {
    private List<HotelVO> testList = new ArrayList<>();
    @Autowired
    private HotelService hotelService;

    private void setTestList() {
        if (testList.size() != 0) return;
        testList.add(createHotelVO(1, "汉庭酒店", "欢迎您入住", null, "XiDan", "Four", "1829373819", 4.8, 1));
        testList.add(createHotelVO(2, "儒家酒店", "欢迎您入住", "南京市鼓楼区珠江路268号", "XiDan", "Four", "1829373819", 4.8, 2));
        testList.add(createHotelVO(3, "桂圆酒店", "欢迎您入住", "南京市栖霞区珠江路268号", "XiDan", "Four", "1829553719", 4.8, 6));
    }


    /**
     * @Description: 测试获取酒店列表功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testGetHotelList() {
        List<HotelVO> hotelVOList = hotelService.retrieveHotels();
        Assert.assertSame("酒店数量有误", 3, hotelVOList.size());
        setTestList();
        for (int i = 0; i < 3; i++) {
            compareHotelVO(testList.get(i), hotelVOList.get(i));
        }
    }

    /**
     * @Description: 测试获取酒店信息功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testGHotelDetail() {
        setTestList();
        for (int i = 0; i < 3; i++) {
            HotelVO hotelVO = testList.get(i);
            compareHotelVO(hotelVO, hotelService.retrieveHotelDetails(hotelVO.getId()));
        }
    }

    /**
     * @Description: 测试增加酒店功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testAddHotel() {
        setTestList();
        HotelVO test1 = createHotelVO(4, "测试名", "测试描述", "测试地址", "XiDan", "Three", "999", 3.3, 6);
        HotelVO test2 = createHotelVO(5, "测试名", "测试描述", "测试地址", "XiDan", "Three", "999", 3.3, 1);
        testList.add(test1);
        try {
            hotelService.addHotel(test1);
            compareHotelVO(test1, hotelService.retrieveHotelDetails(test1.getId()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            hotelService.addHotel(test2);
            Assert.fail("错误：未抛出异常");
        } catch (ServiceException e) {
            Assert.assertEquals("异常信息错误", "管理员不存在或者无权限！创建酒店失败！", e.getMessage());
        }
    }

    /**
     * @Description: 测试编辑酒店功能
     * @Author: Li Yongshao
     * @date: 2020/6/29
     */
    @Test
    @Rollback
    public void testEditHotel() {
        HotelVO test1 = createHotelVO(3, "测试名", "测试描述", "测试地址", "FuZiMiao", "Three", "999", 4.8, 6);
        HotelVO test2 = createHotelVO(5, "测试名", "测试描述", "测试地址", "XiDan", "Three", "999", 3.3, 1);
        try {
            hotelService.updateHotelInfo(test1);
            compareHotelVO(test1, hotelService.retrieveHotelDetails(test1.getId()));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
        try {
            hotelService.updateHotelInfo(test2);
            Assert.fail("错误：未抛出异常");
        } catch (ServiceException e) {
            Assert.assertEquals("异常信息错误", "管理员不存在或者无权限！更新酒店信息失败！", e.getMessage());
        }
    }
    /**
     * @Description: 测试删除酒店功能
     * @Author: Li Yongshao
     * @date: 2020/6/29
     */
    @Test
    @Rollback
    public void testDeleteHotel(){
        List<HotelVO> hotels=hotelService.retrieveHotels();
        int before=hotels.size();
        hotelService.deleteHotel(1);
        hotels=hotelService.retrieveHotels();
        Assert.assertSame("数量错误",before-1,hotels.size());
    }

    /**
     * @Description: 辅助类，判断除rooms外的HotelVO
     * @Params: 预期，实际
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    private void compareHotelVO(HotelVO expectedHotelVO, HotelVO getHotelVO) {
        Assert.assertSame("酒店ID错误", expectedHotelVO.getId(), getHotelVO.getId());
        Assert.assertEquals("酒店名称错误", expectedHotelVO.getName(), getHotelVO.getName());
        Assert.assertEquals("酒店描述错误", expectedHotelVO.getDescription(), getHotelVO.getDescription());
        Assert.assertEquals("酒店地址错误", expectedHotelVO.getAddress(), getHotelVO.getAddress());
        Assert.assertEquals("酒店商圈错误", expectedHotelVO.getBizRegion(), getHotelVO.getBizRegion());
        Assert.assertEquals("酒店星级错误", expectedHotelVO.getHotelStar(), getHotelVO.getHotelStar());
        Assert.assertEquals("酒店号码错误", expectedHotelVO.getPhoneNum(), getHotelVO.getPhoneNum());
        Assert.assertEquals("酒店评分有误", expectedHotelVO.getRate(), getHotelVO.getRate(), 1e-6);
        Assert.assertSame("酒店管理员ID有误", expectedHotelVO.getManagerId(), getHotelVO.getManagerId());
    }

    private HotelVO createHotelVO(int a, String b, String c, String d, String e, String f, String g, double h, int i) {
        HotelVO ret = new HotelVO();
        ret.setId(a);
        ret.setName(b);
        ret.setDescription(c);
        ret.setAddress(d);
        ret.setBizRegion(e);
        ret.setHotelStar(f);
        ret.setPhoneNum(g);
        ret.setRate(h);
        ret.setManagerId(i);
        return ret;
    }
}
