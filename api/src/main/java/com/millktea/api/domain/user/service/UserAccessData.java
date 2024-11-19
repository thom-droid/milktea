package com.millktea.api.domain.user.service;

import com.millktea.core.domain.user.entity.User;

import java.util.Optional;

public interface UserAccessData {
    User get(String username, String password);
    User save(User data);
    Optional<User> getOptional(String username, String password);
}
