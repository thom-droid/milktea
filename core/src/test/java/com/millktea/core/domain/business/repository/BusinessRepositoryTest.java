package com.millktea.core.domain.business.repository;

import com.millktea.core.config.database.config.JpaRepositoryTestConfiguration;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = {JpaRepositoryTestConfiguration.class, BusinessRepository.class})
class BusinessRepositoryTest {

    @Autowired
    private BusinessRepository businessRepository;
    @BeforeEach
    void setup() {
        Business business = new Business();
        business.setBusinessNo("1234567890");
        business.setName("test business name");
        business.setAddr("test business address");
        business.setTelephoneNumber("010-1234-5678");
        business.setRepresentative("test business owner");
        business.setEmail("test@gmail.com");
        business.setStatus(Status.ACTIVE);

        businessRepository.save(business);
    }

    @Test
    void repositoryExists() {
        assertNotNull(businessRepository);
    }

    @Test
    void findByBusinessNo() {
        Optional<Business> business = businessRepository.findByBusinessNo("1234567890");
        assertThat(business.isPresent()).isTrue();

        business.ifPresent(b -> {
            assertThat(b.getBusinessNo()).isEqualTo("1234567890");
            assertThat(b.getName()).isEqualTo("test business name");
            assertThat(b.getAddr()).isEqualTo("test business address");
            assertThat(b.getTelephoneNumber()).isEqualTo("010-1234-5678");
            assertThat(b.getRepresentative()).isEqualTo("test business owner");
            assertThat(b.getEmail()).isEqualTo("test@gmail.com");
        });

    }

}