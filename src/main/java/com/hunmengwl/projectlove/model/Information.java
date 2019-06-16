package com.hunmengwl.projectlove.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ToString
public class Information {
    private Integer informationId;

    private String informationNumber;

    private String informationObject;

    private String informationContent;

    private String informationMe;

    private Date informationTime;

    private Integer informationPraise;

    private Integer informationLook;

    private Integer informationForward;

    private Integer informationShow;

    private String informationRemark;

    private String dictType;

    private Integer userId;

    public Information(Integer informationId, String informationNumber, String informationObject, String informationContent, String informationMe, Date informationTime, Integer informationPraise, Integer informationLook, Integer informationForward, Integer informationShow, String informationRemark, String dictType, Integer userId) {
        this.informationId = informationId;
        this.informationNumber = informationNumber;
        this.informationObject = informationObject;
        this.informationContent = informationContent;
        this.informationMe = informationMe;
        this.informationTime = informationTime;
        this.informationPraise = informationPraise;
        this.informationLook = informationLook;
        this.informationForward = informationForward;
        this.informationShow = informationShow;
        this.informationRemark = informationRemark;
        this.dictType = dictType;
        this.userId = userId;
    }

    public Information() {
        super();
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getInformationNumber() {
        return informationNumber;
    }

    public void setInformationNumber(String informationNumber) {
        this.informationNumber = informationNumber;
    }

    public String getInformationObject() {
        return informationObject;
    }

    public void setInformationObject(String informationObject) {
        this.informationObject = informationObject;
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    public String getInformationMe() {
        return informationMe;
    }

    public void setInformationMe(String informationMe) {
        this.informationMe = informationMe;
    }

    public Date getInformationTime() {
        return informationTime;
    }

    public void setInformationTime(Date informationTime) {
        this.informationTime = informationTime;
    }

    public Integer getInformationPraise() {
        return informationPraise;
    }

    public void setInformationPraise(Integer informationPraise) {
        this.informationPraise = informationPraise;
    }

    public Integer getInformationLook() {
        return informationLook;
    }

    public void setInformationLook(Integer informationLook) {
        this.informationLook = informationLook;
    }

    public Integer getInformationForward() {
        return informationForward;
    }

    public void setInformationForward(Integer informationForward) {
        this.informationForward = informationForward;
    }

    public Integer getInformationShow() {
        return informationShow;
    }

    public void setInformationShow(Integer informationShow) {
        this.informationShow = informationShow;
    }

    public String getInformationRemark() {
        return informationRemark;
    }

    public void setInformationRemark(String informationRemark) {
        this.informationRemark = informationRemark;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}