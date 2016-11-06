package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskDO;

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


    public boolean addRiskItem(RiskDO riskDO);

    public boolean deleteRiskItem(int id);

    public boolean updateRiskItem(RiskDO riskDO);

    /**
     * 查找风险项目
     *
     * @param keyword 关键字
     * @param type    以什么来查
     * @param fuzzy   是否模糊查找
     * @return
     */
    public List<RiskDO> search(Object keyword, int type, boolean fuzzy);
}
