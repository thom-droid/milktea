package com.millktea.core.domain.users.repository;

import com.millktea.core.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByNameAndPassword(String username, String password);

}