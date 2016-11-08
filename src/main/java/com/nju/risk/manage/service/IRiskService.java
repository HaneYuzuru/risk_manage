package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskVO;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public interface IRiskService {
    public static final int SEARCH_BY_NAME = 0;
    public static final int SEARCH_BY_CONTENT = 1;
    public static final int SEARCH_BY_POSSIBILITY = 2;
    public static final int SEARCH_BY_IMPACT = 3;
    public static final int SEARCH_BY_COMMITTER = 4;
    public static final int SEARCH_BY_FOLLOWER = 5;


    /**
     * 新增风险
     *
     * @param riskVO
     * @return 0 增加失败，>0 新增成功，为新增对象的自增id
     */
    public int addRiskItem(RiskVO riskVO);

    public boolean deleteRiskItem(int id);

    public boolean deleteRiskItem(List<Integer> ids);

    public boolean updateRiskItem(RiskVO riskVO);

    /**
     * 查找风险项目
     *
     * @param keyword 关键字
     * @param type    以什么来查
     * @param fuzzy   是否模糊查找
     * @return
     */
    public List<RiskVO> search(Object keyword, int type, boolean fuzzy);

    /**
     * 获取某个时间段的风险
     *
     * @param start 起始时间 格式YYYY-MM-DD
     * @param end   结束时间 格式YYYY-MM-DD
     * @return
     */
    public List<RiskVO> searchByTime(String start, String end);
}
