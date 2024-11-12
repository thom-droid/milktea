package com.millktea.api.domain.business.controller;

import com.millktea.api.domain.business.dto.SaveBusinessReq;
import com.millktea.api.domain.business.dto.SaveBusinessRes;
import com.millktea.api.domain.business.mapper.BusinessMapper;
import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.core.domain.business.entity.Business;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Validated
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/business")
@RestController
public class BusinessController {

    private final BusinessService businessService;
    private final BusinessMapper businessMapper;

    @PostMapping
    public SaveBusinessRes save(@RequestPart @Valid SaveBusinessReq req,
                                @RequestBody MultipartFile logo) {
        Business business = businessMapper.toEntityFrom(req);
        return businessMapper.toResponseFrom(businessService.save(business, logo));
    }

    @PatchMapping
    public SaveBusinessRes update(@RequestPart @Valid SaveBusinessReq req,
                                  @RequestBody MultipartFile logo) {
        Business business = businessMapper.toEntityFrom(req);
        return businessMapper.toResponseFrom(businessService.update(business, logo));
    }

}
