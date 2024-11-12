package com.millktea.api.domain.business.dto;

import com.millktea.core.domain.business.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SaveBusinessRes {

    private String businessNo;
    private String name;
    private String representative;
    private String addr;
    private String telephoneNumber;
    private String email;
    private String logoSrc;
    private String logoName;
    private Status status;
    private boolean hasRepresentativeUser;

}
