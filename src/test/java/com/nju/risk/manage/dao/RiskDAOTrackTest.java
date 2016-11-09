package com.nju.risk.manage.dao;

import com.google.common.collect.Lists;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public class RiskDAOTrackTest extends BaseDaoTestConfiguration {
    @Autowired
    private IRiskTrackDAO trackDAO;

    @Test
    public void insertTest() throws Exception {
        RiskTrackDO riskTrackDO = new RiskTrackDO();
        riskTrackDO.setDescription("描述");
        riskTrackDO.setRiskId(1);
        riskTrackDO.setStatus(1);
        riskTrackDO.setDataStatus(1);
        boolean ret = trackDAO.insert(riskTrackDO);
        Assert.assertTrue(ret);
    }

    @Test
    public void batchTest() throws Exception{
        RiskTrackDO riskTrackDO = new RiskTrackDO();
        riskTrackDO.setDescription("描述");
        riskTrackDO.setRiskId(1);
        riskTrackDO.setStatus(1);
        riskTrackDO.setDataStatus(1);

        RiskTrackDO riskTrackDO2 = new RiskTrackDO();
        riskTrackDO2.setDescription("描述");
        riskTrackDO2.setRiskId(1);
        riskTrackDO2.setStatus(1);
        riskTrackDO2.setDataStatus(1);


        int ret = trackDAO.batchInsert(Lists.newArrayList(riskTrackDO, riskTrackDO2));
        Assert.assertEquals(2, ret);
        int changeNum = trackDAO.batchUpdate(Lists.newArrayList(riskTrackDO, riskTrackDO2));
        Assert.assertEquals(0, changeNum);
    }
}
