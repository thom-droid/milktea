package com.millktea.api.domain.business.service.impl;

import com.millktea.api.domain.business.mapper.BusinessMapper;
import com.millktea.api.domain.business.service.BusinessAccessData;
import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.file.FileStorageService;
import com.millktea.api.domain.user.service.UserService;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.exception.BusinessRuntimeException;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static com.millktea.core.exception.RuntimeErrorCode.BUSINESS_ALREADY_EXISTS;
import static com.millktea.core.exception.RuntimeErrorCode.BUSINESS_NOT_FOUND;

// TODO 인증 인가로직으로 유저 권한 확인
// annotation 활용
@RequiredArgsConstructor
@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessAccessData businessAccessData;
    private final FileStorageService fileStorageService;
    private final BusinessMapper businessMapper;

    @Override
    public Business save(Business business, MultipartFile image) {
        throwIfAlreadyExist(business.getBusinessNo());
        processImageIfExist(business, image);
        return businessAccessData.save(business);
    }

    @Override
    public Business update(Business source, MultipartFile image) {
        getOptional(source.getBusinessNo())
                .ifPresentOrElse(target -> {
                            businessMapper.updateEntityFromSource(target, source);
                            updateBusiness(target, image);
                        },
                        () -> {
                            throw new BusinessRuntimeException(BUSINESS_NOT_FOUND);
                        }
                );
        return source;
    }

    @Override
    public Business getOne(String businessNo) {
        return businessAccessData.get(businessNo);
    }

    @Override
    public void deactivate(Business business) {
        getOptional(business.getBusinessNo()).ifPresentOrElse(
                entity -> {
                    entity.deactivate();
                    businessAccessData.save(entity);
                },
                () -> {
                    throw new BusinessRuntimeException(BUSINESS_NOT_FOUND);
                }
        );
    }

    @Override
    public boolean containsUser(String businessNo, User user) {

        return false;
    }

    private void updateBusiness(Business business, MultipartFile image) {
        processImageIfExist(business, image);
        businessAccessData.save(business);
    }

    private Optional<Business> getOptional(String businessNo) {
        return businessAccessData.getOptional(businessNo);
    }

    private void processImageIfExist(Business business, MultipartFile img) {
        String logoSrc;
        String logoName;

        if (img != null) {
            logoSrc = fileStorageService.storeFile(img);
            logoName = img.getOriginalFilename();
            business.setLogoSrc(logoSrc);
            business.setLogoName(logoName);
        }
    }

    private void throwIfAlreadyExist(String businessNo) {
        getOptional(businessNo).ifPresent(business -> {
            throw new BusinessRuntimeException(BUSINESS_ALREADY_EXISTS);});
    }

    private void throwIfNotFound(String businessNo) {
        getOptional(businessNo).orElseThrow(() -> new BusinessRuntimeException(BUSINESS_NOT_FOUND));
    }

}
