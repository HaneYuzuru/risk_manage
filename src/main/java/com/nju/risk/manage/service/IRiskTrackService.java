package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackVO;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskTrackService {
    public boolean addRiskTrackItem(RiskTrackDO riskTrackDO);

    public boolean deleteRiskTrackItem(int id);

    public boolean deleteRiskTrackItem(List<Integer> ids);

    public boolean updateRiskTrackItem(RiskTrackDO riskTrackDO);

    public List<RiskTrackVO> searchByRiskId(int id);

    /**
     * 获取某个时间段的风险
     *
     * @param start 起始时间 格式YYYY-MM-DD
     * @param end   结束时间 格式YYYY-MM-DD
     * @return
     */
    // public List<RiskTrackVO> searchByTime(String start, String end);
}
