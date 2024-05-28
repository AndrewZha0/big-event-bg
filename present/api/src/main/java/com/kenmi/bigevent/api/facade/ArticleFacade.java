package com.kenmi.bigevent.api.facade;

import com.kenmi.bigevent.api.dto.ArticleDTO;
import com.kenmi.bigevent.api.request.AddArticleRequest;
import com.kenmi.bigevent.api.request.UpdateArticleRequest;
import com.kenmi.bigevent.api.response.Pager;
import com.kenmi.bigevent.api.response.ResultResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/article")
public interface ArticleFacade {

    @PostMapping
    ResultResponse<Void> add(@RequestBody @Validated AddArticleRequest article);

    @GetMapping
    ResultResponse<Pager<ArticleDTO>> page(Integer pageNum, Integer pageSize,
                                           @RequestParam(required = false) Long categoryId,
                                           @RequestParam(required = false) String state);

    @GetMapping("/detail")
    ResultResponse<ArticleDTO> get(Long id);

    @PutMapping
    ResultResponse<Void> update(@RequestBody @Validated UpdateArticleRequest article);

    @DeleteMapping
    ResultResponse<Void> delete(Long id);
}
