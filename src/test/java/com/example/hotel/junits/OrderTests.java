package com.example.hotel.junits;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hotel
 * @description: 测试订单相关功能
 * @author: Li Yongshao
 * @create: 2020-06-28 00:13
 */
@Transactional
public class OrderTests extends HotelApplicationTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoomService roomService;


    /**
     * @Description: 测试取得订单列表相关
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    public void testGetOrders() {
        List<Order> orders = orderService.getAllOrders();
        Assert.assertSame("全部订单数量错误", 9, orders.size());

        orders=orderService.getUserOrders(6);
        Assert.assertSame("用户订单数量错误",9,orders.size());
        orders=orderService.getUserOrders(1);
        Assert.assertSame("用户订单数量错误",0,orders.size());

        orders=orderService.getHotelOrders(1);
        Assert.assertSame("酒店订单数量错误",8,orders.size());
        orders=orderService.getHotelOrders(2);
        Assert.assertSame("酒店订单数量错误",1,orders.size());
    }
    @Test
    @Rollback
    public void testAddOrder(){
        OrderVO orderVO1=new OrderVO();
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
        orderService.addOrder(orderVO1);
        List<Order> orders=orderService.getAllOrders();
        Assert.assertEquals("添加日期错误","2020-06-23",orders.get(orders.size()-1).getCheckOutDate());

        OrderVO orderVO2=new OrderVO();
        BeanUtils.copyProperties(orderVO1,orderVO2);
        orderVO2.setRoomNum(6);
        ResponseVO responseVO=orderService.addOrder(orderVO2);
        Assert.assertFalse(responseVO.getSuccess());
    }
    /**
     * @Description: 测试取消订单
     * @Author: Li Yongshao
     * @date: 2020/6/28
     */
    @Test
    @Rollback
    public void testAnnualOrder(){
        List<Order> orders=orderService.getAllOrders();
        for (Order order:orders
             ) {
            if(order.getOrderState().equals("已预订")){
                int bef=roomService.getRoomCurNum(order.getRoomId());
                int aNum=order.getRoomNum();
                orderService.annulOrder(order.getId());
                Assert.assertSame("取消订单释放房间错误",bef+aNum,roomService.getRoomCurNum(order.getRoomId()));
            }
        }
    }
}