package com.nju.risk.manage.domain;

/**
 * author: winsky
 * date: 2016/11/5
 * description:
 */
public class RiskQueryDO {
    /**
     * 是否模糊搜索
     */
    private boolean fuzzy = false;

    /**
     * 风险名称
     */
    private String name = null;

    /**
     * 风险内容
     */
    private String content = null;

    /**
     * 可能性
     */
    private Integer possibility = -1;

    /**
     * 影响度
     */
    private Integer impact = -1;

    /**
     * 触发器/阈值
     */
    private String trigger = null;

    /**
     * 提交者，映射到user表中的id字段
     */
    private String committer = null;

    /**
     * 跟踪者id，仅支持一个跟踪者搜索
     */
    private String followerName = null;

    /**
     * 起始时间 格式YYYY-MM-DD
     */
    private String start = null;

    /**
     * 结束时间 格式YYYY-MM-DD
     */
    private String end = null;

    public boolean getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(boolean fuzzy) {
        this.fuzzy = fuzzy;
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

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
