package com.kenmi.bigevent.core.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenmi.bigevent.core.repository.ArticleRepository;
import com.kenmi.bigevent.dal.dao.ArticleMapper;
import com.kenmi.bigevent.dal.dataobject.ArticleDO;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleRepository {
}
