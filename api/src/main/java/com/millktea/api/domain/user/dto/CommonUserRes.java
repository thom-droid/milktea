package com.millktea.api.domain.user.dto;

import com.millktea.core.domain.business.entity.Status;
import com.millktea.core.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class CommonUserRes {
    private final String username;
    private final String name;
    private final String email;
    private final String phone;
    private final User.Role role;
    @Builder.Default
    private final List<User.Privilege> privileges = new ArrayList<>();
    private final Status status;
}