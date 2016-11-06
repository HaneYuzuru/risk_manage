package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskDAO;
import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.RiskQueryDO;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.service.IRiskService;
import com.nju.risk.manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    IUserService userService;

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
                result = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_CONTENT:
                riskQueryDO.setContent(String.valueOf(keyword));
                result = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_POSSIBILITY:
                riskQueryDO.setPossibility(Integer.parseInt(keyword.toString()));
                result = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_COMMITTER:
                // 提交者，先模糊查找找到id，再根据id去找记录
                String committer = keyword.toString();
                List<UserDO> committers = userService.searchByName(committer);
                if (!CollectionUtils.isEmpty(committers)) {
                    result = riskDAO.searchByCommitterIds(genUserIds(committers));
                }
                break;
            case IRiskService.SEARCH_BY_FOLLOWER:
                // 跟踪者，先模糊查找找到id，再根据id去找记录
                String follower = keyword.toString();
                List<UserDO> followers = userService.searchByName(follower);
                if (!CollectionUtils.isEmpty(followers)) {
                    result = riskDAO.searchByFollowerIds(genUserIds(followers));
                }
                break;
            case IRiskService.SEARCH_BY_IMPACT:
                riskQueryDO.setImpact(Integer.parseInt(keyword.toString()));
                result = riskDAO.search(riskQueryDO);
                break;
        }
        return result;
    }

    private List<Integer> genUserIds(List<UserDO> users) {
        List<Integer> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(users)) {
            return result;
        }
        for (UserDO user : users) {
            result.add(user.getId());
        }
        return result;
    }
}
