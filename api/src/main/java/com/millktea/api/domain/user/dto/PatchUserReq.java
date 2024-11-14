package com.millktea.api.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Builder
public class PatchUserReq {
    private final String name;
    private final String email;
    private final String phone;
}