package com.millktea.api.domain.business.service.impl;

import com.millktea.api.domain.business.service.BusinessAccessData;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.repository.BusinessRepository;
import com.millktea.core.exception.BusinessRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.millktea.core.exception.RuntimeErrorCode.BUSINESS_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BusinessAccessDataImpl implements BusinessAccessData {

    private final BusinessRepository businessRepository;

    @Override
    public Business get(String businessNo) {
        return getOptional(businessNo).orElseThrow(() -> new BusinessRuntimeException(BUSINESS_NOT_FOUND));
    }

    @Override
    public Business save(Business data) {
        return businessRepository.save(data);
    }

    @Override
    public Optional<Business> getOptional(String businessNo) {
        return businessRepository.findByBusinessNo(businessNo);
    }
}
