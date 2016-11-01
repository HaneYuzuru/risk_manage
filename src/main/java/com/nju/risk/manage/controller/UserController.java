package com.nju.risk.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping
    public String user() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(@RequestParam("userId") int userId,
                              @RequestParam("password") String password) throws Exception {

        ModelAndView result = new ModelAndView("dashBoard");

        String loginMessage = "登录成功";

        result.addObject("loginMessage", loginMessage);

        return result;
    }
}
