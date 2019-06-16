package com.hunmengwl.projectlove.mapper;

import com.hunmengwl.projectlove.model.Dict;
import org.springframework.stereotype.Repository;

public interface DictMapper {
    int deleteByPrimaryKey(Integer dictId);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer dictId);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}