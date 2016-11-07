package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskTrackService {
    public List<RiskTrackDO> getRiskByRiskId(int riskId);

    /**
     * 获取某个风险的状态，默认是风险状态
     *
     * @param riskId 风险编号
     * @return
     */
    public RiskStatusEnum getRiskStatus(int riskId);

    public boolean add(RiskTrackVO riskTrackVO);
}
