package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommentService commentService;

    @Override
    public void addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.Admin)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
//        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
    }

    @Override
    public void updateHotelInfo(HotelVO hotelVO) throws ServiceException{
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || manager.getUserType().equals(UserType.Client)){
            throw new ServiceException("管理员不存在或者无权限！更新酒店信息失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setId(hotelVO.getId());
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.updateHotelInfo(hotel);
    }


//    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
//        roomService.updateRoomInfo(hotelId,roomType,rooms);
    @Override
    public void updateRoomInfo(Integer roomId, Integer rooms) {
        roomService.updateRoomInfo(roomId,rooms);

    }

    @Override
    public int getRoomCurNum(Integer roomId) {
        return roomService.getRoomCurNum(roomId);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        if(hotelVO==null)return null;
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            roomVO.setDetail(r.getDetail());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);
        List<Comment> comments = commentService.getHotelComments(hotelId);
        List<CommentVO> commentVOS = comments.stream().map(c -> {
            CommentVO commentVO =new CommentVO();
            commentVO.setId(c.getId());
            commentVO.setHotelId(c.getHotelId());
            commentVO.setUserId(c.getUserId());
            commentVO.setOrderId(c.getOrderId());
            commentVO.setRate(c.getRate());
            commentVO.setCreateDate(c.getCreateDate());
            commentVO.setCommentContent(c.getCommentContent());
            commentVO.setUserName(c.getUserName());
            return commentVO;
        }).collect(Collectors.toList());
        hotelVO.setComments(commentVOS);

        return hotelVO;
    }

    @Override
    public void deleteHotel(Integer hotelId) {
        hotelMapper.deleteHotel(hotelId);
    }

    @Override
    public List<HotelVO> retrieveHotelsByManagerId(Integer managerId){
        return hotelMapper.selectHotelsByManagerId(managerId);
    }

    @Override
    public void updateHotelRate(Integer hotelId,double rate){
        hotelMapper.updateHotelRate(hotelId,rate);
    }

    @Override
    public Double selectHotelRate(Integer hotelId){
        return hotelMapper.selectHotelRate(hotelId);
    }
}
