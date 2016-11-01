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
}
