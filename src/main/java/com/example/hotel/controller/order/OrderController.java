package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */

@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO) {
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders() {
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }


    @GetMapping("/{userid}/getUserOrders")
    public ResponseVO retrieveUserOrders(@PathVariable int userid) {
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid) {
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    @GetMapping("/{orderId}/deleteOrder")
    public ResponseVO deleteOrder(@PathVariable int orderId) {
        return orderService.deleteOrder(orderId);
    }
    @GetMapping("/{hotelId}/{userId}/getUserThisHotelOrders")
    public ResponseVO getUserThisHotelOrders(@PathVariable int hotelId, @PathVariable int userId) {
        return ResponseVO.buildSuccess(orderService.getUserThisHotelOrders(hotelId,userId));
    }
    @GetMapping("/{orderId}/executeOrder")
    public ResponseVO executeOrder(@PathVariable int orderId) {
        return orderService.executeOrder(orderId);
    }

    /**
     * @Description: 获取列表中所有酒店下的全部订单
     * @Params: hotelId列表
     * @returns: orderList
     * @Author: Li Yongshao
     * @date: 2020/7/2
     */
    @GetMapping("/getManageHotelsOrders")
    public ResponseVO retrieveManageHotelsOrders(@RequestParam int[] hotelIdList){
        //注意要用@RequestParam接受，且无法接受空列表(已在前端加入判断解决http报错)
        return ResponseVO.buildSuccess(orderService.getManageHotelsOrders(hotelIdList));
    }

    /**
     * @Description: 获取指定订单详细信息
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}/orderDetail")
    public ResponseVO retrieveOrderDetail(@PathVariable int orderId){
        return orderService.getOrderDetail(orderId);
    }
}