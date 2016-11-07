package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.*;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.service.IRiskTrackService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzj on 16/11/6.
 */
@Service
public class RiskTrackServiceImpl implements IRiskTrackService {

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    IRiskTrackDAO riskTrackDAO;

    @Override
    public boolean addRiskTrackItem(RiskTrackVO riskTrackVO) {
        return riskTrackDAO.insert(vo2do(riskTrackVO));
    }

    @Override
    public boolean deleteRiskTrackItem(int id) {
        return riskTrackDAO.delete(id);
    }

    @Override
    public boolean updateRiskTrackItem(RiskTrackVO riskTrackVO) {
        return riskTrackDAO.update(vo2do(riskTrackVO));
    }

    public boolean deleteRiskTrackItem(List<Integer> ids) {
        return riskTrackDAO.batchDeleteByIdList(ids) > 0;
    }

    public List<RiskTrackVO> searchByRiskId(int id) {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(id);
        List<RiskTrackDO> obtained = riskTrackDAO.selectByIdList(ids);
        List<RiskTrackVO> result = new ArrayList<RiskTrackVO>();
        for (RiskTrackDO trackDO : obtained) {
            result.add(do2vo(trackDO));
        }

        return result;
    }

    @Override
    public List<RiskTrackVO> searchByTime(String start, String end) {
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            return Lists.newArrayList();
        }

        start = start + " 00:00:00";
        end = end + " 23:59:59";

        RiskTrackQueryDO riskTrackQueryDO = new RiskTrackQueryDO();
        riskTrackQueryDO.setStart(start);
        riskTrackQueryDO.setEnd(end);

        List<RiskTrackDO> riskTrackDOs = riskTrackDAO.searchByTime(riskTrackQueryDO);
        if (CollectionUtils.isEmpty(riskTrackDOs)) {
            return Lists.newArrayList();
        }
        List<RiskTrackVO> riskTrackVOs = Lists.newArrayList();
        for (RiskTrackDO riskTrackDO : riskTrackDOs) {
            riskTrackVOs.add(do2vo(riskTrackDO));
        }
        return riskTrackVOs;
    }

    public List<RiskTrackDO> getRiskByRiskId(int riskId) {
        return riskTrackDAO.selectByRiskIdList(Lists.newArrayList(riskId));
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

    private RiskTrackVO do2vo(RiskTrackDO riskTrackDO) {
        if (riskTrackDO == null) {
            return null;
        }

        RiskTrackVO riskTrackVO = new RiskTrackVO();
        riskTrackVO.setId(riskTrackDO.getId());
        riskTrackVO.setStatus(RiskStatusEnum.fromValue(riskTrackDO.getStatus()).type());
        riskTrackVO.setDescription(riskTrackDO.getDescription());
        riskTrackVO.setRiskId(riskTrackDO.getRiskId());

        return riskTrackVO;
    }
}
