package com.nju.risk.manage.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class BaseVO {
    private String gmtCreate;
    private String gmtModified;
    //数据有效性
    private Integer dataStatus = 0;

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 数据有效性，0-有效，1-已删除
     *
     * @return
     */
    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
