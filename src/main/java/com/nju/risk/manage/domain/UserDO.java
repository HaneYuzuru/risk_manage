package com.nju.risk.manage.domain;

/**
 * @author chenyina
 * @version V1.0
 * @Description:
 * @date 16/10/31
 */
public class UserDO extends BaseDO {
    //主键
    private Integer id;

    //用户名
    private String name;

    //用户密码
    private String password;

    //用户类型
    private Integer userType;

    public UserDO(){}

    public UserDO(String name, String password, Integer userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 用户类型,0-普通工程师，1-项目经理
     *
     * @return
     */
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
