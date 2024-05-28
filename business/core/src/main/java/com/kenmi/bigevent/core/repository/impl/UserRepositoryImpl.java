package com.kenmi.bigevent.core.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenmi.bigevent.core.repository.UserRepository;
import com.kenmi.bigevent.dal.dao.UserMapper;
import com.kenmi.bigevent.dal.dataobject.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserDO> implements UserRepository {
}
