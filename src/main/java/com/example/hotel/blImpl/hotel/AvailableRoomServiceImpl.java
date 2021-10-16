package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.AvailableRoomService;
import com.example.hotel.data.hotel.AvailableRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvailableRoomServiceImpl implements AvailableRoomService {
    @Autowired
    private AvailableRoomMapper availableRoomMapper;
    public void addAvailableRoom(int roomId, int hotelId){
        int currentDate = 1632715200;
        int day = 86400;
        int nums = 30;
        int endDate = currentDate + nums * day;
        while(currentDate < endDate){
            currentDate += day;
            availableRoomMapper.insertAvailableRoom(roomId, currentDate, 5, hotelId);
        }
    }
}
