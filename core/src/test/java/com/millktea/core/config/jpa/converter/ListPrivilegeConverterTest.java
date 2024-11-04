package com.millktea.core.config.jpa.converter;

import com.millktea.core.domain.users.entity.User;
import com.millktea.core.domain.users.entity.User.Privilege;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.millktea.core.domain.users.entity.User.Privilege.READ;
import static com.millktea.core.domain.users.entity.User.Privilege.WRITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListPrivilegeConverterTest {
    ListPrivilegeConverter listPrivilegeConverter = new ListPrivilegeConverter(User.Privilege.class);

    @Test
    void convertToDatabaseColumn() {
        List<Privilege> privileges = List.of(READ, WRITE);
        String data = "READ,WRITE";
        String result = listPrivilegeConverter.convertToDatabaseColumn(privileges);

        assertEquals(data, result);
    }

    @Test
    void convertToEntityAttribute() {
        List<Privilege> privileges = List.of(READ, WRITE);
        String data = "READ,WRITE";
        List<Privilege> result = listPrivilegeConverter.convertToEntityAttribute(data);

        assertEquals(privileges, result);
    }
}