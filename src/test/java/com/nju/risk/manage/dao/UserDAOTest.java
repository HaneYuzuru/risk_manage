package com.nju.risk.manage.dao;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
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
        userDO.setUserId(1);
        userDO.setPassword("nana");
        userDO.setUserType(1);
        userDO.setGroupId(1);
        userDO.setUserSwitch(1);
        userDO.setDataStatus(1);
        boolean ret = userDAO.insert(userDO);
        Assert.assertTrue(ret);
        List<UserDO> resultList = userDAO.selectByUserIdList(Lists.newArrayList(1));
        Assert.assertTrue(resultList.size() > 0);
    }

    @Test
    public void batchTest() throws Exception{
        UserDO userDO1 = createDefaultUserDO(2);
        UserDO userDO2 = createDefaultUserDO(3);
        int ret = userDAO.batchInsert(Lists.newArrayList(userDO1, userDO2));
        Assert.assertEquals(2, ret);
        userDO1.setUserSwitch(2);
        userDO2.setUserSwitch(2);
        int changeNum = userDAO.batchUpdate(Lists.newArrayList(userDO1, userDO2));
        List<UserDO> resultList = userDAO.selectByUserIdList(Lists.newArrayList(2, 3));
        Assert.assertEquals(2, resultList.size());
        Assert.assertNotEquals(0, changeNum);
        for(UserDO user:resultList){
            int user_switch = user.getUserSwitch();
            Assert.assertEquals(2, user_switch);
        }
        int deleteNum = userDAO.batchDeleteByUserIdList(Lists.newArrayList(2, 3));
        Assert.assertEquals(2, deleteNum);

    }

    private UserDO createDefaultUserDO(Integer userId){
        UserDO userDO = new UserDO();
        userDO.setUserId(userId);
        userDO.setPassword("nana");
        userDO.setUserType(1);
        userDO.setGroupId(1);
        userDO.setUserSwitch(1);
        userDO.setDataStatus(1);
        return userDO;
    }
}
