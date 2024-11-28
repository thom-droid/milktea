package com.millktea.api.domain.business.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Builder
public class PatchBusinessReq {

    @Length(max = 100)
    private String representative;

    @Length(max = 100)
    private String addr;

    @Length(max = 20)
    private String telephoneNumber;

    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;
}
