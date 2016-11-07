package com.nju.risk.manage.service.impl;

import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.service.IRiskTrackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzj on 16/11/6.
 */
public class RiskTrackServiceImpl implements IRiskTrackService {
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

    private RiskTrackVO do2vo(RiskTrackDO DO){
        RiskTrackVO VO = new RiskTrackVO();
        VO.setDescription(DO.getDescription());
        VO.setId(DO.getId());
        VO.setRiskId(DO.getRiskId());
        VO.setStatus(DO.getStatus());

        return VO;
    }
}
