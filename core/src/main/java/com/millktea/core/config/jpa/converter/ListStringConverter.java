package com.millktea.core.config.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

/**
 * List<String> -> String
 */
@Converter
public class ListStringConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings == null ? null : String.join(",", strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return s == null ? null : Arrays.stream(s.split(",")).toList();
    }
}
