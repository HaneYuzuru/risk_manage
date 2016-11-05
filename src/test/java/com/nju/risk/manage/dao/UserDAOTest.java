package com.nju.risk.manage.dao;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
public class UserDAOTest extends BaseDaoTestConfiguration {
    @Autowired
    private IUserDAO userDAO;

    @Test
    public void insertTest() throws Exception {
        UserDO userDO = new UserDO();
        userDO.setName("nana");
        userDO.setPassword("nana");
        userDO.setUserType(1);
        userDO.setDataStatus(1);
        boolean ret = userDAO.insert(userDO);
        Assert.assertTrue(ret);
        List<UserDO> resultList = userDAO.selectByUserIdList(Lists.newArrayList(7));
        Assert.assertTrue(resultList.size() > 0);
    }

    @Test
    public void batchTest() throws Exception{
        UserDO userDO1 = createDefaultUserDO(2);
        UserDO userDO2 = createDefaultUserDO(3);
        int ret = userDAO.batchInsert(Lists.newArrayList(userDO1, userDO2));
        Assert.assertEquals(2, ret);
        int changeNum = userDAO.batchUpdate(Lists.newArrayList(userDO1, userDO2));
        List<UserDO> resultList = userDAO.selectByUserIdList(Lists.newArrayList(8, 9));
        Assert.assertEquals(2, resultList.size());
        Assert.assertNotEquals(0, changeNum);
        int deleteNum = userDAO.batchDeleteByIdList(Lists.newArrayList(2, 3));
        Assert.assertEquals(2, deleteNum);

    }

    private UserDO createDefaultUserDO(Integer userId){
        UserDO userDO = new UserDO();
        if(userId!=0){
            userDO.setId(userId);
        }
        userDO.setName("nana");
        userDO.setPassword("nana");
        userDO.setUserType(1);
        userDO.setDataStatus(1);
        return userDO;
    }
}
