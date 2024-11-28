package com.millktea.core.domain.user.repository;

import com.millktea.core.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username AND u.business.businessNo = :businessNo")
    boolean existsByUsernameAndBusinessNo(String username, String businessNo);

    @Query("SELECT u FROM User u where u.username = :username AND u.business.businessNo = :businessNo")
    Optional<User> findByUsernameAndBusinessNo(String username, String businessNo);

}