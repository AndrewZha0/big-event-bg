package com.kenmi.bigevent.server.facade;

import com.kenmi.bigevent.api.dto.ArticleDTO;
import com.kenmi.bigevent.api.facade.ArticleFacade;
import com.kenmi.bigevent.api.request.AddArticleRequest;
import com.kenmi.bigevent.api.request.UpdateArticleRequest;
import com.kenmi.bigevent.api.response.Pager;
import com.kenmi.bigevent.api.response.ResultResponse;
import com.kenmi.bigevent.application.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleFacadeImpl implements ArticleFacade {

    @Autowired
    private ArticleService articleService;

    @Autowired
    ServerFacadeTemplate serverFacadeTemplate;

    @Override
    public ResultResponse<Void> add(AddArticleRequest article) {
        return serverFacadeTemplate.execute(() -> articleService.add(article));
    }

    @Override
    public ResultResponse<Pager<ArticleDTO>> page(Integer pageNum, Integer pageSize, Long categoryId, String state) {
        return serverFacadeTemplate.execute(() -> articleService.list(pageNum, pageSize, categoryId, state));
    }

    @Override
    public ResultResponse<ArticleDTO> get(Long id) {
        return serverFacadeTemplate.execute(() -> articleService.get(id));
    }

    @Override
    public ResultResponse<Void> update(UpdateArticleRequest article) {
        return serverFacadeTemplate.execute(() -> articleService.update(article));
    }

    @Override
    public ResultResponse<Void> delete(Long id) {
        return serverFacadeTemplate.execute(() -> articleService.delete(id));
    }
}
