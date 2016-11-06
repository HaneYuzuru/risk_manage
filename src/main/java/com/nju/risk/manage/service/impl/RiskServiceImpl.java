package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskDAO;
import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.RiskQueryDO;
import com.nju.risk.manage.service.IRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
@Service
public class RiskServiceImpl implements IRiskService {
    @Autowired
    IRiskDAO riskDAO;

    @Override
    public boolean addRiskItem(RiskDO riskDO) {
        return riskDAO.insert(riskDO);
    }

    @Override
    public boolean deleteRiskItem(int id) {
        return riskDAO.delete(id);
    }

    @Override
    public boolean updateRiskItem(RiskDO riskDO) {
        return riskDAO.update(riskDO);
    }

    @Override
    public List<RiskDO> search(Object keyword, int type, boolean fuzzy) {
        List<RiskDO> result = Lists.newArrayList();
        if (keyword == null) {
            return result;
        }
        RiskQueryDO riskQueryDO = new RiskQueryDO();
        riskQueryDO.setFuzzy(fuzzy);
        switch (type) {
            case IRiskService.SEARCH_BY_NAME:
                riskQueryDO.setName(String.valueOf(keyword));
                break;
            case IRiskService.SEARCH_BY_CONTENT:
                riskQueryDO.setContent(String.valueOf(keyword));
                break;
            case IRiskService.SEARCH_BY_POSSIBILITY:
                riskQueryDO.setPossibility(Integer.parseInt(keyword.toString()));
                break;
            case IRiskService.SEARCH_BY_COMMITTER:
                // 提交者不进行模糊查找
                break;
            case IRiskService.SEARCH_BY_FOLLOWER:
                // 跟踪者不进行模糊查找
                break;
            case IRiskService.SEARCH_BY_IMPACT:
                riskQueryDO.setImpact(Integer.parseInt(keyword.toString()));
                break;
        }
        result = riskDAO.search(riskQueryDO);
        return result;
    }
}
