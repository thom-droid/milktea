package com.millktea.core.config.jpa.converter;

import com.millktea.core.domain.user.entity.User;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class ListPrivilegeConverter implements AttributeConverter<List<User.Privilege>, String> {

    @Override
    public String convertToDatabaseColumn(List<User.Privilege> enums) {
        return enums != null ? enums.stream()
                        .map(Enum::name)
                        .collect(Collectors.joining(",")) : null;
    }

    @Override
    public List<User.Privilege> convertToEntityAttribute(String s) {
        return s != null ? Stream.of(s.split(","))
                        .map(User.Privilege::valueOf)
                        .collect(Collectors.toList()) : null;
    }
}
