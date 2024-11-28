package com.millktea.core.domain.user.entity;

import com.milktea.stub.user.UserStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void setDefaultRoleDependingOnRole() {
        // given
        User representative = UserStub.createUserStub(User.Role.REPRESENTATIVE);
        User user = UserStub.createUserStub(User.Role.USER);

        // when
        representative.setDefaultRoleDependingOnRole();
        user.setDefaultRoleDependingOnRole();

        // then
        assertEquals(representative.getRole(), User.Role.REPRESENTATIVE);
        assertEquals(representative.getPrivileges(), List.of(User.Privilege.ALL));

        assertEquals(user.getRole(), User.Role.USER);
        assertEquals(user.getPrivileges(), List.of(User.Privilege.READ));
    }
}