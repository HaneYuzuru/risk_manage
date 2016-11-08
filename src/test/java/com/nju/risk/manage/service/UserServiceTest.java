package com.nju.risk.manage.service;

import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class UserServiceTest extends BaseDaoTestConfiguration {
    @Autowired
    IUserService userService;

    @Test
    public void testLogin() {
        String name = "winsky";
        String password = "12345a";
        int result = userService.login(name, password);
        Assert.assertEquals(IUserService.LOGIN_SUCCESS, result);
    }

    @Test
    public void testSearch() {
        String name = "";
        int size = userService.searchByName(name).size();
        Assert.assertNotEquals(0, size);
    }

}
