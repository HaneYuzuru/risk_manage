package com.nju.risk.manage.dao;

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

    @Test
    public void testGetByName(){
        String name = "na";
        List<UserDO> users=userDAO.searchByName(name);
        System.out.println(users);
    }

    @Test
    public void testRegister(){
        UserDO userDO = new UserDO();
        userDO.setName("nana");
        userDO.setPassword("nana");
        userDO.setUserType(1);
        userDO.setDataStatus(1);
        userDAO.insert(userDO);
    }
}
