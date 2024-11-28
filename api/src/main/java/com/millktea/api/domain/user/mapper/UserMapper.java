package com.millktea.api.domain.user.mapper;

import com.millktea.api.domain.user.dto.PatchUserReq;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.dto.CommonUserRes;
import com.millktea.core.domain.user.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityFrom(SaveUserReq saveUserReq);
    User toEntityFrom(PatchUserReq patchUserReq);
    User toEntityFrom(PatchUserReq.Privileges privileges);
    User toEntityFrom(PatchUserReq.Status status);
    CommonUserRes toDtoFrom(User user);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            ignoreByDefault = true
    )
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    void updateEntityFromSource(@MappingTarget User entity, User source);
}
