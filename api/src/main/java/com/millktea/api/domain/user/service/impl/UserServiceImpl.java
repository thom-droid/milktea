package com.millktea.api.domain.user.service.impl;

import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.user.mapper.UserMapper;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.domain.user.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final BusinessService businessService;
    private final UserMapper userMapper;

    @Override
    public User save(String businessNo, User user) {
        Business business = businessService.getOne(businessNo);
        validateUser(business, user);
        user.setDefaultRoleDependingOnRole();
        user.addBusiness(business);
        return userRepository.save(user);
    }

    // TODO 인증 인가로직으로 유저 권한 확인
    @Override
    public void patch(User user) {
        User entity = getOrThrowIfNotExists(user);
        userMapper.updateEntityFromSource(entity, user);
        userRepository.save(entity);
    }

    private void validateUser(Business business, User user) {
        throwIfAlreadyExists(business, user);
        throwIfBusinessAlreadyHasRepresentative(business, user);
    }

    public Optional<User> getOptional(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    private void throwIfAlreadyExists(Business business, User user) {
        getOptional(user.getUsername(), user.getPassword())
                .ifPresent(u -> {
                    if (business.getUserList().contains(u))
                        throw new BusinessRuntimeException(RuntimeErrorCode.USER_ALREADY_EXISTS);
                });
    }

    private User getOrThrowIfNotExists(User user) {
        return getOptional(user.getUsername(), user.getPassword())
                .orElseThrow(() -> new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_FOUND));
    }

    private void throwIfBusinessAlreadyHasRepresentative(Business business, User user) {
        if (business.hasRepresentativeUser() && user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.BUSINESS_ALREADY_HAS_REPRESENTATIVE_USER);
    }

    private void throwIfNotRepresentative(User user) {
        if (!user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_REPRESENTATIVE);
    }

}
