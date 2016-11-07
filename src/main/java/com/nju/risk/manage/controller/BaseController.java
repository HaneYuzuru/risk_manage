package com.nju.risk.manage.controller;

import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
@Controller
@RequestMapping(value = "/base")
public class BaseController {
    public static final String USER_NAME_SESSION = "userName";//session中存放的登陆用户的用户名

    protected String username;//登陆用户的用户名
    protected Integer type;//登陆用户的角色
    protected Integer userId;//当前登录用户的id
    @Autowired
    IUserService userService;
    @Autowired
    protected HttpSession session;

    @RequestMapping(value = "/getUserInfo")
    public String checkLogin() {
        String name = (String) session.getAttribute(USER_NAME_SESSION);
        if (StringUtils.isEmpty(name)) {
            return "login";
        }
        UserDO user = userService.getUserByName(name);
        this.username=user.getName();
        this.type=user.getUserType();
        this.userId=user.getId();

        return "success";
    }

    @RequestMapping(value = "/gotoPage")
    public ModelAndView turnToPage(String view) {
        ModelAndView result;
        if(checkLogin().equals("login")){
            result = new ModelAndView("login");
        }
        else{
            result = new ModelAndView(view);
            result.addObject("username", username);
            result.addObject("type", type);
        }
        return result;
    }

    @RequestMapping(value = "/getDate")
    public String getDateString(int offset ){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,  offset);
        String date = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());
        return date;
    }

}
