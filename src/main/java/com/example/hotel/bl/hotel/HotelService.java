package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    void addHotel(HotelVO hotelVO) throws ServiceException;
    /**
     *  修改酒店基本信息
     * @param hotelVO
     * @throws
     */
    void updateHotelInfo(HotelVO hotelVO) throws ServiceException;

    /**
     * 预订酒店修改剩余客房信息
     * @param roomId
     * @param rooms
     */
    void updateRoomInfo(Integer roomId,Integer rooms);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     * @param roomId
     * @return
     */
    int getRoomCurNum(Integer roomId);

    void deleteHotel(Integer hotelId);

    /**
     * 获取指定hotelManager管理的所有酒店
     * @param managerId
     * @return
     */
    List<HotelVO> retrieveHotelsByManagerId(Integer managerId);

}
