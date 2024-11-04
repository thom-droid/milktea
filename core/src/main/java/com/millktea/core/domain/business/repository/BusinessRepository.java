package com.millktea.core.domain.business.repository;

import com.millktea.core.domain.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByBusinessNo(String businessNo);
}