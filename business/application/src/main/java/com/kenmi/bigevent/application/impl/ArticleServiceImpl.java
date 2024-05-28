package com.kenmi.bigevent.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kenmi.bigevent.api.dto.ArticleDTO;
import com.kenmi.bigevent.api.request.AddArticleRequest;
import com.kenmi.bigevent.api.request.UpdateArticleRequest;
import com.kenmi.bigevent.api.response.Pager;
import com.kenmi.bigevent.application.ArticleService;
import com.kenmi.bigevent.application.convert.ArticleConverter;
import com.kenmi.bigevent.common.utils.IdWorker;
import com.kenmi.bigevent.common.utils.UserInfoThreadHolder;
import com.kenmi.bigevent.core.repository.ArticleRepository;
import com.kenmi.bigevent.dal.dataobject.ArticleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void add(AddArticleRequest article) {
        ArticleDO articleDO = ArticleConverter.INSTANCE.convert(article);
        articleDO.setId(new IdWorker().nextId());
        articleRepository.save(articleDO);
    }

    @Override
    public Pager<ArticleDTO> list(Integer pageNum, Integer pageSize, Long categoryId, String state) {

        IPage<ArticleDO> iPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<ArticleDO>().lambda()
                .eq(Objects.nonNull(categoryId), ArticleDO::getCategoryId, categoryId)
                .eq(StringUtils.hasLength(state), ArticleDO::getState, state)
                .eq(ArticleDO::getCreatedBy, UserInfoThreadHolder.getCurrentUser().getUserId());

        List<ArticleDTO> articleDTOS = articleRepository.list(iPage, queryWrapper).stream()
                .map(ArticleConverter.INSTANCE::convert).collect(Collectors.toList());
        return new Pager<>(iPage.getTotal(), articleDTOS);
    }

    @Override
    public ArticleDTO get(Long id) {
        return ArticleConverter.INSTANCE.convert(articleRepository.getById(id));
    }

    @Override
    public void update(UpdateArticleRequest article) {
        articleRepository.updateById(ArticleConverter.INSTANCE.convert(article));
    }

    @Override
    public void delete(Long id) {
        articleRepository.removeById(id);
    }
}
