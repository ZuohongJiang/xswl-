package mockMvc;

import com.alibaba.fastjson.JSONObject;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: hotel
 * @description: MockMvc的测试类
 * @author: Li Yongshao
 * @create: 2020-06-29 23:43
 */
@Rollback
public class MockMvcTests extends AbstractBaseTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    RoomService roomService;

    private MockMvc mvc;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * @Description: 具体测试方法
     * @Author: Li Yongshao
     * @date: 2020/6/30
     */
    @Test
    public void test1() throws Exception {
        UserForm userForm = new UserForm();
        userForm.setEmail("333@qq.com");
        userForm.setPassword("123456");
        testPOST("/api/user/login", userForm);
    }

    @Test
    public void test2() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserName("nickName");
        userVO.setEmail("testtest@qq.com");
        userVO.setPassword("Password");
        userVO.setUserType(UserType.Client);
        userVO.setCredit(99);
        userVO.setPhoneNumber("999");
        testPOST("/api/user/register", userVO);
    }

    @Test
    public void test3() throws Exception {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserName("测试名");
        userInfoVO.setPhoneNumber("99912345678");
        testPOST("/api/user/6/userInfo/update", userInfoVO);
    }

    @Test
    public void test4() throws Exception {
        UserPasswordInfoVO userPasswordInfoVO = new UserPasswordInfoVO();
        userPasswordInfoVO.setOldPassword("123456");
        userPasswordInfoVO.setPassword("654321");
        testPOST("/api/user/6/userPassword/update", userPasswordInfoVO);
    }

    @Test
    public void test5() throws Exception {
        testGET("/api/user/6/getUserInfo", null);
    }

    @Test
    public void test6() throws Exception {
        HotelVO hotelVO = new HotelVO();
        hotelVO.setId(4);
        hotelVO.setName("测试名");
        hotelVO.setDescription("测试描述");
        hotelVO.setAddress("测试地址");
        hotelVO.setBizRegion("XiDan");
        hotelVO.setHotelStar("Three");
        hotelVO.setPhoneNum("999");
        hotelVO.setRate(3.3);
        hotelVO.setManagerId(6);
        testPOST("/api/hotel/addHotel", hotelVO);
    }

    @Test
    public void test7() throws Exception {
        HotelVO hotelVO = new HotelVO();
        hotelVO.setId(4);
        hotelVO.setName("测试名");
        hotelVO.setDescription("测试描述");
        hotelVO.setAddress("测试地址");
        hotelVO.setBizRegion("FuZiMiao");
        hotelVO.setHotelStar("Three");
        hotelVO.setPhoneNum("999");
        hotelVO.setRate(3.3);
        hotelVO.setManagerId(6);
        testPOST("/api/hotel/updateHotelInfo", hotelVO);
    }

    @Test
    public void test8() throws Exception {
        testGET("/api/hotel/all", null);
    }

    @Test
    public void test9() throws Exception {
        HotelRoom addHotelRoom = new HotelRoom();
        addHotelRoom.setHotelId(1);
        addHotelRoom.setTotal(13);
        addHotelRoom.setCurNum(13);
        addHotelRoom.setPrice(233);
        addHotelRoom.setRoomType("大床房");
        addHotelRoom.setDetail("test");
        testPOST("/api/hotel/roomInfo", addHotelRoom);
    }

    @Test
    public void test10() throws Exception {
        testGET("/api/hotel/6/getHotelsByManagerId", null);
    }

    @Test
    public void test11() throws Exception {
        testGET("/api/hotel/1/detail", null);
    }

    @Test
    public void test12() throws Exception {
        HotelRoom editHotelRoom = new HotelRoom();
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        BeanUtils.copyProperties(hotelRooms.get(0), editHotelRoom);
        editHotelRoom.setDetail("test");
        editHotelRoom.setTotal(editHotelRoom.getTotal() + 3);
        testPOST("/api/hotel/editRoom", editHotelRoom);
    }

    @Test
    public void test13() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("roomId", "2");
        testGET("/api/hotel/deleteRoom", params);
    }

    @Test
    public void test14() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("hotelId", "2");
        testGET("/api/hotel/deleteHotel", params);
    }

    @Test
    public void test15() throws Exception {
        OrderVO orderVO1 = new OrderVO();
        orderVO1.setHotelId(2);
        orderVO1.setCheckInDate("2020-06-20");
        orderVO1.setCheckOutDate("2020-06-23");
        orderVO1.setUserId(5);
        orderVO1.setHaveChild(true);
        orderVO1.setPeopleNum(4);
        orderVO1.setRoomType("Family");
        orderVO1.setRoomNum(5);
        orderVO1.setRoomId(6);
        orderVO1.setPrice(999.9);
        orderVO1.setHotelName("儒家酒店");
        testPOST("/api/order/addOrder", orderVO1);
    }

    @Test
    public void test16() throws Exception {
        testGET("/api/order/getAllOrders", null);
    }

    @Test
    public void test17() throws Exception {
        testGET("/api/order/6/getUserOrders", null);
    }

    @Test
    public void test18() throws Exception {
        testGET("/api/order/21/annulOrder", null);
    }

    @Test
    public void test19() throws Exception {
        testGET("/api/order/1/allOrders", null);
    }

    @Test
    public void test20() throws Exception {
        testGET("/api/order/19/deleteOrder", null);
    }

    @Test
    public void test21() throws Exception {
        testGET("/api/order/1/6/getUserThisHotelOrders", null);
    }

    @Test
    public void test22() throws Exception {
        testGET("/api/order/21/executeOrder", null);
    }

    @Test
    public void test23() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("hotelIdList", "1");
        params.add("hotelIdList", "6");
        testGET("/api/order/getManageHotelsOrders", params);
    }

    @Test
    public void test24() throws Exception {
        HotelTargetMoneyCouponVO o1 = new HotelTargetMoneyCouponVO();
        o1.setDescription("test");
        o1.setHotelId(1);
        o1.setType(3);
        o1.setName("name");
        o1.setTargetMoney(500);
        o1.setDiscount(0);
        o1.setStartTime("2020-04-22");
        o1.setEndTime("2021-01-24");
        o1.setDiscountMoney(101);
        testPOST("/api/coupon/hotelTargetMoney", o1);
    }

    @Test
    public void test25() throws Exception {
        HotelTargetMoneyCouponVO o1 = new HotelTargetMoneyCouponVO();
        o1.setDescription("test");
        o1.setHotelId(1);
        o1.setType(4);
        o1.setName("name");
        o1.setTargetMoney(500);
        o1.setDiscount(0);
        o1.setStartTime("2020-04-22");
        o1.setEndTime("2021-01-24");
        o1.setDiscountMoney(101);
        testPOST("/api/coupon/hotelTime", o1);
    }

    @Test
    public void test26() throws Exception {
        HotelTargetMoneyCouponVO o1 = new HotelTargetMoneyCouponVO();
        o1.setDescription("test");
        o1.setHotelId(1);
        o1.setType(2);
        o1.setTarget_roomNum(3);
        o1.setName("name");
        o1.setTargetMoney(500);
        o1.setDiscount(0);
        o1.setStartTime("2020-04-22");
        o1.setEndTime("2021-01-24");
        o1.setDiscountMoney(101);
        testPOST("/api/coupon/hotelMultiRoom", o1);
    }

    @Test
    public void test27() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("hotelId", "1");
        testGET("/api/coupon/hotelAllCoupons", params);
    }

    @Test
    public void test28() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("hotelId", "1");
        testGET("/api/coupon/hotelOrderedCoupons", params);
    }

    @Test
    public void test29() throws Exception {
        testGET("/api/coupon/37/annulCoupon", null);
    }

    @Test
    public void test30() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "6");
        params.put("hotelId", "1");
        params.put("orderPrice", "399");
        params.put("roomNum", "1");
        params.put("checkIn", "2020-06-15");
        params.put("checkOut", "2020-06-20");
        testGET("/api/coupon/orderMatchCoupons", params);
    }

    @Test
    public void test31() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("hotelId","3");
        testGET("/api/comment/getHotelComments", params);
    }

    @Test
    public void test32() throws Exception {
        CommentVO commentVO = new CommentVO();
        commentVO.setRate(5.0);
        commentVO.setCreateDate("2020-07-02");
        commentVO.setCommentContent("this is test");
        commentVO.setHotelId(3);
        commentVO.setOrderId(21);
        commentVO.setUserId(6);
        commentVO.setUserName("测试评论");
        testPOST("/api/comment/addComment", commentVO);
    }

    /**
     * @Description: 测试POST的复用辅助方法
     * @Author: Li Yongshao
     * @date: 2020/6/30
     */
    private void testPOST(String url, Object body) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSONObject.toJSONString(body));
        ResultActions resultActions = mvc.perform(request);
        resultActions.andReturn().getRequest().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());
    }

    /**
     * @Description: 测试GET的复用辅助方法
     * @Author: Li Yongshao
     * @date: 2020/6/30
     */
    private void testGET(String url, Object params) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        if (params != null) {
            if (params instanceof HashMap) {
                for (Map.Entry<String, String> entry : ((Map<String, String>) params).entrySet()
                ) {
                    request.param(entry.getKey(), entry.getValue());
                }
            }
            else request.params((MultiValueMap<String, String>) params);
        }
        ResultActions resultActions = mvc.perform(request);
        resultActions.andReturn().getRequest().setCharacterEncoding("UTF-8");
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        resultActions.andDo(MockMvcResultHandlers.print());
    }
}