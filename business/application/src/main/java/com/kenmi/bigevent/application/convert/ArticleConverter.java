package com.kenmi.bigevent.application.convert;

import com.kenmi.bigevent.api.dto.ArticleDTO;
import com.kenmi.bigevent.api.request.AddArticleRequest;
import com.kenmi.bigevent.api.request.UpdateArticleRequest;
import com.kenmi.bigevent.dal.dataobject.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConverter {
    ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

    ArticleDTO convert(ArticleDO articleDO);

    ArticleDO convert(AddArticleRequest articleRequest);

    ArticleDO convert(UpdateArticleRequest articleRequest);
}
