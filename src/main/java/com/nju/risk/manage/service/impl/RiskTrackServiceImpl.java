package com.nju.risk.manage.service.impl;

import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.service.IRiskTrackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.service.IRiskTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Created by zzj on 16/11/6.
 */
@Service
public class RiskTrackServiceImpl implements IRiskTrackService {

    private final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    IRiskTrackDAO riskTrackDAO;

    @Override
    public boolean addRiskTrackItem(RiskTrackDO riskTrackDO) { return riskTrackDAO.insert(riskTrackDO); }

    @Override
    public boolean deleteRiskTrackItem(int id) {
        return riskTrackDAO.delete(id);
    }

    @Override
    public boolean updateRiskTrackItem(RiskTrackDO riskTrackDO) {
        return riskTrackDAO.update(riskTrackDO);
    }

    public boolean deleteRiskTrackItem(List<Integer> ids){
        return riskTrackDAO.batchDeleteByIdList(ids) > 0;
    }

    public List<RiskTrackVO> searchByRiskId(int id){
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(id);
        List<RiskTrackDO> obtained = riskTrackDAO.selectByIdList(ids);
        List<RiskTrackVO> result = new ArrayList<RiskTrackVO>();
        for (RiskTrackDO trackDO: obtained) {
            result.add(do2vo(trackDO));
        }

        return result;
    }

    private RiskTrackVO do2vo(RiskTrackDO DO) {
        RiskTrackVO VO = new RiskTrackVO();
        VO.setDescription(DO.getDescription());
        VO.setId(DO.getId());
        VO.setRiskId(DO.getRiskId());
        VO.setStatus(RiskStatusEnum.fromValue(DO.getStatus()).type());

        return VO;
    }

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
