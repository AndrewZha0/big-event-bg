package com.kenmi.bigevent.api.facade;

import com.kenmi.bigevent.api.dto.CategoryDTO;
import com.kenmi.bigevent.api.request.AddCategoryRequest;
import com.kenmi.bigevent.api.request.UpdateCategoryRequest;
import com.kenmi.bigevent.api.response.ResultResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
public interface CategoryFacade {

    @PostMapping
    ResultResponse<Void> add(@RequestBody @Validated AddCategoryRequest category);

    @GetMapping
    ResultResponse<List<CategoryDTO>> list();

    @PutMapping
    ResultResponse<Void> update(@RequestBody @Validated UpdateCategoryRequest category);

    @GetMapping("/detail")
    ResultResponse<CategoryDTO> detail(Long id);

    @DeleteMapping
    ResultResponse<Void> delete(Long id);
}
