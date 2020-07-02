package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String PRICE_ERROR = "订单价格异常";

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getRoomId());
        if (reserveRoomNum > curNum) {
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        if(orderVO.getPrice()<=0){
            return ResponseVO.buildFailure(PRICE_ERROR);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO, order);
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getRoomId(), orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userid) {
        return orderMapper.getUserOrders(userid);
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        Order order = orderMapper.getOrderById(orderid);
        orderMapper.annulOrder(order.getId());
        Long curTime=System.currentTimeMillis();
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Long exeTime=sdf.parse(order.getCheckInDate(),pos).getTime();
        if((exeTime-curTime)/3600000<6){
            User user=accountService.getUserInfo(order.getUserId());
            accountService.updateUserCredit(user.getId(),user.getCredit()-order.getPrice()/2);
        }
        hotelService.updateRoomInfo(order.getRoomId(), (-order.getRoomNum()));
        return ResponseVO.buildSuccess(true);
    }

    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<Order> getHotelOrders(Integer hotelId) {
        List<Order> orders = getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    /**
     *
     * @param orderid
     * @return
     */

    @Override
    public ResponseVO deleteOrder(int orderid){
        orderMapper.annulOrder(orderid);
        orderMapper.deleteOrder(orderid);
        return ResponseVO.buildSuccess(true);
    }
    @Override
    public ResponseVO executeOrder(int orderId){
        orderMapper.executeOrder(orderId);
        return ResponseVO.buildSuccess(true);
    }
}
