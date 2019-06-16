package com.hunmengwl.projectlove.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ToString
public class InformationComment {
    private Integer commentId;

    private String commentName;

    private String commentContent;

    private Date commentTime;

    private Integer commentPraise;

    private Integer informationId;

    private Integer userId;

    public InformationComment(Integer commentId, String commentName, String commentContent, Date commentTime, Integer commentPraise, Integer informationId, Integer userId) {
        this.commentId = commentId;
        this.commentName = commentName;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.commentPraise = commentPraise;
        this.informationId = informationId;
        this.userId = userId;
    }

    public InformationComment() {
        super();
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentPraise() {
        return commentPraise;
    }

    public void setCommentPraise(Integer commentPraise) {
        this.commentPraise = commentPraise;
    }

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}