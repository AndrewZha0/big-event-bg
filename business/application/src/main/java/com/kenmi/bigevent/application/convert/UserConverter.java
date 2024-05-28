package com.kenmi.bigevent.application.convert;

import com.kenmi.bigevent.api.dto.UserDTO;
import com.kenmi.bigevent.api.request.UpdateUserRequest;
import com.kenmi.bigevent.dal.dataobject.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

//    @Mapping(source = "password", target = "", ignore = true)
    UserDTO convert(UserDO userDO);

    UserDO convert(UpdateUserRequest userRequest);
}
