package com.millktea.api.domain.user.service.impl;

import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.user.mapper.UserMapper;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.exception.RuntimeErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO authorize, authenticate
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserAccessDataImpl userAccessData;
    private final BusinessService businessService;
    private final UserMapper userMapper;

    @Override
    public User save(String businessNo, User user) {
        Business business = businessService.getOne(businessNo);
        validateUser(business, user);
        user.setDefaultRoleDependingOnRole();
        user.addBusiness(business);
        return userAccessData.save(user);
    }

    // TODO 인증 인가로직으로 유저 권한 확인
    @Override
    public void patch(User user) {
        User entity = getByUsernameAndPasswordOrThrowIfNotExists(user);
        userMapper.updateEntityFromSource(entity, user);
        userAccessData.save(entity);
    }

    /**
     * 유저 권한을 수정한다.
     * 수정이 가능한 것은 대표계정 (REPRESENTATIVE) 만 가능하다.
     * @param businessNo
     * @param target
     * @return
     */
    @Override
    public User updatePrivileges(String businessNo, User target) {
        Business business = businessService.getOne(businessNo);
        User entity = getByUsernameAndPasswordOrThrowIfNotExists(target);
        entity.updatePrivileges(target.getPrivileges());
        // TODO:: JWT 재발급 필요
        userAccessData.save(entity);
        return entity;
    }

    private void validateUser(Business business, User user) {
        throwIfAlreadyExists(business, user);
        throwIfBusinessAlreadyHasRepresentative(business, user);
    }

    public Optional<User> getOptional(String username, String password) {
        return userAccessData.getOptional(username, password);
    }

    private void throwIfAlreadyExists(Business business, User user) {
        getOptional(user.getUsername(), user.getPassword())
                .ifPresent(u -> {
                    if (business.getUserList().contains(u))
                        throw new BusinessRuntimeException(RuntimeErrorCode.USER_ALREADY_EXISTS);
                });
    }

    private User getByUsernameAndPasswordOrThrowIfNotExists(User user) {
        return userAccessData.get(user.getUsername(), user.getPassword());
    }

    private void throwIfBusinessAlreadyHasRepresentative(Business business, User user) {
        if (business.hasRepresentativeUser() && user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.BUSINESS_ALREADY_HAS_REPRESENTATIVE_USER);
    }

    private void throwIfNotRepresentative(User user) {
        if (!user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_REPRESENTATIVE);
    }

}
