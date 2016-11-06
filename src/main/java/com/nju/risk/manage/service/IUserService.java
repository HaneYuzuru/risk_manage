package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.UserDO;

import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
public interface IUserService {
    List<UserDO> getUserDOListByUserIds(List<Integer> idList);

    /**
     * 检测用户名是否存在
     *
     * @param name
     * @return true：名字可用 false ：名字不可用
     */
    boolean checkUserName(String name);

    public static final int LOGIN_SUCCESS = 0;
    public static final int LOGIN_INVALID_PASSWORD = 1;
    public static final int LOGIN_INVALID_NAME = 2;

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 登录状态 0-成功，1-密码错误，2-用户名
     */
    int login(String name, String password);

    /**
     * 用户注册
     *
     * @param userDO
     * @return 注册状态 true：成功，false：失败
     */
    boolean register(UserDO userDO);

    /**
     * 根据用户名获取用户信息
     *
     * @param name
     * @return null则表示没有找到该用户名的用户
     */
    UserDO getUserByName(String name);
}
