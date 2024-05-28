package com.kenmi.bigevent.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kenmi.bigevent.dal.dataobject.CategoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper extends BaseMapper<CategoryDO> {
}
