package com.milktea.stub.buisness;

import com.millktea.api.domain.business.dto.PatchBusinessReq;

public class BusinessReqStub {

    public static PatchBusinessReq createPatchBusinessReq() {
        return PatchBusinessReq.builder()
                .representative("representative")
                .addr("addr")
                .telephoneNumber("telephoneNumber")
                .email("email")
                .build();
    }

}
