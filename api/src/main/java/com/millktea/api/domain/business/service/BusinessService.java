package com.millktea.api.domain.business.service;

import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface BusinessService {

    Business save(Business business, MultipartFile image);

    Business getOne(String businessNo);

    Business update(Business business, MultipartFile image);

    void deactivate(Business business);

    boolean containsUser(String businessNo, User user);

}
