package com.nju.risk.manage.dao;

import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackQueryDO;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskTrackDAO {
    public boolean insert(RiskTrackDO riskTrackDO);

    public int batchInsert(List<RiskTrackDO> riskTrackDOList);

    public boolean update(RiskTrackDO riskTrackDO);

    public int batchUpdate(List<RiskTrackDO> riskTrackDOList);

    public boolean delete(Integer id);

    public int batchDeleteByIdList(List<Integer> idList);

    public List<RiskTrackDO> selectByIdList(List<Integer> idList);

    public List<RiskTrackDO> selectByRiskIdList(List<Integer> riskIdList);

    public List<RiskTrackDO> searchByTime(RiskTrackQueryDO riskTrackQueryDO);
}
