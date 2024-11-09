package com.millktea.api.domain.business.dto;

import com.millktea.core.domain.business.entity.Business;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

public class BusinessRequestDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @Builder
    public static class Post {

        @NotNull
        @Length(min = 10, max = 10)
        private String businessNo;

        @NotNull
        @Length(max = 20)
        private String name;

        @NotNull
        @Length(max = 100)
        private String representative;

        @Column(length = 100)
        @Length(max = 100)
        private String addr;

        @Column(length = 20)
        @Length(max = 20)
        private String telephoneNumber;

        @Column(length = 100)
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

}
