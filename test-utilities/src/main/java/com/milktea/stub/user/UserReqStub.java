package com.milktea.stub.user;

import com.milktea.stub.helper.random.NameGenerator;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.core.domain.user.entity.User;

public class UserReqStub {

    public static SaveUserReq createSaveUserReq(User.Role role) {
        return SaveUserReq.builder()
                .username("username")
                .name(NameGenerator.getInstance().generate())
                .password("password")
                .email("email@example.com")
                .phone("010-1234-5678")
                .role(role)
                .businessNo("1234567890")
                .build();
    }
}
