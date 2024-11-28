package com.millktea.api.domain.file.impl;

import com.millktea.api.domain.file.FileStorageService;
import com.millktea.core.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import static com.millktea.core.exception.RuntimeErrorCode.*;

@Slf4j
@Service
public class DefaultStorageServiceImpl implements FileStorageService {

    private static final Path ROOT_PATH = Path.of(System.getProperty("user.dir")).resolve("uploads");

    @Override
    public String storeFile(final MultipartFile file) {
        checkFileEmpty(file);
        try (InputStream inputStream = file.getInputStream()) {
            Path targetLocation = ROOT_PATH.resolve(file.getOriginalFilename());
            makeDirectoryIfNotExists(ROOT_PATH);
            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException e) {
            log.error("error :: {}", e.getMessage());
            throw new BusinessRuntimeException(FILE_UPLOAD_FAILED);
        }
    }

    private void checkFileEmpty(MultipartFile file) {
        Optional<MultipartFile> optionalFile = Optional.ofNullable(file);
        optionalFile.orElseThrow(() -> new BusinessRuntimeException(FILE_NOT_FOUND));
        Optional.ofNullable(optionalFile.get().getOriginalFilename()).orElseThrow(() -> new BusinessRuntimeException(FILE_NOT_FOUND));
    }

    private void makeDirectoryIfNotExists(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                log.debug("directory created :: {}", path);
            } catch (IOException e) {
                log.error("error :: {}", e.getMessage());
                throw new BusinessRuntimeException(FILE_CREATING_DIRECTORY_FAILED);
            }
        }
    }
}
