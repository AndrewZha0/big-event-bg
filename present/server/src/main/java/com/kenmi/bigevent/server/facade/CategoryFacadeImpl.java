package com.kenmi.bigevent.server.facade;

import com.kenmi.bigevent.api.dto.CategoryDTO;
import com.kenmi.bigevent.api.facade.CategoryFacade;
import com.kenmi.bigevent.api.request.AddCategoryRequest;
import com.kenmi.bigevent.api.request.UpdateCategoryRequest;
import com.kenmi.bigevent.api.response.ResultResponse;
import com.kenmi.bigevent.application.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryFacadeImpl implements CategoryFacade {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    ServerFacadeTemplate serverFacadeTemplate;

    @Override
    public ResultResponse<Void> add(AddCategoryRequest category) {
        return serverFacadeTemplate.execute(() -> categoryService.add(category));
    }

    @Override
    public ResultResponse<List<CategoryDTO>> list() {
        return serverFacadeTemplate.execute(() -> categoryService.list());
    }

    @Override
    public ResultResponse<Void> update(UpdateCategoryRequest category) {
        return serverFacadeTemplate.execute(() -> categoryService.update(category));
    }

    @Override
    public ResultResponse<CategoryDTO> detail(Long id) {
        return serverFacadeTemplate.execute(() -> categoryService.findById(id));
    }

    @Override
    public ResultResponse<Void> delete(Long id) {
        return serverFacadeTemplate.execute(() -> categoryService.delete(id));
    }
}
