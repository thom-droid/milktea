package com.millktea.api.domain.user.service;

import com.millktea.core.domain.user.entity.User;

import java.util.Optional;

public interface UserAccessData {
    User get(String username, String password);
    User save(User data);
    Optional<User> getOptional(String username, String password);
    boolean existsByUsernameAndBusinessNo(String username, String businessNo);
    Optional<User> getOptionalByUsernameAndBusinessNo(String username, String businessNo);
    User getByUsernameAndBusinessNo(String username, String businessNo);
    void delete(User entity);
}
