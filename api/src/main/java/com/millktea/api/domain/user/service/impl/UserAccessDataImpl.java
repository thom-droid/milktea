package com.millktea.api.domain.user.service.impl;

import com.millktea.api.domain.user.service.UserAccessData;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.domain.user.repository.UserRepository;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.exception.RuntimeErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAccessDataImpl implements UserAccessData {

    private final UserRepository userRepository;

    @Override
    public User get(String username, String password) {
        return getOptional(username, password)
                .orElseThrow(() -> new BusinessRuntimeException(RuntimeErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User save(User data) {
        return userRepository.save(data);
    }

    @Override
    public Optional<User> getOptional(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
