package com.nju.risk.manage.dao;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.RiskQueryDO;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public class RiskDAOTest extends BaseDaoTestConfiguration {
    @Autowired
    private IRiskDAO riskDAO;

    @Test
    public void insertTest() throws Exception {
        RiskDO riskDO = new RiskDO();
        riskDO.setName("风险");
        riskDO.setContent("这个风险很大的");
        riskDO.setPossibility(0);
        riskDO.setImpact(0);
        riskDO.setTrigger("超过一天就要报警了");
        riskDO.setCommitter(1);
        riskDO.setFollowers("2,3");
        riskDO.setDataStatus(1);
        int ret = riskDAO.insert(riskDO);
        Assert.assertNotEquals(0,ret);
        List<RiskDO> resultList = riskDAO.selectByIdList(Lists.newArrayList(1));
        Assert.assertTrue(resultList.size() > 0);
    }

    @Test
    public void batchTest() throws Exception {
        RiskDO riskDO = new RiskDO();
        riskDO.setName("风险");
        riskDO.setContent("这个风险很大的");
        riskDO.setPossibility(0);
        riskDO.setImpact(0);
        riskDO.setTrigger("超过一天就要报警了");
        riskDO.setCommitter(1);
        riskDO.setFollowers("2,3");
        riskDO.setDataStatus(1);

        RiskDO riskDO2 = new RiskDO();
        riskDO2.setName("风险");
        riskDO2.setContent("这个风险很大的");
        riskDO2.setPossibility(0);
        riskDO2.setImpact(0);
        riskDO2.setTrigger("超过一天就要报警了");
        riskDO2.setCommitter(1);
        riskDO2.setFollowers("2,3");
        riskDO2.setDataStatus(1);


        int ret = riskDAO.batchInsert(Lists.newArrayList(riskDO, riskDO2));
        Assert.assertEquals(2, ret);
        int changeNum = riskDAO.batchUpdate(Lists.newArrayList(riskDO, riskDO2));
        List<RiskDO> resultList = riskDAO.selectByIdList(Lists.newArrayList(2, 3));
        Assert.assertEquals(2, resultList.size());
        Assert.assertEquals(0, changeNum);
        int deleteNum = riskDAO.batchDeleteByIdList(Lists.newArrayList(2, 3));
        Assert.assertEquals(2, deleteNum);
    }

    @Test
    public void testSearch() {
        String keyword = "风";
        RiskQueryDO riskQueryDO = new RiskQueryDO();
        riskQueryDO.setFuzzy(true);
        riskQueryDO.setName(keyword);
        List<RiskDO> result = riskDAO.search(riskQueryDO);
        System.out.println(result.size());
    }
}
