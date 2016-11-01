package com.nju.risk.manage.dao.impl;

import com.nju.risk.manage.dao.IUserDAO;
import com.nju.risk.manage.domain.UserDO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Repository
public class UserDAOImpl implements IUserDAO{

    private final static String BASE = "com.nju.risk.manage.dao.IUserDAO.";

    private final static String STATEMENT_INSERT = BASE + "insert";

    private final static String STATEMENT_BATCH_INSERT = BASE + "batchInsert";

    private final static String STATEMENT_UPDATE = BASE + "update";

    private final static String STATEMENT_BATCH_UPDATE = BASE + "batchUpdate";

    private final static String STATEMENT_DELETE = BASE + "delete";

    private final static String STATEMENT_BATCH_DELETE_BY_ID_LIST = BASE + "batchDeleteByIdList";

    private final static String STATEMENT_BATCH_DELETE_BY_USER_ID_LIST = BASE + "batchDeleteByUserIdList";

    private final static String STATEMENT_SELECT_BY_USER_ID_LIST = BASE + "selectByUserIdList";

    private final static Integer PAGESIZE =1000;

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean insert(UserDO userDO) {
        int res = sqlSession.insert(STATEMENT_INSERT, userDO);
        return res > 0;
    }

    @Override
    public int batchInsert(List<UserDO> userDOList) {
        if(userDOList.isEmpty()){
            return 0;
        }
        return sqlSession.insert(STATEMENT_BATCH_INSERT, userDOList);
    }

    @Override
    public boolean update(UserDO userDO) {
        int res = sqlSession.update(STATEMENT_UPDATE, userDO);
        return res > 0;
    }

    @Override
    public int batchUpdate(List<UserDO> userDOList) {
        if(userDOList.isEmpty()){
            return 0;
        }
        return sqlSession.update(STATEMENT_BATCH_UPDATE, userDOList);
    }

    @Override
    public boolean delete(Integer userId) {
        int res = sqlSession.delete(STATEMENT_DELETE, userId);
        return res > 0;
    }

    @Override
    public int batchDeleteByIdList(List<Integer> idList) {
        if(idList.isEmpty()){
            return 0;
        }
        return sqlSession.delete(STATEMENT_BATCH_DELETE_BY_ID_LIST, idList);
    }

    @Override
    public int batchDeleteByUserIdList(List<Integer> userIdList) {
        if(userIdList.isEmpty()){
            return 0;
        }
        return sqlSession.delete(STATEMENT_BATCH_DELETE_BY_USER_ID_LIST, userIdList);
    }

    @Override
    public List<UserDO> selectByUserIdList(List<Integer> userIdList) {
        if(userIdList.isEmpty()){
            return new ArrayList<>();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_USER_ID_LIST, userIdList);
    }
}
