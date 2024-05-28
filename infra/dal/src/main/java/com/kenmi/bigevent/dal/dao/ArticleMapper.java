package com.kenmi.bigevent.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kenmi.bigevent.dal.dataobject.ArticleDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ArticleMapper extends BaseMapper<ArticleDO> {
}
