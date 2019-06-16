package com.hunmengwl.projectlove.mapper;

import com.hunmengwl.projectlove.model.Information;
import org.springframework.stereotype.Repository;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationId);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
}