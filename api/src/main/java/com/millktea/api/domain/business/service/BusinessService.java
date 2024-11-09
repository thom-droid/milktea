package com.millktea.api.domain.business.service;

import com.millktea.core.domain.business.entity.Business;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessService {

    Long save(Business business, MultipartFile image);

    Business getOne(String businessNo);

    Long update(Business business, MultipartFile image);

}
