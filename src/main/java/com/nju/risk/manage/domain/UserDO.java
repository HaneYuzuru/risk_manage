package com.nju.risk.manage.domain;

import java.util.Date;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/10/31
 */
public class UserDO {
    //主键
    private Integer id;

    //用户id
    private Integer userId;

    //用户密码
    private String password;

    //用户类型
    private Integer userType;

    //用户状态
    private Integer userSwitch;

    //用户组id
    private Integer groupId;

    //创建时间
    private Date gmtCreate;

    //修改时间
    private Date gmtModified;

    //数据有效性
    private Integer dataStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserSwitch() {
        return userSwitch;
    }

    public void setUserSwitch(Integer userSwitch) {
        this.userSwitch = userSwitch;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
