package com.hunmengwl.projectlove.mapper;

import com.hunmengwl.projectlove.model.InformationComment;
import org.springframework.stereotype.Repository;

public interface InformationCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(InformationComment record);

    int insertSelective(InformationComment record);

    InformationComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(InformationComment record);

    int updateByPrimaryKey(InformationComment record);
}