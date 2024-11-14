package com.millktea.api.domain.user.mapper;

import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.dto.SaveUserRes;
import com.millktea.core.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityFrom(SaveUserReq saveUserReq);
    SaveUserRes toDtoFrom(User user);
}
