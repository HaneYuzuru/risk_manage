package com.nju.risk.manage.controller;
import com.nju.risk.manage.domain.RiskVO;
import com.nju.risk.manage.service.IRiskService;
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

/**
 * Created by huanghanqian on 16/11/5.
 */
@Controller
@RequestMapping(value = "/risk")
public class RiskController extends BaseController{
    @Autowired
    IRiskService iRiskService;

    @RequestMapping
    public ModelAndView riskView() {
        return turnToPage("home");
    }

    @RequestMapping(value = "/getRisks", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> Login(@RequestParam("beginDate") String beginDate,
                                     @RequestParam("endDate") String endDate) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        List<RiskVO> list=iRiskService.searchByTime(beginDate,endDate);
        modelMap.put("list", list);
        return modelMap;
    }


}
