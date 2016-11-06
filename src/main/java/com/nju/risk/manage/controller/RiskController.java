package com.nju.risk.manage.controller;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/risk")
public class RiskController extends BaseController{
    @RequestMapping
    public ModelAndView riskView() {
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


}
