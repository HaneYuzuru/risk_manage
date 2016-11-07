package com.nju.risk.manage.controller;

import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/searchByName", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchByName(@RequestParam("name") String name) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        List<UserDO> list = userService.searchByName(name);
        modelMap.put("list", list);
        return modelMap;
    }
}
