package com.millktea.api.domain.user.mapper;

import com.milktea.stub.user.UserReqStub;
import com.milktea.stub.user.UserStub;
import com.millktea.api.config.domain.business.UserTestConfig;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.dto.CommonUserRes;
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
        CommonUserRes commonUserRes = userMapper.toDtoFrom(userStub);

        // then
        assertEquals(commonUserRes.getUsername(), userStub.getUsername());
        assertEquals(commonUserRes.getName(), userStub.getName());
        assertEquals(commonUserRes.getEmail(), userStub.getEmail());
        assertEquals(commonUserRes.getPhone(), userStub.getPhone());
        assertEquals(commonUserRes.getRole(), userStub.getRole());
        assertEquals(commonUserRes.getPrivileges(), userStub.getPrivileges());
        assertEquals(commonUserRes.getStatus(), userStub.getStatus());
    }

    @Test
    void updateEntityFromSource() {
        // given
        User userStub = UserStub.createUserStub();
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.REPRESENTATIVE);

        // when
        userMapper.updateEntityFromSource(userStub, userMapper.toEntityFrom(saveUserReq));

        // then
        assertEquals(userStub.getName(), saveUserReq.getName());
        assertEquals(userStub.getEmail(), saveUserReq.getEmail());
        assertEquals(userStub.getPhone(), saveUserReq.getPhone());
    }

}