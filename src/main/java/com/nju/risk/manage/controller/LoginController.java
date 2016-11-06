package com.nju.risk.manage.controller;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by huanghanqian on 16/11/5.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping
    public String userView() {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String registerView() {
        return "register";
    }

    @RequestMapping(value = "/testRegister", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> testregisterForm(@RequestParam("username") String username) throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        if(userService.checkUserName(username)==true){
            modelMap.put("result", "true");
        }
        else{
            modelMap.put("result", "false");
        }
        return modelMap;
    }

    @RequestMapping(value = "/submitRegister", method = RequestMethod.POST)
    public ModelAndView registerForm(@RequestParam("form-firstname") String username,
                              @RequestParam("form-password") String password,
                              @RequestParam("optionsRadiosinline") int type) throws Exception {

        ModelAndView result = new ModelAndView("dashBoard");

        UserDO user=new UserDO(username,password,type);

        userService.register(user);

        result.addObject("loginMessage", username);

        return result;
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
