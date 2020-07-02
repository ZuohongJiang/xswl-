package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     *
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     *
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     *
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     *
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 查看酒店的所有订单
     *
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    /**
     * 删除订单
     * @param orderid
     * @return
     */

    ResponseVO deleteOrder(int orderid);

    /**
     *
     * @param orderid
     * 将订单状态设为已执行
     * @return
     */

    ResponseVO executeOrder(int orderid);

    /**
     * 获得hotelIdList内所有酒店的订单
     * @param hotelIdList
     * @return
     */
    List<Order> getManageHotelsOrders(int[] hotelIdList);
}

