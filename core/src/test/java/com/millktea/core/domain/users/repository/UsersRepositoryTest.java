package com.millktea.core.domain.users.repository;

import com.millktea.core.config.database.config.JpaRepositoryTestConfiguration;
import com.millktea.core.domain.users.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = {JpaRepositoryTestConfiguration.class, UsersRepository.class})
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;
    private User user;

    @BeforeEach
    void setup() {

    }

    @Test
    void repositoryExists() {
        assertNotNull(usersRepository);
    }

    @Test
    void findByNameAndPassword() {
        Optional<User> users = usersRepository.findByNameAndPassword("test", "test");
        assertThat(users.isPresent()).isTrue();

        users.ifPresent(u -> {
            assertThat(u.getName()).isEqualTo("test");
            assertThat(u.getPassword()).isEqualTo("test");
        });

    }

}