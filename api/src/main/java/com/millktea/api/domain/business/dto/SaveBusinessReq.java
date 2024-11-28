package com.millktea.api.domain.business.dto;

import com.millktea.core.domain.business.entity.Business;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Builder
public class SaveBusinessReq {

    @NotNull
    @Length(min = 10, max = 10)
    private String businessNo;

    @NotNull
    @Length(max = 20)
    private String name;

    @NotNull
    @Length(max = 100)
    private String representative;

    @Length(max = 100)
    private String addr;

    @Length(max = 20)
    private String telephoneNumber;

    @Length(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    private String email;

    private String logoSrc;
    private String logoName;

    public Business toEntity() {
        return Business.builder()
                .businessNo(this.businessNo)
                .name(this.name)
                .representative(this.representative)
                .addr(this.addr)
                .telephoneNumber(this.telephoneNumber)
                .email(this.email)
                .build();
    }
}
