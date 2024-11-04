package com.millktea.core.config.jpa.converter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListStringConverterTest {

    ListStringConverter listStringConverter = new ListStringConverter();

    @Test
    void convertToDatabaseColumn() {
        String data1 = "test1,test2,test3";
        List<String> list1 = List.of("test1", "test2", "test3");

        String s = listStringConverter.convertToDatabaseColumn(list1);
        assertEquals(data1, s);
    }

    @Test
    void convertToEntityAttribute() {
        String data1 = "test1,test2,test3";
        List<String> list1 = List.of("test1", "test2", "test3");

        List<String> list = listStringConverter.convertToEntityAttribute(data1);
        assertEquals(list1, list);
    }
}