package com.millktea.api.domain.business.service.impl;

import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.exception.BusinessRuntimeException;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.millktea.api.exception.RuntimeErrorCode.BUSINESS_ALREADY_EXISTS;
import static com.millktea.api.exception.RuntimeErrorCode.BUSINESS_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;

    public Long saveBusiness(Business business) {
        throwIfAlreadyExist(business.getBusinessNo());
        return businessRepository.save(business).getId();
    }

    private void processImageIfExist(Business business, MultipartFile img) {
        // TODO:: logic
    }

    @Override
    public Business getBusiness(String businessNo) {
        return getOptional(businessNo).orElseThrow(() -> new BusinessRuntimeException(BUSINESS_NOT_FOUND));
    }

    private void throwIfAlreadyExist(String businessNo) {
        getOptional(businessNo).ifPresent(business -> {
            throw new BusinessRuntimeException(BUSINESS_ALREADY_EXISTS);});
    }

    private void throwIfNotFound(String businessNo) {
        getOptional(businessNo).orElseThrow(() -> new BusinessRuntimeException(BUSINESS_NOT_FOUND));
    }

    public Optional<Business> getOptional(String businessNo) {
        return businessRepository.findByBusinessNo(businessNo);
    }

}
