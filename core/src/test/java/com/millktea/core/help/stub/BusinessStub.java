package com.millktea.core.help.stub;

import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import com.millktea.core.domain.users.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessStub {

    public static Business createBusinessStub() {
        return Business.builder()
                .businessNo(createRandomBusinessNo())
                .name("Test Business")
                .businessOwner("Test Owner")
                .addr("123 Test Street")
                .telephoneNumber("010-1234-5678")
                .email("test@example.com")
                .logoSrc("path/to/logo.png")
                .logoName("logo.png")
                .status(Status.ACTIVE)
                .build();
    }

    public static Business createBusinessWithUsersStub(List<User> users) {
        Business business = createBusinessStub();
        business.setUserList(users);
        return business;
    }

    private static String createRandomBusinessNo() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }
}
