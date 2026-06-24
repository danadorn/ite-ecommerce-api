package co.istad.ecommerce.feature.file.impl;

import co.istad.ecommerce.feature.file.FileUploadService;
import co.istad.ecommerce.feature.file.dto.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${file.storage-location}")
    private String storageLocation;
    @Value("${file.base-uri}")
    private String baseUri;


    @Override
    public FileUploadResponse upload(MultipartFile file) {

        // file information
        String name = UUID.randomUUID().toString();

        // include extension
        String ext = Objects.requireNonNull(file.getOriginalFilename())
                .substring(file.getOriginalFilename().lastIndexOf("."));

        // to create unique file name
        name += ext;

        // create absolute path
        Path path = Paths.get(storageLocation + name);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to upload");
        }

        return FileUploadResponse.builder()
                .name(name).size(file.getSize())
                .mediaType(file.getContentType())
                .uri(baseUri + name)
                .build();

    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {

        List<FileUploadResponse> responses = new java.util.ArrayList<>(List.of());

        for (MultipartFile file : files) {
            FileUploadResponse response = upload(file);
            responses.add(response);
        }

        return responses;
    }

    @Override
    public void deleteByName(String name) {

        Path path = Paths.get(storageLocation + name);
        log.info("FILE NAME: {}", path);
        try {
            Files.delete(path);
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to delete");
        }

    }
}
