package com.nju.risk.manage.dao.impl;

import com.nju.risk.manage.dao.IRiskDAO;
import com.nju.risk.manage.domain.RiskDO;
import com.nju.risk.manage.domain.RiskQueryDO;
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
public class RiskDAOImpl implements IRiskDAO {
    private final static String BASE = "com.nju.risk.manage.dao.IRiskDAO.";

    private final static String STATEMENT_INSERT = BASE + "insert";

    private final static String STATEMENT_BATCH_INSERT = BASE + "batchInsert";

    private final static String STATEMENT_UPDATE = BASE + "update";

    private final static String STATEMENT_BATCH_UPDATE = BASE + "batchUpdate";

    private final static String STATEMENT_DELETE = BASE + "delete";

    private final static String STATEMENT_BATCH_DELETE_BY_ID_LIST = BASE + "batchDeleteByIdList";

    private final static String STATEMENT_SELECT_BY_ID_LIST = BASE + "selectByIdList";

    private final static String STATEMENT_SEARCH = BASE + "search";

    private final static String STATEMENT_SEARCH_BY_COMMITTER = BASE + "searchByCommitterIds";

    private final static String STATEMENT_SEARCH_BY_FOLLOWER = BASE + "searchByFollowerIds";

    private final static String STATEMENT_SEARCH_BY_TIME = BASE + "searchByTime";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public int insert(RiskDO riskDO) {
        int res = sqlSession.insert(STATEMENT_INSERT, riskDO);
        if (res > 0) {
            return riskDO.getId();
        } else {
            return 0;
        }
    }

    @Override
    public int batchInsert(List<RiskDO> riskDOList) {
        if (riskDOList.isEmpty()) {
            return 0;
        }
        return sqlSession.insert(STATEMENT_BATCH_INSERT, riskDOList);
    }

    @Override
    public boolean update(RiskDO riskDO) {
        int res = sqlSession.update(STATEMENT_UPDATE, riskDO);
        return res > 0;
    }

    @Override
    public int batchUpdate(List<RiskDO> riskDOList) {
        if (riskDOList.isEmpty()) {
            return 0;
        }
        return sqlSession.update(STATEMENT_BATCH_UPDATE, riskDOList);
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
    public List<RiskDO> selectByIdList(List<Integer> idList) {
        if (idList.isEmpty()) {
            return new ArrayList<>();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_ID_LIST, idList);
    }

    @Override
    public List<RiskDO> search(RiskQueryDO riskQueryDO) {
        return sqlSession.selectList(STATEMENT_SEARCH, riskQueryDO);
    }

    @Override
    public List<RiskDO> searchByCommitterIds(List<Integer> ids) {
        return sqlSession.selectList(STATEMENT_SEARCH_BY_COMMITTER, ids);
    }

    @Override
    public List<RiskDO> searchByFollowerIds(List<Integer> ids) {
        return sqlSession.selectList(STATEMENT_SEARCH_BY_FOLLOWER, ids);
    }

    @Override
    public List<RiskDO> searchByTime(RiskQueryDO riskQueryDO) {
        return sqlSession.selectList(STATEMENT_SEARCH_BY_TIME, riskQueryDO);
    }
}
