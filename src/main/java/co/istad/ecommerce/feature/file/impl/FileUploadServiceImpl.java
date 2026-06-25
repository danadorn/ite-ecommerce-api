package co.istad.ecommerce.feature.file.impl;

import co.istad.ecommerce.feature.file.FileUpload;
import co.istad.ecommerce.feature.file.FileUploadMapper;
import co.istad.ecommerce.feature.file.FileUploadRepository;
import co.istad.ecommerce.feature.file.FileUploadService;
import co.istad.ecommerce.feature.file.dto.FileUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
    private final FileUploadMapper fileUploadMapper;
    @Value("${file.storage-location}")
    private String storageLocation;
    @Value("${file.base-uri}")
    private String baseUri;

    private final FileUploadRepository fileUploadRepository;

    public FileUploadServiceImpl(FileUploadRepository fileUploadRepository, FileUploadMapper fileUploadMapper) {
        this.fileUploadRepository = fileUploadRepository;
        this.fileUploadMapper = fileUploadMapper;
    }


    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }

    private FileUploadResponse saveFile(MultipartFile file) {
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

        // save to db
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(name);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("ISTAD");
        fileUpload.setSize(fileUpload.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.mapFileUploadToFileUploadResponse(fileUpload);
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
//        List<FileUploadResponse> responses = new java.util.ArrayList<>(List.of());
//
//        for (MultipartFile file : files) {
//            FileUploadResponse response = upload(file);
//            responses.add(response);
//        }
//        return responses;
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByName(String name) {

//        Path path = Paths.get(storageLocation + name);
//        log.info("FILE NAME: {}", path);
//        try {
//            Files.delete(path);
//        } catch (IOException exception) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to delete");
//        }
        FileUpload fileUpload = fileUploadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has been not found!"));
        fileUploadRepository.delete(fileUpload);
        Path path = Paths.get(storageLocation + name);
        try {
            boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File has been not found!");
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been deleted");
        }

    }

    @Override
    public Page<FileUploadResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<FileUpload> fileUploadResponses = fileUploadRepository.findAll(pageRequest);

        return fileUploadResponses.map(fileUploadMapper::mapFileUploadToFileUploadResponse);
    }

    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadToFileUploadResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has not been found"));
    }


}
