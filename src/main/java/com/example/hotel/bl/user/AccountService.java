package com.example.hotel.bl.user;

import com.example.hotel.po.User;
import com.example.hotel.vo.*;

import java.rmi.ServerException;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO) throws ServerException;

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     * @param id
     * @return
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     * @param id
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id,String username,String phonenumber);

    ResponseVO updateUserPassword(int id,String oldPassword,String password);

    ResponseVO updateUserCredit(int id,double credit);

//    String getUserName(int id);
}
