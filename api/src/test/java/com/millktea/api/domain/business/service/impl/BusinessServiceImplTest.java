package com.millktea.api.domain.business.service.impl;

import com.millktea.api.domain.business.dto.BusinessRequestDto;
import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.file.FileStorageService;
import com.millktea.api.exception.BusinessRuntimeException;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.repository.BusinessRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static com.millktea.api.exception.RuntimeErrorCode.BUSINESS_ALREADY_EXISTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BusinessServiceImpl.class)
class BusinessServiceImplTest {

    @MockBean
    BusinessRepository businessRepository;

    @MockBean
    FileStorageService fileStorageService;

    @Autowired
    BusinessService businessService;

    @Test
    void saveBusiness() {
        // given
        Business business = BusinessRequestDto.Post.builder()
                .businessNo("1234")
                .businessOwner("test")
                .addr("test")
                .email("test@example.com")
                .telephoneNumber("010-1234-5678")
                .name("test business")
                .build().toEntity();
        business.setId(1L);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //when
        Mockito.when(businessRepository.findByBusinessNo(business.getBusinessNo())).thenReturn(Optional.empty());
        Mockito.when(businessRepository.save(business)).thenReturn(business);
        Mockito.when(fileStorageService.storeFile(mockMultipartFile)).thenReturn("test.jpg");

        //then
        Long businessId = assertDoesNotThrow(() -> businessService.saveBusiness(business, mockMultipartFile));
        assertThat(businessId).isEqualTo(1L);
    }

    @Test
    void givenBusinessNoAlreadyExistWhenSavingThenThrows() {

        // given
        Business business = BusinessRequestDto.Post.builder()
                .businessNo("1234")
                .businessOwner("test")
                .addr("test")
                .email("test@example.com")
                .telephoneNumber("010-1234-5678")
                .name("test business")
                .build().toEntity();
        business.setId(1L);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //when
        //assuming that the business already exists by returning the same business object
        Mockito.when(businessRepository.findByBusinessNo(business.getBusinessNo())).thenReturn(Optional.of(business));

        //then
        BusinessRuntimeException businessRuntimeException = assertThrows(BusinessRuntimeException.class, () -> businessService.saveBusiness(business, mockMultipartFile));
        assertThat(businessRuntimeException.getErrorCode()).isEqualTo(BUSINESS_ALREADY_EXISTS);

    }

}