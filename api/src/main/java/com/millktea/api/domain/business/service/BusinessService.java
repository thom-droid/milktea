package com.millktea.api.domain.business.service;

import com.millktea.core.domain.business.entity.Business;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessService {

    Long saveBusiness(Business business, MultipartFile image);

    Business getBusiness(String businessNo);

}
