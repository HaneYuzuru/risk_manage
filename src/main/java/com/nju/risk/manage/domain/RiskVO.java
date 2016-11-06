package com.nju.risk.manage.domain;

/**
 * author: winsky
 * date: 2016/11/6
 * description:
 */
public class RiskVO extends BaseVO {
    private Integer id;
    /**
     * 风险名称
     */
    private String name;

    /**
     * 风险内容
     */
    private String content;

    /**
     * 可能性
     */
    private String possibility;

    /**
     * 影响度
     */
    private String impact;

    /**
     * 触发器/阈值
     */
    private String trigger;

    /**
     * 提交者姓名
     */
    private String committerName;

    public RiskVO() {
    }

    public RiskVO(String name, String content, String possibility, String impact, String trigger, String committerName, String followerNames) {
        this.name = name;
        this.content = content;
        this.possibility = possibility;
        this.impact = impact;
        this.trigger = trigger;
        this.committerName = committerName;
        this.followerNames = followerNames;
    }

    public RiskVO(Integer id, String name, String content, String possibility, String impact, String trigger, String committerName, String followerNames) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.possibility = possibility;
        this.impact = impact;
        this.trigger = trigger;
        this.committerName = committerName;
        this.followerNames = followerNames;
    }

    /**
     * 跟踪者姓名，多个跟踪者以,（英文下）相连
     */
    private String followerNames;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPossibility() {
        return possibility;
    }

    public void setPossibility(String possibility) {
        this.possibility = possibility;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getCommitterName() {
        return committerName;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public String getFollowerNames() {
        return followerNames;
    }

    public void setFollowerNames(String followerNames) {
        this.followerNames = followerNames;
    }
}
