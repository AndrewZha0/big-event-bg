package com.kenmi.bigevent.dal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kenmi.bigevent.dal.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<UserDO> {
}
