package com.millktea.api.domain.business.mapper;

import com.millktea.api.domain.business.dto.PatchBusinessReq;
import com.millktea.api.domain.business.dto.SaveBusinessReq;
import com.millktea.api.domain.business.dto.SaveBusinessRes;
import com.millktea.core.domain.business.entity.Business;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BusinessMapper {

    Business toEntityFrom(SaveBusinessReq dto);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    Business toEntityFrom(PatchBusinessReq dto);

    @Mapping(target = "businessNo", source = "businessNo")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "representative", source = "representative")
    @Mapping(target = "addr", source = "addr")
    @Mapping(target = "telephoneNumber", source = "telephoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "logoSrc", source = "logoSrc")
    @Mapping(target = "logoName", source = "logoName")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "hasRepresentativeUser", expression = "java(source.hasRepresentativeUser())")
    SaveBusinessRes toResponseFrom(Business source);

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
