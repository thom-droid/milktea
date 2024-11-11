package com.millktea.api.domain.business.mapper;

import com.millktea.api.domain.business.dto.BusinessRequestDto;
import com.millktea.core.domain.business.entity.Business;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BusinessMapper {

    Business toEntityFrom(BusinessRequestDto.Post dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "representative", source = "representative")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "addr", source = "addr")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "telephoneNumber", source = "telephoneNumber")
    void updateEntityFromSource(@MappingTarget Business entity, Business source);
}
