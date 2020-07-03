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
        if (reserveRoomNum > curNum) { //判断余量是否充足
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        if(orderVO.getPrice()<=0){ //不允许非正订单
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
            BeanUtils.copyProperties(orderVO, order);   //vo->po 订单类型转化
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
    public List<Order> getUserThisHotelOrders(int hotelId,int userId) {
        return orderMapper.getUserThisHotelOrders(hotelId,userId);
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
        //判断是否超期，超期扣除信用值，为订单价值的1/2
        if((exeTime-curTime)/3600000<6){
            User user=accountService.getUserInfo(order.getUserId());
            accountService.updateUserCredit(user.getId(),user.getCredit()-order.getPrice()/2);
        }
        //更新对应房间的存量
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
     * @Description: 获取列表中所有hotelId对应的酒店下的全部订单
     * @Params: hotelId列表，可能为空
     * @returns: 订单列表
     * @Author: Jiang Zuohong
     * @date: 2020/7/1
     */
    @Override public List<Order> getManageHotelsOrders(int[] hotelIdList){
        //注意参数，是当前管理员账户下的所有酒店id列表
        List<Order> orders = getHotelOrders(hotelIdList[0]);
        for(int i=1;i<hotelIdList.length;i++){
            orders.addAll(getHotelOrders(hotelIdList[i]));
        }
        return orders;
    }

    /**
     *
     * @param orderid
     * @return
     */

    @Override
    public ResponseVO deleteOrder(int orderid){
        Order order = orderMapper.getOrderById(orderid);
        hotelService.updateRoomInfo(order.getRoomId(), (-order.getRoomNum()));
        orderMapper.deleteOrder(orderid);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO executeOrder(int orderId){
        orderMapper.executeOrder(orderId);
        //增加当前订单价值的信用值
        Order order=orderMapper.getOrderById(orderId);
        User user=accountService.getUserInfo(order.getUserId());
        accountService.updateUserCredit(user.getId(),user.getCredit()+order.getPrice());
        return ResponseVO.buildSuccess(true);
    }
    @Override
    public void commentOrder(int orderid){
        orderMapper.commentOrder(orderid);
    }
}
