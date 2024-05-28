package com.kenmi.bigevent.application.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kenmi.bigevent.api.dto.CategoryDTO;
import com.kenmi.bigevent.api.request.AddCategoryRequest;
import com.kenmi.bigevent.api.request.UpdateCategoryRequest;
import com.kenmi.bigevent.application.CategoryService;
import com.kenmi.bigevent.application.convert.CategoryConverter;
import com.kenmi.bigevent.common.domain.SimpleUser;
import com.kenmi.bigevent.common.utils.IdWorker;
import com.kenmi.bigevent.common.utils.UserInfoThreadHolder;
import com.kenmi.bigevent.core.repository.CategoryRepository;
import com.kenmi.bigevent.dal.dataobject.CategoryDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void add(AddCategoryRequest category) {
        CategoryDO categoryDO = CategoryConverter.INSTANCE.convert(category);
        categoryDO.setId(new IdWorker().nextId());
        categoryRepository.save(categoryDO);
    }

    @Override
    public List<CategoryDTO> list() {
        SimpleUser currentUser = UserInfoThreadHolder.getCurrentUser();
        return categoryRepository.list(new QueryWrapper<CategoryDO>().lambda()
                        .eq(CategoryDO::getCreatedBy, currentUser.getUserId()))
                .stream().map(CategoryConverter.INSTANCE::convert).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        return CategoryConverter.INSTANCE.convert(categoryRepository.getById(id));
    }

    @Override
    public void update(UpdateCategoryRequest category) {
        categoryRepository.updateById(CategoryConverter.INSTANCE.convert(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.removeById(id);
    }
}
