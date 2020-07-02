package com.example.hotel.junits;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: hotel
 * @description: 测试评价相关的功能
 * @author: Li Yongshao
 * @create: 2020-07-03 01:14
 */
@Transactional
public class CommentTests extends HotelApplicationTests {
    @Autowired
    private CommentService commentService;

    /**
     * @Description: 测试增加新评价
     * @Author: Li Yongshao
     * @date: 2020/7/3
     */
    @Test
    @Rollback
    public void tesAddNewComment() {
        CommentVO commentVO = new CommentVO();
        commentVO.setRate(5.0);
        commentVO.setCreateDate("2020-07-02");
        commentVO.setCommentContent("this is test");
        commentVO.setHotelId(3);
        commentVO.setOrderId(21);
        commentVO.setUserId(6);
        commentVO.setUserName("测试评论");
        commentService.addComment(commentVO);
        List<Comment> comments = commentService.getHotelComments(3);
        Assert.assertSame("增加错误", 2, comments.size());
    }

    /**
     * @Description: 测试增加新评价
     * @Author: Li Yongshao
     * @date: 2020/7/3
     */
    @Test
    @Rollback
    public void tesGetHotelAllComments() {
        List<Comment> comments = commentService.getHotelComments(1);
        Assert.assertSame("数量错误", 1, comments.size());
    }
}