package com.millktea.api.domain.user.service.impl;

import com.milktea.stub.buisness.BusinessStub;
import com.milktea.stub.user.UserReqStub;
import com.milktea.stub.user.UserStub;
import com.millktea.api.config.domain.business.BusinessTestConfig;
import com.millktea.api.config.domain.business.UserTestConfig;
import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.business.service.impl.BusinessServiceImpl;
import com.millktea.api.domain.user.dto.SaveUserReq;
import com.millktea.api.domain.user.mapper.UserMapper;
import com.millktea.api.domain.user.service.UserAccessData;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.domain.user.repository.UserRepository;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.exception.RuntimeErrorCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {UserTestConfig.class, BusinessTestConfig.class})
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    BusinessServiceImpl businessService;

    @MockBean
    UserAccessDataImpl userAccessData;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapper userMapper;

    @Test
    void givenValidUser_whenSaveUser_thenSuccess() {
        // given
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.REPRESENTATIVE);
        Business businessStub = BusinessStub.createBusinessStub();
        User userStub = userMapper.toEntityFrom(saveUserReq);

        // when
        Mockito.when(businessService.getOne(any())).thenReturn(businessStub);
        Mockito.when(userAccessData.getOptional(any(), any())).thenReturn(Optional.of(userStub));
        Mockito.when(userAccessData.save(userStub)).thenReturn(userStub);

        // then
        assertEquals(userService.save(businessStub.getBusinessNo(), userStub), userStub);
        Mockito.verify(businessService, Mockito.times(1)).getOne(any());
        Mockito.verify(userAccessData, Mockito.times(1)).getOptional(any(), any());
        Mockito.verify(userAccessData, Mockito.times(1)).save(userStub);
    }

    @Test
    void givenBusiness_whenAlreadyHasRepresentative_thenThrows() {
        //given
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.REPRESENTATIVE);
        User userStub = userMapper.toEntityFrom(saveUserReq);
        Business businessStub = BusinessStub.createBusinessWithUsersStub(List.of(UserStub.createUserStub(User.Role.REPRESENTATIVE)));

        //when
        Mockito.when(businessService.getOne(any())).thenReturn(businessStub);
        Mockito.when(userAccessData.getOptional(any(), any())).thenReturn(Optional.of(userStub));

        //then
        BusinessRuntimeException businessRuntimeException = assertThrows(BusinessRuntimeException.class, () -> userService.save(businessStub.getBusinessNo(), userStub));
        assertEquals(businessRuntimeException.getErrorCode(), RuntimeErrorCode.BUSINESS_ALREADY_HAS_REPRESENTATIVE_USER);
        Mockito.verify(businessService, Mockito.times(1)).getOne(any());
        Mockito.verify(userAccessData, Mockito.times(1)).getOptional(any(), any());

    }

    @Test
    void givenUserWithSameUsernameAndPassword_whenAlreadyExists_thenThrows() {
        //given
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.REPRESENTATIVE);
        User userStub = userMapper.toEntityFrom(saveUserReq);
        Business businessStub = BusinessStub.createBusinessWithUsersStub(List.of(userStub));

        //when
        Mockito.when(businessService.getOne(any())).thenReturn(businessStub);
        Mockito.when(userAccessData.getOptional(any(), any())).thenReturn(Optional.of(userStub));

        //then
        BusinessRuntimeException businessRuntimeException = assertThrows(BusinessRuntimeException.class, () -> userService.save(businessStub.getBusinessNo(), userStub));
        assertEquals(businessRuntimeException.getErrorCode(), RuntimeErrorCode.USER_ALREADY_EXISTS);
        Mockito.verify(businessService, Mockito.times(1)).getOne(any());
        Mockito.verify(userAccessData, Mockito.times(1)).getOptional(any(), any());
    }

    @Test
    void givenBusinessWithRepresentative_whenNewUserTrySave_thenSucceed() {
        //given
        SaveUserReq saveUserReq = UserReqStub.createSaveUserReq(User.Role.USER);
        User userStub = userMapper.toEntityFrom(saveUserReq);
        Business businessStub = BusinessStub.createBusinessWithUsersStub(List.of(UserStub.createUserStub(User.Role.REPRESENTATIVE)));

        //when
        Mockito.when(businessService.getOne(any())).thenReturn(businessStub);
        Mockito.when(userAccessData.getOptional(any(), any())).thenReturn(Optional.empty());
        Mockito.when(userAccessData.save(userStub)).thenReturn(userStub);

        //then
        assertEquals(userService.save(businessStub.getBusinessNo(), userStub), userStub);
        Mockito.verify(businessService, Mockito.times(1)).getOne(any());
        Mockito.verify(userAccessData, Mockito.times(1)).getOptional(any(), any());
        Mockito.verify(userAccessData, Mockito.times(1)).save(userStub);
    }

    @Test
    void givenUser_whenPatch_thenSuccess() {
        //given
        User entity = UserStub.createUserStub(User.Role.REPRESENTATIVE);
        User source = UserStub.createUserStub(User.Role.REPRESENTATIVE);
        userMapper.updateEntityFromSource(entity, source);

        //when
        Mockito.when(userAccessData.get(source.getUsername(), source.getPassword())).thenReturn(entity);
        Mockito.when(userAccessData.save(entity)).thenReturn(entity);

        //then
        assertDoesNotThrow(() -> userService.patch(source));
        Mockito.verify(userAccessData, Mockito.times(1)).get(source.getUsername(), source.getPassword());
        Mockito.verify(userAccessData, Mockito.times(1)).save(entity);
    }

}