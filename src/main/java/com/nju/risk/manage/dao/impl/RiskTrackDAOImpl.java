package com.nju.risk.manage.dao.impl;

import com.nju.risk.manage.dao.IRiskTrackDAO;
import com.nju.risk.manage.domain.RiskTrackDO;
import com.nju.risk.manage.domain.RiskTrackQueryDO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
@Repository
public class RiskTrackDAOImpl implements IRiskTrackDAO {
    private final static String BASE = "com.nju.risk.manage.dao.IRiskTrackDAO.";

    private final static String STATEMENT_INSERT = BASE + "insert";

    private final static String STATEMENT_BATCH_INSERT = BASE + "batchInsert";

    private final static String STATEMENT_UPDATE = BASE + "update";

    private final static String STATEMENT_BATCH_UPDATE = BASE + "batchUpdate";

    private final static String STATEMENT_DELETE = BASE + "delete";

    private final static String STATEMENT_BATCH_DELETE_BY_ID_LIST = BASE + "batchDeleteByIdList";

    private final static String STATEMENT_SELECT_BY_ID_LIST = BASE + "selectByIdList";

    private final static String STATEMENT_SELECT_BY_RISK_ID_LIST = BASE + "selectByRiskIdList";

    private final static String STATEMENT_SEARCH_BY_TIME = BASE + "searchByTime";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean insert(RiskTrackDO riskTrackDO) {
        int res = sqlSession.insert(STATEMENT_INSERT, riskTrackDO);
        return res > 0;
    }

    @Override
    public int batchInsert(List<RiskTrackDO> riskTrackDOList) {
        if (riskTrackDOList.isEmpty()) {
            return 0;
        }
        return sqlSession.insert(STATEMENT_BATCH_INSERT, riskTrackDOList);
    }

    @Override
    public boolean update(RiskTrackDO riskTrackDO) {
        int res = sqlSession.update(STATEMENT_UPDATE, riskTrackDO);
        return res > 0;
    }

    @Override
    public int batchUpdate(List<RiskTrackDO> riskTrackDOList) {
        if (riskTrackDOList.isEmpty()) {
            return 0;
        }
        return sqlSession.update(STATEMENT_BATCH_UPDATE, riskTrackDOList);
    }

    @Override
    public boolean delete(Integer id) {
        int res = sqlSession.delete(STATEMENT_DELETE, id);
        return res > 0;
    }

    @Override
    public int batchDeleteByIdList(List<Integer> idList) {
        if (idList.isEmpty()) {
            return 0;
        }
        return sqlSession.delete(STATEMENT_BATCH_DELETE_BY_ID_LIST, idList);
    }

    @Override
    public List<RiskTrackDO> selectByIdList(List<Integer> idList) {
        if (idList.isEmpty()) {
            return new ArrayList<>();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_ID_LIST, idList);
    }

    @Override
    public List<RiskTrackDO> selectByRiskIdList(List<Integer> idList) {
        if (idList.isEmpty()) {
            return new ArrayList<>();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_RISK_ID_LIST, idList);
    }

    @Override
    public List<RiskTrackDO> searchByTime(RiskTrackQueryDO riskTrackQueryDO) {
        return sqlSession.selectList(STATEMENT_SEARCH_BY_TIME, riskTrackQueryDO);
    }
}
