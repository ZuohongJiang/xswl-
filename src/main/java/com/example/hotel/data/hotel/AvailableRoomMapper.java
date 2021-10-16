package com.example.hotel.data.hotel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AvailableRoomMapper {
    public void insertAvailableRoom(@Param("roomId") int roomId,@Param("currentDate") int currentDate,@Param("quantity") int quantity,@Param("hotelId") int hotelId);
}
