package com.nju.risk.manage.service.impl;

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
    public boolean login(String name, String password) {

        return false;
    }

    @Override
    public boolean register(UserDO userDO) {
        try {
            return userDAO.insert(userDO);
        } catch (Exception e) {
            return false;
        }
    }
}
