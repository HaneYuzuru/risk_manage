package com.nju.risk.manage.service.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IRiskDAO;
import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.RiskQueryDO;
import com.nju.risk.manage.domain.RiskVO;
import com.nju.risk.manage.domain.UserDO;
import com.nju.risk.manage.domain.domainEnum.ImpactEnum;
import com.nju.risk.manage.domain.domainEnum.PossibilityEnum;
import com.nju.risk.manage.domain.domainEnum.RiskStatusEnum;
import com.nju.risk.manage.service.IRiskService;
import com.nju.risk.manage.service.IRiskTrackService;
import com.nju.risk.manage.service.IUserService;
import com.nju.risk.manage.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
@Service
public class RiskServiceImpl implements IRiskService {
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    IRiskDAO riskDAO;
    @Autowired
    IUserService userService;
    @Autowired
    IRiskTrackService riskTrackService;

    @Override
    public boolean addRiskItem(RiskVO riskVO) {
        RiskDO riskDO = vo2Do(riskVO);
        if (riskDO == null) {
            return false;
        }
        return riskDAO.insert(riskDO);
    }

    @Override
    public boolean deleteRiskItem(int id) {
        return riskDAO.delete(id);
    }

    @Override
    public boolean deleteRiskItem(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        for (Integer id : ids) {
            deleteRiskItem(id);
        }
        return true;
    }

    @Override
    public boolean updateRiskItem(RiskVO riskVO) {
        RiskDO riskDO = vo2Do(riskVO);
        if (riskDO == null) {
            return false;
        }
        return riskDAO.update(riskDO);
    }

    @Override
    public List<RiskVO> search(Object keyword, int type, boolean fuzzy) {
        List<RiskVO> result = Lists.newArrayList();
        if (keyword == null) {
            return result;
        }

        List<RiskDO> riskDOs = Lists.newArrayList();
        RiskQueryDO riskQueryDO = new RiskQueryDO();
        riskQueryDO.setFuzzy(fuzzy);
        switch (type) {
            case IRiskService.SEARCH_BY_NAME:
                riskQueryDO.setName(String.valueOf(keyword));
                riskDOs = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_CONTENT:
                riskQueryDO.setContent(String.valueOf(keyword));
                riskDOs = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_POSSIBILITY:
                riskQueryDO.setPossibility(Integer.parseInt(keyword.toString()));
                riskDOs = riskDAO.search(riskQueryDO);
                break;
            case IRiskService.SEARCH_BY_COMMITTER:
                // 提交者，先模糊查找找到id，再根据id去找记录
                String committer = keyword.toString();
                List<UserDO> committers = userService.searchByName(committer);
                if (!CollectionUtils.isEmpty(committers)) {
                    riskDOs = riskDAO.searchByCommitterIds(genUserIds(committers));
                }
                break;
            case IRiskService.SEARCH_BY_FOLLOWER:
                // 跟踪者，先模糊查找找到id，再根据id去找记录
                String follower = keyword.toString();
                List<UserDO> followers = userService.searchByName(follower);
                if (!CollectionUtils.isEmpty(followers)) {
                    riskDOs = riskDAO.searchByFollowerIds(genUserIds(followers));
                }
                break;
            case IRiskService.SEARCH_BY_IMPACT:
                riskQueryDO.setImpact(Integer.parseInt(keyword.toString()));
                riskDOs = riskDAO.search(riskQueryDO);
                break;
        }

        if (!CollectionUtils.isEmpty(riskDOs)) {
            for (RiskDO riskDO : riskDOs) {
                result.add(do2vo(riskDO));
            }
        }
        return result;
    }

    @Override
    public List<RiskVO> searchByTime(String start, String end) {
        if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end)) {
            return Lists.newArrayList();
        }

        start = start + " 00:00:00";
        end = end + " 23:59:59";

        RiskQueryDO riskQueryDO = new RiskQueryDO();
        riskQueryDO.setStart(start);
        riskQueryDO.setEnd(end);

        List<RiskDO> riskDOs = riskDAO.searchByTime(riskQueryDO);
        if (CollectionUtils.isEmpty(riskDOs)) {
            return Lists.newArrayList();
        }
        List<RiskVO> riskVOs = Lists.newArrayList();
        for (RiskDO riskDO : riskDOs) {
            riskVOs.add(do2vo(riskDO));
        }
        return riskVOs;
    }

    private List<Integer> genUserIds(List<UserDO> users) {
        List<Integer> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(users)) {
            return result;
        }
        for (UserDO user : users) {
            result.add(user.getId());
        }
        return result;
    }

    private RiskVO do2vo(RiskDO risk) {
        if (risk == null) {
            return null;
        }
        RiskVO riskVO = new RiskVO();
        riskVO.setGmtCreate(DateUtil.formatDate(DATE_FORMAT, risk.getGmtCreate()));
        riskVO.setGmtModified(DateUtil.formatDate(DATE_FORMAT, risk.getGmtModified()));
        riskVO.setDataStatus(risk.getDataStatus());
        riskVO.setId(risk.getId());
        riskVO.setName(risk.getName());
        riskVO.setPossibility(PossibilityEnum.fromValue(risk.getPossibility()).type());
        riskVO.setImpact(ImpactEnum.fromValue(risk.getImpact()).type());
        riskVO.setContent(risk.getContent());
        riskVO.setTrigger(risk.getTrigger());

        int committerId = risk.getCommitter();
        String committer = getNameByUserId(String.valueOf(committerId));
        riskVO.setCommitterName(committer);
        String followers = risk.getFollowers();
        riskVO.setFollowerNames(getNameByUserId(followers));

        //设置风险当前的状态
        RiskStatusEnum riskStatus = riskTrackService.getRiskStatus(risk.getId());
        riskVO.setStatus(riskStatus.type());

        return riskVO;
    }


    private RiskDO vo2Do(RiskVO risk) {
        if (risk == null) {
            return null;
        }
        RiskDO riskDO = new RiskDO();
        riskDO.setDataStatus(risk.getDataStatus());
        riskDO.setId(risk.getId());
        riskDO.setName(risk.getName());
        riskDO.setPossibility(PossibilityEnum.fromType(risk.getPossibility()).value());
        riskDO.setImpact(ImpactEnum.fromType(risk.getImpact()).value());
        riskDO.setContent(risk.getContent());
        riskDO.setTrigger(risk.getTrigger());

        String committerName = risk.getCommitterName();
        UserDO committer = userService.getUserByName(committerName);
        if (committer != null) {
            riskDO.setCommitter(committer.getId());
        }
        String followersName = risk.getFollowerNames();
        riskDO.setFollowers(getUserIdByName(followersName));

        return riskDO;
    }

    /**
     * 根据id获得用户名，若有多个，id和用户名均以英文,分隔
     *
     * @param id
     * @return
     */
    private String getNameByUserId(String id) {
        if (StringUtils.isEmpty(id)) {
            return StringUtils.EMPTY;
        }

        String result = StringUtils.EMPTY;
        String[] idStr = id.split(",");
        for (int i = 0; i < idStr.length; i++) {
            int idInt = Integer.parseInt(idStr[i]);
            UserDO user = userService.getUserById(idInt);
            if (user != null) {
                result = result + "," + user.getName();
            }
        }

        if (result.length() > 1) {
            return result.substring(1);
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 根据用户名获得id，若有多个，id和用户名均以英文,分隔
     *
     * @param name
     * @return
     */
    private String getUserIdByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return StringUtils.EMPTY;
        }

        String[] names = name.split(",");
        String result = StringUtils.EMPTY;

        for (int i = 0; i < names.length; i++) {
            UserDO user = userService.getUserByName(names[i]);
            if (user != null) {
                result = result + "," + user.getId();
            }
        }

        if (result.length() > 1) {
            return result.substring(1);
        } else {
            return StringUtils.EMPTY;
        }

    }
}
