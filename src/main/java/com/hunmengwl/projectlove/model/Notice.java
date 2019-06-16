package com.hunmengwl.projectlove.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ToString
public class Notice {
    private Integer noticeId;

    private String noticeName;

    private String noticeContent;

    private Date noticeTime;

    private Integer noticePraise;

    private String dictType;

    private Integer userId;

    public Notice(Integer noticeId, String noticeName, String noticeContent, Date noticeTime, Integer noticePraise, String dictType, Integer userId) {
        this.noticeId = noticeId;
        this.noticeName = noticeName;
        this.noticeContent = noticeContent;
        this.noticeTime = noticeTime;
        this.noticePraise = noticePraise;
        this.dictType = dictType;
        this.userId = userId;
    }

    public Notice() {
        super();
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public Integer getNoticePraise() {
        return noticePraise;
    }

    public void setNoticePraise(Integer noticePraise) {
        this.noticePraise = noticePraise;
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