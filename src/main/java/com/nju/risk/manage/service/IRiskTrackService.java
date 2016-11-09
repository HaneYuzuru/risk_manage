package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskTrackVO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskTrackService {
    public boolean addRiskTrackItem(RiskTrackVO riskTrackVO);

    public boolean deleteRiskTrackItem(int id);

    public boolean deleteRiskTrackItem(List<Integer> ids);

    public boolean updateRiskTrackItem(RiskTrackVO riskTrackVO);

    public List<RiskTrackVO> searchByRiskId(int id);

    /**
     * 获取某个时间段的风险
     *
     * @param start 起始时间 格式YYYY-MM-DD
     * @param end   结束时间 格式YYYY-MM-DD
     * @return
     */
    public List<RiskTrackVO> searchByTime(String start, String end);

    /**
     * 获取某个风险的状态，默认是风险状态
     *
     * @param riskId 风险编号
     * @return
     */
    public RiskStatusEnum getRiskStatus(int riskId);

    /**
     * 获取某个时间段的某个风险全部跟踪情况，以创建时间排序
     *
     * @param riskId 风险id
     * @param start  开始时间
     * @param end    结束时间
     * @return
     */
    public List<RiskTrackVO> searchByTime(int riskId, String start, String end);
}
