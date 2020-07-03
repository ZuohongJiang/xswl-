package com.example.hotel.data.comment;

import com.example.hotel.po.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int addComment(Comment comment);

    List<Comment> getHotelComments(@Param("hotelId") Integer hotelId);

    Integer getHotelCommentsNums(@Param("hotelId") Integer hotelId);
}
