package com.example.hotel.controller.comment;


import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.blImpl.comment.CommentServiceImpl;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("addComment")
    public ResponseVO addComment(@RequestBody CommentVO commentVO){
        return commentService.addComment(commentVO);
    }

    @GetMapping("getHotelComments")
    public ResponseVO getHotelComments(@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(commentService.getHotelComments(hotelId));
    }
}
