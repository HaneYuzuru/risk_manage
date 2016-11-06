package com.nju.risk.manage.controller;

import com.nju.risk.manage.domain.UserDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghanqian on 16/11/5.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController{

    @RequestMapping
    public ModelAndView userView() {
        ModelAndView result;
        if(checkLogin()=="login"){
            result = new ModelAndView("login");
        }
        else{
            result = new ModelAndView("home");
            result.addObject("username", username);
            result.addObject("type", type);
        }
        return result;
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

        ModelAndView result = new ModelAndView("home");

        UserDO user=new UserDO(username,password,type);

        userService.register(user);

        session.setAttribute(USER_NAME_SESSION,username);
        result.addObject("username", username);
        result.addObject("type", type);

        return result;
    }

    @RequestMapping(value = "/testlogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> Login(@RequestParam("username") String username,
                              @RequestParam("password") String password) throws Exception {

        ModelAndView result = new ModelAndView("dashBoard");

        Map<String, Object> modelMap = new HashMap<String, Object>(1);

        int s=userService.login(username,password);

        if(s==0){
            session.setAttribute(USER_NAME_SESSION,username);
            modelMap.put("result", "true");
        }
        else if(s==1){
            modelMap.put("result", "密码错误");
        }
        else{
            modelMap.put("result", "用户名不存在");
        }

        return modelMap;
    }
}
