package com.hunmengwl.projectlove.mapper;

import com.hunmengwl.projectlove.model.Notice;
import org.springframework.stereotype.Repository;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}