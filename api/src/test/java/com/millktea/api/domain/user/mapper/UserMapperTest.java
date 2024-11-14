package com.millktea.api.domain.user.mapper;

import com.milktea.stub.user.UserReqStub;
import com.milktea.stub.user.UserStub;
import com.millktea.api.config.domain.business.UserTestConfig;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.dto.SaveUserRes;
import com.millktea.core.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {UserTestConfig.class})
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void toEntityFromSaveUserReq() {
        // given
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.REPRESENTATIVE);

        // when
        User entity = userMapper.toEntityFrom(saveUserReq);

        // then
        assertEquals(entity.getUsername(), saveUserReq.getUsername());
        assertEquals(entity.getName(), saveUserReq.getName());
        assertEquals(entity.getPassword(), saveUserReq.getPassword());
        assertEquals(entity.getEmail(), saveUserReq.getEmail());
        assertEquals(entity.getPhone(), saveUserReq.getPhone());
        assertEquals(entity.getRole(), saveUserReq.getRole());

    }

    @Test
    void toSaveUserResFromEntity() {
        // given
        User userStub = UserStub.createUserStub();

        // when
        SaveUserRes saveUserRes = userMapper.toDtoFrom(userStub);

        // then
        assertEquals(saveUserRes.getUsername(), userStub.getUsername());
        assertEquals(saveUserRes.getName(), userStub.getName());
        assertEquals(saveUserRes.getEmail(), userStub.getEmail());
        assertEquals(saveUserRes.getPhone(), userStub.getPhone());
        assertEquals(saveUserRes.getRole(), userStub.getRole());
        assertEquals(saveUserRes.getPrivileges(), userStub.getPrivileges());
        assertEquals(saveUserRes.getStatus(), userStub.getStatus());
    }

}