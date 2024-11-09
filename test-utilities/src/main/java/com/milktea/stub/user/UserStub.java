package com.milktea.stub.user;

import com.milktea.stub.buisness.BusinessStub;
import com.millktea.core.domain.user.entity.User;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserStub {

    public static User createUserStub() {
        return User.builder()
                .userId("test")
                .password("test")
                .name("test")
                .email("test@exmple.com")
                .role(User.Role.USER)
                .privileges(List.of(User.Privilege.ALL))
                .business(BusinessStub.createBusinessStub())
                .build();
    }

    private static String createRandomUserId() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }

    private static String createRandomPassword() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }


}

