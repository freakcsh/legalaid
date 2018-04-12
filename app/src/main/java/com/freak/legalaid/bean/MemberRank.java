package com.freak.legalaid.bean;

import java.io.Serializable;

/**
 * 会员等级
 * <p>
 * Created by hboxs020 on 2018/1/10.
 */

public class MemberRank implements Serializable {
    //	等级折扣
    private int discount;
    //	等级id
    private Long id;
    //	是否启用
    private Boolean isEnable;
    //	等级1~10
    private int level;
    //	图片链接
    private String logo;
    //	赠送积分
    private String presentPoint;
    //	等级名称
    private String rankName;
    //	触发赠送积分的金额	number
    private String triggerPrice;
    //触发升级消费的金额
    private String consumePromotePrice;
    //是否开通消费升级
    private Boolean isConsumePromote;
    //是否开通充值升级功能
    private Boolean isRechargePromote;
    //充值升级触发金额
    private String rechargePromotePrice;
    //等级类型
    private String type;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPresentPoint() {
        return presentPoint;
    }

    public void setPresentPoint(String presentPoint) {
        this.presentPoint = presentPoint;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getTriggerPrice() {
        return triggerPrice == null?"0":triggerPrice;
    }

    public void setTriggerPrice(String triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public String getConsumePromotePrice() {

        return consumePromotePrice == null?"0":consumePromotePrice;
    }

    public void setConsumePromotePrice(String consumePromotePrice) {
        this.consumePromotePrice = consumePromotePrice;
    }

    public Boolean getConsumePromote() {
        return isConsumePromote;
    }

    public void setConsumePromote(Boolean consumePromote) {
        isConsumePromote = consumePromote;
    }

    public Boolean getRechargePromote() {
        return isRechargePromote;
    }

    public void setRechargePromote(Boolean rechargePromote) {
        isRechargePromote = rechargePromote;
    }

    public String getRechargePromotePrice() {
        return rechargePromotePrice == null?"0":rechargePromotePrice;
    }

    public void setRechargePromotePrice(String rechargePromotePrice) {
        this.rechargePromotePrice = rechargePromotePrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemberRank{" +
                "discount=" + discount +
                ", id=" + id +
                ", isEnable=" + isEnable +
                ", level=" + level +
                ", logo='" + logo + '\'' +
                ", presentPoint='" + presentPoint + '\'' +
                ", rankName='" + rankName + '\'' +
                ", triggerPrice='" + triggerPrice + '\'' +
                ", consumePromotePrice='" + consumePromotePrice + '\'' +
                ", isConsumePromote=" + isConsumePromote +
                ", isRechargePromote=" + isRechargePromote +
                ", rechargePromotePrice='" + rechargePromotePrice + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
