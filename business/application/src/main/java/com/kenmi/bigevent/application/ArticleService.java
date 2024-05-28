package com.kenmi.bigevent.application;


import com.kenmi.bigevent.api.dto.ArticleDTO;
import com.kenmi.bigevent.api.request.AddArticleRequest;
import com.kenmi.bigevent.api.request.UpdateArticleRequest;
import com.kenmi.bigevent.api.response.Pager;

public interface ArticleService {
    void add(AddArticleRequest article);

    Pager<ArticleDTO> list(Integer pageNum, Integer pageSize, Long categoryId, String state);

    ArticleDTO get(Long id);

    void update(UpdateArticleRequest article);

    void delete(Long id);
}
