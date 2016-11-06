package com.nju.risk.manage.controller;

import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
@Controller
@RequestMapping(value = "/base")
public class BaseController {
    public static final String USER_NAME_SESSION = "userName";//session中存放的登陆用户的用户名
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/getUserInfo")
    public UserDO checkLogin(HttpSession session) {
        String name = session.getAttribute(USER_NAME_SESSION).toString();
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return userService.getUserByName(name);
    }
}
