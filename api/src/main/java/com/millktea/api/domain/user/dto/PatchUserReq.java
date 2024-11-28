package com.millktea.api.domain.user.dto;

import com.millktea.core.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class PatchUserReq {
    private final String name;
    private final String email;
    private final String phone;

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Privileges {
        @NotNull
        private String businessNo;
        @NotNull
        private String username;
        @Builder.Default
        private List<User.Privilege> privileges = new ArrayList<>();
    }
}