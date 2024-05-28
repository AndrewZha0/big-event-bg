package com.kenmi.bigevent.application.convert;

import com.kenmi.bigevent.api.dto.CategoryDTO;
import com.kenmi.bigevent.api.request.AddCategoryRequest;
import com.kenmi.bigevent.api.request.UpdateCategoryRequest;
import com.kenmi.bigevent.dal.dataobject.CategoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryConverter {
    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    CategoryDTO convert(CategoryDO categoryDO);

    CategoryDO convert(AddCategoryRequest categoryRequest);

    CategoryDO convert(UpdateCategoryRequest categoryRequest);
}
