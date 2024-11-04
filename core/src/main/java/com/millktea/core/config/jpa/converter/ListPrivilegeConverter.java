package com.millktea.core.config.jpa.converter;

import com.millktea.core.domain.users.entity.User;
import jakarta.persistence.Converter;

@Converter
public class ListPrivilegeConverter extends ListEnumStringConverter<User.Privilege> {

    public ListPrivilegeConverter(Class<User.Privilege> enumType) {
        super(enumType);
    }
}
