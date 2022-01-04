package com.example.hotel.junits;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hotel
 * @description: 测试房间相关功能
 * @author: Li Yongshao
 * @create: 2020-06-27 21:16
 */
@Transactional
public class RoomInfoTests extends HotelApplicationTests {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;

    /**
     * @Description: 测试获取房间数量
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testGetRoomCurNum() {
        Assert.assertSame("房间剩余数量错误", 8, hotelService.getRoomCurNum(4));
    }

    /**
     * @Description: 测试获取指定酒店的所有房间
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testGetAllHotelRooms() {
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        Assert.assertSame("酒店房间列表大小错误", 4, hotelRooms.size());
        Assert.assertEquals("房间细节错误",
                "{\"area\":27.5,\"level\":\"5-11\",\"bedType\":\"两张1.5米床\",\"addBed\":true,\"facility\":\"<span style='color:green'>测试</span>\"}",
                hotelRooms.get(1).getDetail().trim());
    }

    /**
     * @Description: 测试增加房间
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testInsertRoom() {
        HotelRoom addHotelRoom = new HotelRoom();
        addHotelRoom.setHotelId(1);
        addHotelRoom.setTotal(13);
        addHotelRoom.setCurNum(13);
        addHotelRoom.setPrice(233);
        addHotelRoom.setRoomType("大床房");
        addHotelRoom.setDetail("test");
        roomService.insertRoomInfo(addHotelRoom);
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        int addId = hotelRooms.get(hotelRooms.size() - 1).getId();
        Assert.assertSame("增加房间余量错误", 13, roomService.getRoomCurNum(addId));
    }

    /**
     * @Description: 测试增加房间数量
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testUpdateRoomNum() {
        int curNum = hotelService.getRoomCurNum(3);
        hotelService.updateRoomInfo(3, 3);
        Assert.assertSame("更新剩余房量错误", curNum - 3, hotelService.getRoomCurNum(3));  //注意更新语句是curNum=curNum-#{curNum}
    }

    /**
     * @Description: 测试编辑房间信息
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testEditRoom() {
        HotelRoom editHotelRoom = new HotelRoom();
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        BeanUtils.copyProperties(hotelRooms.get(0), editHotelRoom);
        editHotelRoom.setDetail("test");
        editHotelRoom.setTotal(editHotelRoom.getTotal() + 3);   //注意剩余房间计算公式curNum=curNum+#{total}-total
        roomService.editRoomInfo(editHotelRoom);
        List<HotelRoom> aft = roomService.retrieveHotelRoomInfo(1);
        Assert.assertEquals("编辑细节出错", "test", aft.get(0).getDetail());
        Assert.assertSame("编辑剩余房间出错", editHotelRoom.getCurNum() + 3, roomService.getRoomCurNum(editHotelRoom.getId()));
    }

    /**
     * @Description: 测试删除房间
     * @Author: Li Yongshao
     * @date: 2020/6/29
     */
    @Test
    @Rollback
    public void testDeleteRoom() {
        List<HotelRoom> hotelRooms = roomService.retrieveHotelRoomInfo(1);
        int before = hotelRooms.size();
        roomService.deleteRoom(hotelRooms.get(0).getId());
        hotelRooms = roomService.retrieveHotelRoomInfo(1);
        Assert.assertSame("数量错误", before - 1, hotelRooms.size());
    }
}