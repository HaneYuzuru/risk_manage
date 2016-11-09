package com.nju.risk.manage.domain;

/**
 * author: winsky
 * date: 2016/11/7
 * description:
 */
public class RiskTrackQueryDO {
    private Integer riskId = null;

    private String start = null;

    private String end = null;

    public Integer getRiskId() {
        return riskId;
    }

    public void setRiskId(Integer riskId) {
        this.riskId = riskId;
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
