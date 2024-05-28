package com.kenmi.bigevent.application;

import com.kenmi.bigevent.api.dto.CategoryDTO;
import com.kenmi.bigevent.api.request.AddCategoryRequest;
import com.kenmi.bigevent.api.request.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {
    void add(AddCategoryRequest request);

    List<CategoryDTO> list();

    CategoryDTO findById(Long id);

    void update(UpdateCategoryRequest request);

    void delete(Long id);
}
