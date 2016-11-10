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

import java.text.SimpleDateFormat;
import java.util.*;

import static com.nju.risk.manage.utils.DateUtil.formatDate;

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
    public void testDate() {
        String s = formatDate("yyyy-MM-dd HH:mm:ss z", new Date());
        System.out.println(s);
    }

    @Test
    public void testTimeZone() throws Exception {
        SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("foo:" + foo.format(new Date()));

        Date date= new Date(1478720028);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Calendar gc = Calendar.getInstance();

        gc.setTime(date);
        String str = DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", gc.getTime());
        System.out.println(str);

        System.out.println("-------------------------------------------");


        System.out.println("gc.getTime():" + gc.getTime());
        System.out.println("gc.getTimeInMillis():" + new Date(gc.getTimeInMillis()));

        // 当前系统默认时区的时间：
        Calendar calendar = new GregorianCalendar();
        System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
        System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        // 美国洛杉矶时区
        TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
        // 时区转换
        calendar.setTimeZone(tz);
        System.out.print("时区：" + calendar.getTimeZone().getID() + "  ");
        System.out.println("时间：" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        Date time = new Date();

        // 1、取得本地时间：
        java.util.Calendar cal = java.util.Calendar.getInstance();

        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        // 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
        System.out.println("UTC:" + new Date(cal.getTimeInMillis()));

        Calendar calendar1 = Calendar.getInstance();
        TimeZone tztz = TimeZone.getTimeZone("GMT");
        calendar1.setTimeZone(tztz);
        System.out.println(calendar.getTime());
        System.out.println(calendar.getTimeInMillis());

        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // df.setTimeZone(TimeZone.getTimeZone("UTC"));
        // System.out.println(df.parse("2014-08-23T09:20:05Z").toString());

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date t = new Date();
        System.out.println(df1.format(t));
        System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(df1.format(t));
        System.out.println("-----------");
        System.out.println(df1.format(df1.parse("2014-08-27T18:02:59.676Z")) + "***********");
        System.out.println("2014-08-27T18:02:59.676Z");
    }
}