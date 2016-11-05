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
     * @return
     */
    boolean checkUserName(String name);

    /**
     * 用户登录
     *
     * @param name     用户名
     * @param password 密码
     * @return 登录状态 true：成功，false：失败
     */
    boolean login(String name, String password);

    /**
     * 用户注册
     *
     * @param userDO
     * @return 注册状态 true：成功，false：失败
     */
    boolean register(UserDO userDO);
}
