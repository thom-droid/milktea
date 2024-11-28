package com.millktea.api.domain.user.dto;

import com.millktea.core.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Builder
public class SaveUserReq {

    @NotNull
    private String businessNo;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
    @NotNull
    @Length(max = 13)
    private String phone;
    @Length(max = 100)
    private String address;
    @NotNull
    private User.Role role;

}
