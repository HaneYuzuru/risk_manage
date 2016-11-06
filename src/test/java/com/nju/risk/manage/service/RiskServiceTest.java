package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class RiskServiceTest extends BaseDaoTestConfiguration {
    @Autowired
    IRiskService riskService;

    @Test
    public void testSearch() {
        String name = "na";
        List<RiskDO> risks = riskService.search(name, IRiskService.SEARCH_BY_COMMITTER, true);
        System.out.println(risks);
    }
}
