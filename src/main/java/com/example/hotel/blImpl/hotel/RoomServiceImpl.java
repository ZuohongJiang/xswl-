package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public void insertRoomInfo(HotelRoom hotelRoom) {
        roomMapper.insertRoom(hotelRoom);
    }

    @Override
    public void updateRoomInfo(Integer roomId, Integer rooms) {
        roomMapper.updateRoomInfo(roomId,rooms);
    }

    @Override
    public int getRoomCurNum(Integer roomId) {
        return roomMapper.getRoomCurNum(roomId);
    }

    @Override
    public void editRoomInfo(HotelRoom hotelRoom){
        roomMapper.editRoomInfo(hotelRoom);
    }

    @Override
    public void deleteRoom(Integer roomId) {
        roomMapper.deleteRoom(roomId);
    }
}
