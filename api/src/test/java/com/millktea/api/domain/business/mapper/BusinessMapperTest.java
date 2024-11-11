package com.millktea.api.domain.business.mapper;

import com.milktea.stub.buisness.BusinessStub;
import com.millktea.api.config.domain.business.BusinessTestConfig;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

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

}