package com.milktea.stub.user;

import com.milktea.stub.buisness.BusinessStub;
import com.milktea.stub.helper.random.NameGenerator;
import com.millktea.core.domain.user.entity.User;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserStub {

    /**
     * @return simple user with all privileges and a random business stubbed
     */
    public static User createUserStub() {
        return User.builder()
                .username("test")
                .password("test")
                .name(NameGenerator.getInstance().generate())
                .email("test@exmple.com")
                .phone("010-1234-5678")
                .role(User.Role.USER)
                .privileges(List.of(User.Privilege.ALL))
                .business(BusinessStub.createBusinessStub())
                .build();
    }


    /**
     * @param role
     * @return user with role
     */
    public static User createUserStub(User.Role role) {
        if (role == null) return createUserStub();
        return User.builder()
                .username("test")
                .password(createRandomPassword())
                .name(NameGenerator.getInstance().generate())
                .email("test@example.com")
                .phone("010-1234-5678")
                .role(role)
                .privileges(List.of(User.Privilege.ALL))
                .business(BusinessStub.createBusinessStub())
                .build();
    }

    public static User createUserStub(String username, User.Role role) {
            if (role == null) return createUserStub();
            return User.builder()
                    .username(username)
                    .password(createRandomPassword())
                    .name(NameGenerator.getInstance().generate())
                    .email("test@example.com")
                    .phone("010-1234-5678")
                    .role(role)
                    .privileges(List.of(User.Privilege.ALL))
                    .business(BusinessStub.createBusinessStub())
                    .build();
        }


    public static User createUserStub(User.Role role, List<User.Privilege> privileges) {
        if (role == null) return createUserStub();
        return User.builder()
                .username("test")
                .password(createRandomPassword())
                .name(NameGenerator.getInstance().generate())
                .email("test@example.com")
                .phone("010-1234-5678")
                .role(role)
                .privileges(privileges)
                .business(BusinessStub.createBusinessStub())
                .build();
    }

    public static User createUserStub(String username, User.Role role, List<User.Privilege> privileges) {
        if (role == null || username == null) return createUserStub();
        return User.builder()
                .username(username)
                .password(createRandomPassword())
                .name(NameGenerator.getInstance().generate())
                .email("test@example.com")
                .phone("010-1234-5678")
                .role(role)
                .privileges(privileges)
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

