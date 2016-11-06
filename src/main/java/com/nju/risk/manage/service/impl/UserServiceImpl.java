package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IUserDAO;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDO> getUserDOListByUserIds(List<Integer> idList) {
        return userDAO.selectByIdList(idList);
    }

    @Override
    public boolean checkUserName(String name) {
        if (StringUtils.isEmpty(name)) {
            return true;
        }
        List<UserDO> users = userDAO.selectByName(name);

        if (CollectionUtils.isEmpty(users)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int login(String name, String password) {
        if (StringUtils.isEmpty(name)) {
            return IUserService.LOGIN_INVALID_NAME;
        }

        List<UserDO> users = userDAO.selectByName(name);
        if (CollectionUtils.isEmpty(users)) {
            return IUserService.LOGIN_INVALID_NAME;
        }

        UserDO user = users.get(0);
        String expect = user.getPassword();
        if (expect.equals(password)) {
            return IUserService.LOGIN_SUCCESS;
        } else {
            return IUserService.LOGIN_INVALID_PASSWORD;
        }
    }

    @Override
    public boolean register(UserDO userDO) {
        return userDAO.insert(userDO);
    }

    @Override
    public UserDO getUserByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        List<UserDO> users = userDAO.selectByName(name);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        return users.get(0);
    }

    @Override
    public List<UserDO> searchByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Lists.newArrayList();
        }
        return userDAO.searchByName(name);
    }
}
