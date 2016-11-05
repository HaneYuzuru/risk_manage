package com.nju.risk.manage.dao;

import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.UserDO;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskDAO {
    public boolean insert(RiskDO riskDO);

    public int batchInsert(List<RiskDO> riskDOList);

    public boolean update(RiskDO riskDO);

    public int batchUpdate(List<RiskDO> riskDOList);

    public boolean delete(Integer id);

    public int batchDeleteByIdList(List<Integer> idList);

    public List<UserDO> selectByIdList(List<Integer> idList);
}
