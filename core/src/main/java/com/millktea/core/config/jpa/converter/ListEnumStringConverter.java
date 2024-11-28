package com.millktea.core.config.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * List<Enum<E>> -> String
 * 해당 클래스를 상속하여 원하는 enum 타입을 생성자 파라미터로 넣으면 해당 타입 enum 또는 문자열로 변환
 */
@Converter
@RequiredArgsConstructor
public class ListEnumStringConverter<E extends Enum<E>> implements AttributeConverter<List<E>, String> {

    private final Class<E> enumType;

    @Override
    public String convertToDatabaseColumn(List<E> enums) {
        return enums != null ? enums.stream()
                        .map(Enum::name)
                        .collect(Collectors.joining(",")) : null;
    }

    @Override
    public List<E> convertToEntityAttribute(String s) {
        return s != null ? Stream.of(s.split(","))
                        .map(name -> Enum.valueOf(enumType, name))
                        .collect(Collectors.toList()) : null;
    }
}
