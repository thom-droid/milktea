package com.millktea.api.domain.user.service;

import com.millktea.core.domain.user.entity.User;

public interface UserService {

    User save(String businessNo, User user);
    void patch(User user);
    User updatePrivileges(String businessNo, User user);
    User updateStatus(String businessNo, User user);
    void delete(String businessNo, String username);

}
