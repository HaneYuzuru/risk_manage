package com.nju.risk.manage.service;

import com.nju.risk.manage.domain.RiskVO;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.domain.domainEnum.UserTypeEnum;
import com.nju.risk.manage.util.BaseDaoTestConfiguration;
import com.nju.risk.manage.utils.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class RiskServiceTest extends BaseDaoTestConfiguration {
    @Autowired
    IRiskService riskService;
    @Autowired
    IUserService userService;

    @Test
    public void testAPI() {
        UserDO userDO = new UserDO();
        userDO.setPassword("12345a");
        userDO.setName("winsky");
        userDO.setUserType(UserTypeEnum.MANAGER.value());
        boolean ret = userService.register(userDO);
        Assert.assertTrue(ret);

        RiskVO riskVO = new RiskVO("风险", "风险内容", "高", "中", "阈值", "winsky", "winsky", RiskStatusEnum.RISK.type());
        int result = riskService.addRiskItem(riskVO);
        Assert.assertNotEquals(0, result);

        List<RiskVO> riskVOs = riskService.searchByTime("1999-01-01", "2999-01-01");
        Assert.assertNotEquals(0, riskVOs.size());

        RiskVO vo = riskVOs.get(0);
        int riskId = vo.getId();

        riskVO.setId(riskId);
        riskVO.setName("风险-修改");
        ret = riskService.updateRiskItem(riskVO);
        Assert.assertTrue(ret);

        List<RiskVO> vos = riskService.search("修改", IRiskService.SEARCH_BY_NAME, true);
        Assert.assertNotEquals(0, vos.size());

        RiskVO risk = riskService.getById(riskId);
        Assert.assertEquals("风险-修改", risk.getName());

        boolean delRet = riskService.deleteRiskItem(riskId);
        Assert.assertTrue(delRet);
    }

    @Test
    public void testDate(){
        String s= DateUtil.formatDate("yyyy-MM-dd HH:mm:ss z",new Date());
        System.out.println(s);
    }
}