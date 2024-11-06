package com.millktea.core.domain.user.repository;

import com.millktea.core.config.database.config.JpaRepositoryTestConfiguration;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.repository.BusinessRepository;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.help.stub.UserStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = {JpaRepositoryTestConfiguration.class, UserRepository.class, BusinessRepository.class})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @BeforeEach
    void setup() {
    }

    @Test
    void repositoryExists() {
        assertNotNull(userRepository);
    }

    @Test
    void findByNameAndPassword() {
        //given
        User userStub = UserStub.createUserStub();
        Business business = userStub.getBusiness();

        //then
        assertDoesNotThrow(() -> businessRepository.save(business));
        assertDoesNotThrow(() -> userRepository.save(userStub));

        Optional<User> user = userRepository.findByNameAndPassword(userStub.getUserId(), userStub.getPassword());
        assertThat(user.isPresent()).isTrue();

        user.ifPresent(u -> {
            assertThat(u.getName()).isEqualTo("test");
            assertThat(u.getPassword()).isEqualTo("test");
        });

    }

}