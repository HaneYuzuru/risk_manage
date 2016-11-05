package com.nju.risk.manage.dao;

import com.nju.risk.manage.domain.UserDO;

import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/10/31
 */

public interface IUserDAO {
    public boolean insert(UserDO userDO);

    public int batchInsert(List<UserDO> userDOList);

    public boolean update(UserDO userDO);

    public int batchUpdate(List<UserDO> userDOList);

    public boolean delete(Integer userId);

    public int batchDeleteByIdList(List<Integer> idList);

    public List<UserDO> selectByIdList(List<Integer> userIdList);

    public List<UserDO> selectByName(String name);

    public UserDO login(String name, String password);
}
