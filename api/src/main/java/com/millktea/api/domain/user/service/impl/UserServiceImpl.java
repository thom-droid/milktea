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

import static com.millktea.core.exception.RuntimeErrorCode.*;

// TODO authorize, authenticate
// TODO:: 유저 이름, 회사 번호로 조회하는 빈 추가 쿼리로 조회
// TODO:: 특정 메서드 수행 시 마스터 계정 확인 로직 추가 (마스터 계정이 아닌 경우 예외 발생)

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

    @Override
    public void patch(User user) {
        User entity = getByUsernameAndPasswordOrThrow(user);
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
        if (target.doesNotHaveAnyPrivilege()) throw new BusinessRuntimeException(NO_PRIVILEGES_SELECTED);

        Business business = businessService.getOne(businessNo);
        User entity = getByUsernameAndBusinessNo(target.getUsername(), businessNo);

        throwIfBusinessNotContainUser(business, entity);
        throwIfTryingToUpdateRepresentative(entity);

        entity.updatePrivileges(target.getPrivileges());
        userAccessData.save(entity);
        return entity;
    }

    /**
     * 유저 상태를 수정한다.
     * 수정이 가능한 것은 대표계정 (REPRESENTATIVE) 만 가능하다.
     *
     * @param businessNo
     * @param user
     * @return
     */
    @Override
    public User updateStatus(String businessNo, User user) {
        Business business = businessService.getOne(businessNo);
        User entity = getByUsernameAndBusinessNo(user.getUsername(), businessNo);

        throwIfBusinessNotContainUser(business, entity);
        throwIfTryingToUpdateRepresentative(entity);

        user.updateStatus(user.getStatus());

        return userAccessData.save(user);
    }

    @Override
    public void delete(String businessNo, String username) {
        Business business = businessService.getOne(businessNo);
        User entity = getByUsernameAndBusinessNo(username, businessNo);

        throwIfBusinessNotContainUser(business, entity);
        throwIfTryingToUpdateRepresentative(entity);

        userAccessData.delete(entity);
    }

    private void throwIfBusinessNotContainUser(Business business, User user) {
        if (!business.containsUser(user)) throw new BusinessRuntimeException(BUSINESS_DOES_NOT_CONTAIN_USER);
    }

    private void throwIfTryingToUpdateRepresentative(User user) {
        if (user.isRepresentative()) throw new BusinessRuntimeException(USER_REPRESENTATIVE_MUST_NOT_BE_UPDATED);
    }

    private void validateUser(Business business, User user) {
        throwIfAlreadyExists(business, user);
        throwIfBusinessAlreadyHasRepresentative(business, user);
    }

    public Optional<User> getOptional(String username, String password) {
        return userAccessData.getOptional(username, password);
    }

    public User getByUsernameAndBusinessNo(String username, String businessNo) {
        return userAccessData.getByUsernameAndBusinessNo(username, businessNo);
    }

    private void throwIfAlreadyExists(Business business, User user) {
        getOptional(user.getUsername(), user.getPassword())
                .ifPresent(u -> {
                    if (business.getUserList().contains(u))
                        throw new BusinessRuntimeException(RuntimeErrorCode.USER_ALREADY_EXISTS);
                });
    }

    private User getByUsernameAndPasswordOrThrow(User user) {
        return userAccessData.get(user.getUsername(), user.getPassword());
    }

    private void throwIfBusinessAlreadyHasRepresentative(Business business, User user) {
        if (business.hasRepresentativeUser() && user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.BUSINESS_ALREADY_HAS_REPRESENTATIVE_USER);
    }

    private void throwIfNotRepresentative(User user) {
        if (!user.isRepresentative()) throw new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_REPRESENTATIVE);
    }

}
