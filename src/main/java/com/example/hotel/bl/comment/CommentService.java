package com.example.hotel.bl.comment;

import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import java.util.List;

public interface CommentService {
    /**
     * 写评论
     * @param commentVO
     * @return
     */
    ResponseVO addComment(CommentVO commentVO);

    /**
     * 获得指定酒店的评论列表
     * @param hotelId
     * @return
     */
    List<Comment> getHotelComments(Integer hotelId);
}
