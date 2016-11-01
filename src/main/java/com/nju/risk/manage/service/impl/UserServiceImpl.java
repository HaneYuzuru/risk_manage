package com.nju.risk.manage.service.impl;

import com.nju.risk.manage.dao.IUserDAO;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<UserDO> getUserDOListByUserIds(List<Integer> idList) {
        return userDAO.selectByUserIdList(idList);
    }
}
