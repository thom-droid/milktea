package com.millktea.api.domain.file.impl;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DefaultStorageServiceImplTest {

    DefaultStorageServiceImpl defaultStorageServiceImpl = new DefaultStorageServiceImpl();

    @Test
    void storeFile() {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "test.jpg", "image/jpeg", "test".getBytes());
        assertDoesNotThrow(() -> defaultStorageServiceImpl.storeFile(mockMultipartFile));
        // when
        // then
    }

}