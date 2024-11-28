package com.millktea.api.domain.business.mapper;

import com.milktea.stub.buisness.BusinessReqStub;
import com.milktea.stub.buisness.BusinessStub;
import com.milktea.stub.user.UserStub;
import com.millktea.api.config.domain.business.BusinessTestConfig;
import com.millktea.api.domain.business.dto.PatchBusinessReq;
import com.millktea.api.domain.business.dto.SaveBusinessRes;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import com.millktea.core.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BusinessTestConfig.class)
class BusinessMapperTest {

    @Autowired
    BusinessMapper businessMapper;

    @Test
    void updateEntityFromSource() {

        Business entity = BusinessStub.createBusinessStub();
        Business source = Business.builder()
                .representative("new representative")
                .email("newemail@example.com")
                .name("newname")
                .addr("newaddr")
                .status(Status.INACTIVE)
                .telephoneNumber("010-1234-1234")
                .build();
        businessMapper.updateEntityFromSource(entity, source);

        assertEquals(entity.getRepresentative(), source.getRepresentative());
        assertEquals(entity.getEmail(), source.getEmail());
        assertEquals(entity.getName(), source.getName());
        assertEquals(entity.getAddr(), source.getAddr());
        assertEquals(entity.getTelephoneNumber(), source.getTelephoneNumber());

    }

    @Test
    void mapToSaveResponseFromEntity() {

        //given
        User representativeUser = UserStub.createUserStub(User.Role.REPRESENTATIVE);
        Business businessStub = BusinessStub.createBusinessStub();
        businessStub.addUser(representativeUser);

        //when
        SaveBusinessRes saveBusinessRes = assertDoesNotThrow(() -> businessMapper.toResponseFrom(businessStub));

        //then
        assertThat(saveBusinessRes.getBusinessNo()).isEqualTo(businessStub.getBusinessNo());
        assertThat(saveBusinessRes.getName()).isEqualTo(businessStub.getName());
        assertThat(saveBusinessRes.getRepresentative()).isEqualTo(businessStub.getRepresentative());
        assertThat(saveBusinessRes.getAddr()).isEqualTo(businessStub.getAddr());
        assertThat(saveBusinessRes.getTelephoneNumber()).isEqualTo(businessStub.getTelephoneNumber());
        assertThat(saveBusinessRes.getEmail()).isEqualTo(businessStub.getEmail());
        assertThat(saveBusinessRes.getLogoSrc()).isEqualTo(businessStub.getLogoSrc());
        assertThat(saveBusinessRes.getLogoName()).isEqualTo(businessStub.getLogoName());
        assertThat(saveBusinessRes.getStatus()).isEqualTo(businessStub.getStatus());
        assertThat(saveBusinessRes.isHasRepresentativeUser()).isEqualTo(businessStub.hasRepresentativeUser());

    }

    @Test
    void mapToEntityFromPatchReq() {

        //given
        PatchBusinessReq patchBusinessReq = BusinessReqStub.createPatchBusinessReq();
        Business businessStub = BusinessStub.createBusinessStub();

        //when
        Business business = assertDoesNotThrow(() -> businessMapper.toEntityFrom(patchBusinessReq));

        //then
        assertThat(business.getRepresentative()).isEqualTo(patchBusinessReq.getRepresentative());
        assertThat(business.getAddr()).isEqualTo(patchBusinessReq.getAddr());
        assertThat(business.getTelephoneNumber()).isEqualTo(patchBusinessReq.getTelephoneNumber());
        assertThat(business.getEmail()).isEqualTo(patchBusinessReq.getEmail());
    }

}