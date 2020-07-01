package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {

        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }
    @PostMapping("/updateHotelInfo")
    public ResponseVO updateHotelInfo(@RequestBody HotelVO hotelVO) throws ServiceException{
        hotelService.updateHotelInfo(hotelVO);

        return ResponseVO.buildSuccess(true);
    }
    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess();
    }
    @GetMapping("/{hotelManagerId}/getHotelsByManagerId")
    public ResponseVO retrieveHotelsByManagerId(@PathVariable Integer hotelManagerId){
        return ResponseVO.buildSuccess(hotelService.retrieveHotelsByManagerId(hotelManagerId));
    }
    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }
    @PostMapping("/editRoom")
    public ResponseVO editRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.editRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess();
    }
    @GetMapping("/deleteRoom")
    public ResponseVO deleteRoom(@RequestParam Integer roomId){
        roomService.deleteRoom(roomId);
        return ResponseVO.buildSuccess(true);
    }
    @GetMapping("/deleteHotel")
    public ResponseVO deleteHotel(@RequestParam Integer hotelId){
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess(true);
    }
}
