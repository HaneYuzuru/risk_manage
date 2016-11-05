package com.nju.risk.manage.domain;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public class RiskDO extends BaseDO {
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
    private Integer possibility;

    /**
     * 影响度
     */
    private Integer impact;

    /**
     * 触发器/阈值
     */
    private String trigger;

    /**
     * 提交者，映射到user表中的id字段
     */
    private Integer committer;

    /**
     * 跟踪者id，多个跟踪者以,（英文下）相连
     */
    private String followers;

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

    /**
     * 可能性，0-低，1-中，2-高
     *
     * @return
     */
    public Integer getPossibility() {
        return possibility;
    }

    public void setPossibility(Integer possibility) {
        this.possibility = possibility;
    }

    /**
     * 影响程度，0-低，1-中，2-高
     *
     * @return
     */
    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public Integer getCommitter() {
        return committer;
    }

    public void setCommitter(Integer committer) {
        this.committer = committer;
    }

    /**
     * 跟踪者id，多个跟踪者以,（英文下）相连
     *
     * @return
     */
    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }
}
