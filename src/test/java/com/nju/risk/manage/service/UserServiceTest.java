package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.domain.domainEnum.UserTypeEnum;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class UserServiceTest extends BaseDaoTestConfiguration {
    @Autowired
    IUserService userService;

    @Test
    public void testAPI() {
        UserDO userDO = new UserDO();
        userDO.setPassword("12345a");
        userDO.setName("test");
        userDO.setUserType(UserTypeEnum.DEVELOPER.value());
        boolean result = userService.register(userDO);
        Assert.assertTrue(result);

        result = userService.checkUserName("test");
        Assert.assertFalse(result);

        int ret = userService.login("test", "12345a");
        Assert.assertEquals(IUserService.LOGIN_SUCCESS, ret);

        UserDO user = userService.getUserByName("test");
        Assert.assertEquals(UserTypeEnum.DEVELOPER.value(), user.getUserType(), 0);
    }
}
