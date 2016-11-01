package com.nju.risk.manage.controller;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping
    public String user() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(@RequestParam("userId") int userId,
                              @RequestParam("password") String password) throws Exception {

        ModelAndView result = new ModelAndView("dashBoard");

        List<UserDO> userDOList = userService.getUserDOListByUserIds(Lists.newArrayList(userId));

        String loginMessage = null;

        if(userDOList.isEmpty()){
            loginMessage = "用户不存在";
        } else{
            UserDO thisUser = userDOList.get(0);
            if(Objects.equals(thisUser.getPassword(), password)){
                loginMessage = "登录成功";
            } else{
                loginMessage = "密码错误";
            }
        }

        result.addObject("loginMessage", loginMessage);

        return result;
    }
}
