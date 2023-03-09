package com.jymj.zhglxt.ui.bean.homepage;



import java.io.Serializable;
import java.util.Date;

/**
 * 卡规则
 *
 * @author J.Tang
 * @version 1.0
 * @email seven_tjb@163.com
 * @date 2022-09-09
 */
public class EffectiveRulesInfo implements Serializable {

    //(value = "规则id")
    private Long rulesId;

    //(value = "生效规则")
    private EffectiveRulesEnum effectiveRules;

    //(value = "几小时后生效")
    private Integer hoursLater;

    //("使用规则")
    private UsageRulesEnum usageRule;

    //("几天后内可用")
    private Integer days;

    //("可用开始时间")
    private String startDate;

    //("可用结束时间")
    private String endDate;

    public Long getRulesId() {
        return rulesId;
    }

    public void setRulesId(Long rulesId) {
        this.rulesId = rulesId;
    }

    public EffectiveRulesEnum getEffectiveRules() {
        return effectiveRules;
    }

    public void setEffectiveRules(EffectiveRulesEnum effectiveRules) {
        this.effectiveRules = effectiveRules;
    }

    public Integer getHoursLater() {
        return hoursLater;
    }

    public void setHoursLater(Integer hoursLater) {
        this.hoursLater = hoursLater;
    }

    public UsageRulesEnum getUsageRule() {
        return usageRule;
    }

    public void setUsageRule(UsageRulesEnum usageRule) {
        this.usageRule = usageRule;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
