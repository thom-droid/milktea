package com.millktea.api.domain.business.service.impl;

import com.milktea.stub.buisness.BusinessStub;
import com.milktea.stub.helper.comparator.BusinessComparator;
import com.milktea.stub.user.UserStub;
import com.millktea.api.config.domain.business.BusinessTestConfig;
import com.millktea.api.domain.business.dto.SaveBusinessReq;
import com.millktea.api.domain.business.mapper.BusinessMapper;
import com.millktea.api.domain.business.service.BusinessService;
import com.millktea.api.domain.file.FileStorageService;
import com.millktea.core.domain.business.entity.Business;
import com.millktea.core.domain.business.entity.Status;
import com.millktea.core.domain.business.repository.BusinessRepository;
import com.millktea.core.domain.user.entity.User;
import com.millktea.core.exception.BusinessRuntimeException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static com.milktea.stub.helper.comparator.BusinessComparator.throwsIfNotTheSameObjects;
import static com.millktea.core.exception.RuntimeErrorCode.BUSINESS_ALREADY_EXISTS;
import static com.millktea.core.exception.RuntimeErrorCode.BUSINESS_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BusinessTestConfig.class)
class BusinessServiceImplTest {

    @MockBean
    BusinessRepository businessRepository;

    @MockBean
    FileStorageService fileStorageService;

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessMapper businessMapper;

    @Test
    void saveBusiness() {
        // given
        Business business = SaveBusinessReq.builder()
                .businessNo("1234")
                .representative("test")
                .addr("test")
                .email("test@example.com")
                .telephoneNumber("010-1234-5678")
                .name("test business")
                .build().toEntity();
        business.setId(1L);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //when
        when(businessRepository.findByBusinessNo(business.getBusinessNo())).thenReturn(Optional.empty());
        when(businessRepository.save(business)).thenReturn(business);
        when(fileStorageService.storeFile(mockMultipartFile)).thenReturn("test.jpg");

        //then
        Business savedBusiness = assertDoesNotThrow(() -> businessService.save(business, mockMultipartFile));
        assertThat(savedBusiness.getId()).isEqualTo(1L);
    }

    @Test
    void givenBusinessNoAlreadyExistWhenSavingThenThrows() {

        // given
        Business business = SaveBusinessReq.builder()
                .businessNo("1234")
                .representative("test")
                .addr("test")
                .email("test@example.com")
                .telephoneNumber("010-1234-5678")
                .name("test business")
                .build().toEntity();
        business.setId(1L);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //when
        //assuming that the business already exists by returning the same business object
        when(businessRepository.findByBusinessNo(business.getBusinessNo())).thenReturn(Optional.of(business));

        //then
        BusinessRuntimeException businessRuntimeException = assertThrows(BusinessRuntimeException.class, () -> businessService.save(business, mockMultipartFile));
        assertThat(businessRuntimeException.getErrorCode()).isEqualTo(BUSINESS_ALREADY_EXISTS);

    }

    @Test
    void givenNewBusinessNotExistsThenThrows() {
        //given
        Business businessStub = BusinessStub.createBusinessStub();
        when(businessRepository.findByBusinessNo(businessStub.getBusinessNo())).thenReturn(Optional.empty());
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //then
        BusinessRuntimeException businessRuntimeException = assertThrows(
                BusinessRuntimeException.class,
                () -> businessService.update(businessStub, mockMultipartFile));
        assertThat(businessRuntimeException.getErrorCode()).isEqualTo(BUSINESS_NOT_FOUND);
    }

    @Test
    void givenModifiedBusinessWithImageThenUpdateSucceeds() {
        //given
        Business entity = BusinessStub.createBusinessStub();
        Business source = Business.builder()
                .businessNo(entity.getBusinessNo())
                .representative("new representative")
                .email("newemail@example.com")
                .name("newname")
                .addr("newaddr")
                .status(Status.INACTIVE)
                .telephoneNumber("010-1234-1234")
                .build();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());

        //when
        when(businessRepository.findByBusinessNo(source.getBusinessNo())).thenReturn(Optional.of(entity));
        when(businessRepository.save(Mockito.mock(Business.class))).thenReturn(entity);
        assertDoesNotThrow(() -> businessService.update(source, mockMultipartFile));

        //then
        assertDoesNotThrow(() -> throwsIfNotTheSameObjects(entity, source));
        assertThat(entity.getLogoName()).isEqualTo(mockMultipartFile.getOriginalFilename());
    }

    @Test
    void whenDeactivateBusinessThenBusinessAndAllUsersDeactivated() {
        //given
        User representative = UserStub.createUserStub(User.Role.REPRESENTATIVE);
        User user = UserStub.createUserStub();
        Business business = BusinessStub.createBusinessWithUsersStub(List.of(representative, user));

        //when
        when(businessRepository.findByBusinessNo(business.getBusinessNo())).thenReturn(Optional.of(business));
        when(businessRepository.save(Mockito.mock(Business.class))).thenReturn(Mockito.any(Business.class));

        //then
        assertDoesNotThrow(() -> businessService.deactivate(business));
        assertThat(business.isActive()).isFalse();
        business.getUserList().forEach(u -> assertThat(u.isActive()).isFalse());
    }

}
