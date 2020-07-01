package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {

    int addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getUserOrders(@Param("userId") int userId);

    int annulOrder(@Param("orderId") int orderId);

    Order getOrderById(@Param("orderId") int orderId);

    int deleteOrder(@Param("orderId") int orderId );

    int executeOrder(@Param("orderId") int orderId);
}
