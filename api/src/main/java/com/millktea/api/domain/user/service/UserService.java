package com.millktea.api.domain.user.service;

import com.millktea.core.domain.user.entity.User;

public interface UserService {

    User save(String businessNo, User user);

}
