package com.nju.risk.manage.domain;

/**
 * Created by zzj on 16/11/6.
 */
public class RiskTrackVO {
    private Integer id;

    /**
     * 风险id
     */
    private Integer riskId;

    /**
     * 风险状态,0-风险状态，1-问题状态，2-解决状态
     */
    private Integer status;

    /**
     * 风险描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRiskId() {
        return riskId;
    }

    public void setRiskId(Integer riskId) {
        this.riskId = riskId;
    }

    /**
     * 风险状态,0-风险状态，1-问题状态，2-解决状态
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
