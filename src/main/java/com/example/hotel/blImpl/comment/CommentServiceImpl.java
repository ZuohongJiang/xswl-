package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Comment;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final static String ADD_ERROR = "评论添加失败";
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override public ResponseVO addComment(CommentVO commentVO){
        try{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        commentVO.setCreateDate(curdate);
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentVO, comment);
        commentMapper.addComment(comment);
        orderMapper.commentOrder(commentVO.getOrderId());
        Integer recordNums = commentMapper.getHotelCommentsNums(commentVO.getHotelId());
        Double rate = hotelMapper.selectHotelRate(commentVO.getHotelId());
        rate = ((recordNums-1)*rate + commentVO.getRate())/(recordNums);
        hotelMapper.updateHotelRate(commentVO.getHotelId(),rate);
    } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ADD_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }
    @Override public List<Comment> getHotelComments(Integer hotelId){
        return commentMapper.getHotelComments(hotelId);
    }
}
