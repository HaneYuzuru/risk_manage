package com.nju.risk.manage.dao.impl;

import com.google.common.collect.Lists;
import com.nju.risk.manage.dao.IUserDAO;
import com.nju.risk.manage.domain.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/11/1
 */
@Repository
public class UserDAOImpl implements IUserDAO {
    private final static String BASE = "com.nju.risk.manage.dao.IUserDAO.";

    private final static String STATEMENT_INSERT = BASE + "insert";

    private final static String STATEMENT_BATCH_INSERT = BASE + "batchInsert";

    private final static String STATEMENT_UPDATE = BASE + "update";

    private final static String STATEMENT_BATCH_UPDATE = BASE + "batchUpdate";

    private final static String STATEMENT_DELETE = BASE + "delete";

    private final static String STATEMENT_BATCH_DELETE_BY_ID_LIST = BASE + "batchDeleteByIdList";

    private final static String STATEMENT_SELECT_BY_ID_LIST = BASE + "selectByIdList";

    private final static String STATEMENT_SELECT_BY_NAME = BASE + "selectByName";

    private final static String STATEMENT_SEARCH_BY_NAME = BASE + "searchByName";

    private final static Integer PAGE_SIZE = 20;

    @Autowired
    private SqlSession sqlSession;

    @Override
    public boolean insert(UserDO userDO) {
        int res = sqlSession.insert(STATEMENT_INSERT, userDO);
        return res > 0;
    }

    @Override
    public int batchInsert(List<UserDO> userDOList) {
        if (userDOList.isEmpty()) {
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
        if (userDOList.isEmpty()) {
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
        if (idList.isEmpty()) {
            return 0;
        }
        return sqlSession.delete(STATEMENT_BATCH_DELETE_BY_ID_LIST, idList);
    }

    @Override
    public List<UserDO> selectByIdList(List<Integer> idList) {
        if (idList.isEmpty()) {
            return new ArrayList<>();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_ID_LIST, idList);
    }

    @Override
    public List<UserDO> selectByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Lists.newArrayList();
        }
        return sqlSession.selectList(STATEMENT_SELECT_BY_NAME, name);
    }

    @Override
    public UserDO login(String name, String password) {
        List<UserDO> users = selectByName(name);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        try {
            UserDO user = users.get(0);
            String expect = user.getPassword();
            if (expect.equals(password)) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserDO> searchByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Lists.newArrayList();
        }
        return sqlSession.selectList(STATEMENT_SEARCH_BY_NAME, name);
    }
}
