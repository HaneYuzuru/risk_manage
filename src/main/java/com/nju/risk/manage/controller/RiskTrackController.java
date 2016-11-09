package com.nju.risk.manage.controller;

import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.domain.RiskVO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.service.IRiskService;
import com.nju.risk.manage.service.IRiskTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: winsky
 * date: 2016/11/7
 * description:
 */
@Controller
@RequestMapping(value = "/riskTrack")
public class RiskTrackController extends BaseController {
    @Autowired
    IRiskTrackService riskTrackService;
    @Autowired
    IRiskService iRiskService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView riskView(@RequestParam("riskID") int riskID) {
        ModelAndView result = turnToPage("risktrack");
        RiskVO vo=iRiskService.getById(riskID);
        result.addObject("riskVO", vo);
        result.addObject("riskID", riskID);
        return result;
    }

    @RequestMapping(value = "/getRisktracks", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> Login(@RequestParam("beginDate") String beginDate,
                                     @RequestParam("endDate") String endDate,@RequestParam("riskID") int riskID) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        List<RiskTrackVO> list = riskTrackService.searchByTime(riskID,beginDate,endDate);
        modelMap.put("list", list);
        return modelMap;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Map<String, Object> add(@RequestParam("riskId") int riskId, @RequestParam("description") String description) {
        RiskTrackVO riskTrackVO = new RiskTrackVO();
        riskTrackVO.setRiskId(riskId);
        riskTrackVO.setStatus(RiskStatusEnum.RISK.toString());
        riskTrackVO.setDescription(description);

        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        boolean result = riskTrackService.addRiskTrackItem(riskTrackVO);
        if (result) {
            modelMap.put("result", "true");
        } else {
            modelMap.put("result", "创建风险跟踪失败，请仔细检查信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam("id") int id, @RequestParam("riskId") int riskId, @RequestParam("description") String description, @RequestParam("status") String status) {
        RiskTrackVO riskTrackVO = new RiskTrackVO();
        riskTrackVO.setId(id);
        riskTrackVO.setStatus(status);
        riskTrackVO.setRiskId(riskId);
        riskTrackVO.setStatus(RiskStatusEnum.RISK.toString());
        riskTrackVO.setDescription(description);

        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        boolean result = riskTrackService.updateRiskTrackItem(riskTrackVO);
        if (result) {
            modelMap.put("result", "true");
        } else {
            modelMap.put("result", "修改风险跟踪失败，请仔细检查信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("idStr") String idStr) throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>(1);

        if (idStr == null) {
            modelMap.put("result", "删除风险跟踪失败");
            return modelMap;
        }
        String[] idStrTemp = idStr.split(",");
        int len = idStrTemp.length;
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ids.add(Integer.parseInt(idStrTemp[i]));
        }

        boolean result = riskTrackService.deleteRiskTrackItem(ids);

        if (result) {
            modelMap.put("result", "true");
        } else {
            modelMap.put("result", "删除风险跟踪失败");
        }
        return modelMap;
    }
}
