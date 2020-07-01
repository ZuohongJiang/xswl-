package com.example.hotel.junits;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;
import java.util.List;

/**
 * @program: hotel
 * @description: 测试账号相关功能
 * @author: Li Yongshao
 * @create: 2020-06-27 23:00
 */
@Transactional
public class AccountTests extends HotelApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AccountService accountService;

    /**
     * @Description: 测试获取所有管理员
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testGetAllAdmin() {
        List<User> users = adminService.getAllManagers();
        Assert.assertSame("列表数量错误", 0, users.size());
    }

    /**
     * @Description: 测试增加管理员功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testAddAdmin() {
        UserForm user1 = new UserForm();
        user1.setEmail("test");
        user1.setPassword("test");
        try {
            adminService.addManager(user1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        List<User> users = adminService.getAllManagers();
        Assert.assertEquals("新增管理员email错误", "test", users.get(users.size() - 1).getEmail());
    }

    /**
     * @Description: 测试登录功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    public void testAccountLogin() {
        UserForm user1 = new UserForm();
        user1.setEmail("1012681@qq.com");
        user1.setPassword("123456");
        User getUser1 = accountService.login(user1);
        Assert.assertEquals("登录用户名错误", "测试一号", getUser1.getUserName());

        UserForm user2 = new UserForm();
        user2.setEmail("1012681@qq.com");
        user2.setPassword("test");
        User getUser2 = accountService.login(user2);
        Assert.assertNull(getUser2);

        UserForm user3 = new UserForm();
        user3.setEmail("test");
        user3.setPassword("test");
        User getUser3 = accountService.login(user2);
        Assert.assertNull(getUser3);
    }

    /**
     * @Description: 测试更新用户信息功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testUpdateInfo() {
        accountService.updateUserInfo(6, "测试更改", "999");
        User aft1 = accountService.getUserInfo(6);
        Assert.assertEquals("更改用户名错误", "测试更改", aft1.getUserName());
        Assert.assertEquals("更改手机号错误", "999", aft1.getPhoneNumber());
    }

    /**
     * @Description: 测试更新用户密码功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testUpdatePassword() {
        accountService.updateUserPassword(5, "123456", "999");
        User aft1 = accountService.getUserInfo(5);
        Assert.assertEquals("更改密码错误", "999", aft1.getPassword());

        ResponseVO responseVO = accountService.updateUserPassword(6, "999", "999");
        Assert.assertFalse(responseVO.getSuccess());
    }

    /**
     * @Description: 测试注册新用户功能
     * @Author: Li Yongshao
     * @date: 2020/6/27
     */
    @Test
    @Rollback
    public void testRegister() {
        UserVO userVO = new UserVO();
        userVO.setUserName("nickName");
        userVO.setEmail("testtest@qq.com");
        userVO.setPassword("Password");
        userVO.setUserType(UserType.Client);
        userVO.setCredit(99);
        userVO.setPhoneNumber("999");
        try {
            accountService.registerAccount(userVO);
        }catch (ServerException e){
            System.out.println(e.getMessage());
        }
        UserForm userForm = new UserForm();
        userForm.setEmail("testtest@qq.com");
        userForm.setPassword("Password");
        User user = accountService.login(userForm);
        Assert.assertEquals("注册邮箱错误", "testtest@qq.com", user.getEmail());
        Assert.assertEquals("注册密码错误", "Password", user.getPassword());
        Assert.assertEquals("注册用户名错误", "nickName", user.getUserName());

        try{
            accountService.registerAccount(userVO);
            Assert.fail("错误");
        }catch (ServerException e){
            Assert.assertEquals("报错出错","账号已存在",e.getMessage());
        }
    }
}