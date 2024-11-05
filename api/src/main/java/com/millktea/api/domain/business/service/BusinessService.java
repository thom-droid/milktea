package com.millktea.api.domain.business.service;

import com.millktea.core.domain.business.entity.Business;

public interface BusinessService {

    Long saveBusiness(Business business);

    Business getBusiness(String businessNo);

}
