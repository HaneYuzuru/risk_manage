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

    private String name;//登陆用户的用户名
    private Integer type;//登陆用户的角色
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/getUserInfo")
    public String checkLogin(HttpSession session) {
        String name = (String) session.getAttribute(USER_NAME_SESSION);
        if (StringUtils.isEmpty(name)) {
            return "login";
        }
        UserDO user = userService.getUserByName(name);
        this.setName(user.getName());
        this.setType(user.getUserType());

        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
