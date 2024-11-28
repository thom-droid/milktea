package com.millktea.api.domain.business.service;

import com.millktea.core.domain.business.entity.Business;

import java.util.Optional;

public interface BusinessAccessData {

    Business get(String businessNo);

    Business save(Business data);

    Optional<Business> getOptional(String businessNo);
}
