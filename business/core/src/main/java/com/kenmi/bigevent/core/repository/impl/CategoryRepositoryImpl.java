package com.kenmi.bigevent.core.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenmi.bigevent.core.repository.CategoryRepository;
import com.kenmi.bigevent.dal.dao.CategoryMapper;
import com.kenmi.bigevent.dal.dataobject.CategoryDO;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl extends ServiceImpl<CategoryMapper, CategoryDO> implements CategoryRepository {
}
