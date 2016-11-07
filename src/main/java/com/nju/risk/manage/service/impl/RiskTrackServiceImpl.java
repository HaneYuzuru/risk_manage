package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.service.IRiskTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/7
 * description:
 */
@Service
public class RiskTrackServiceImpl implements IRiskTrackService {
    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    IRiskTrackDAO riskTrackDAO;

    @Override
    public List<RiskTrackDO> getRiskByRiskId(int riskId) {
        // return riskTrackDAO.searchByRiskId(Lists.newArrayList(riskId));
        return Lists.newArrayList();
    }

    @Override
    public RiskStatusEnum getRiskStatus(int riskId) {
        List<RiskTrackDO> riskTrackDOs = getRiskByRiskId(riskId);

        if (CollectionUtils.isEmpty(riskTrackDOs)) {
            //默认是风险状态
            return RiskStatusEnum.RISK;
        }

        int status = RiskStatusEnum.RISK.value();
        int riskTrackId = 0;
        for (int i = riskTrackDOs.size() - 1; i >= 0; i--) {
            RiskTrackDO riskTrackDO = riskTrackDOs.get(i);
            int curId = riskTrackDO.getId();
            if (curId > riskTrackId) {
                riskTrackId = curId;
                status = riskTrackDO.getStatus();
            }
        }
        return RiskStatusEnum.fromValue(status);
    }

    @Override
    public boolean add(RiskTrackVO riskTrackVO) {
        if (riskTrackVO == null) {
            return false;
        }
        return riskTrackDAO.insert(vo2do(riskTrackVO));
    }

    private RiskTrackDO vo2do(RiskTrackVO riskTrackVO) {
        if (riskTrackVO == null) {
            return null;
        }

        RiskTrackDO riskTrackDO = new RiskTrackDO();
        riskTrackDO.setId(riskTrackVO.getId());
        riskTrackDO.setDescription(riskTrackVO.getDescription());
        riskTrackDO.setStatus(RiskStatusEnum.fromType(riskTrackVO.getStatus()).value());
        riskTrackDO.setRiskId(riskTrackVO.getRiskId());
        return riskTrackDO;
    }
}
