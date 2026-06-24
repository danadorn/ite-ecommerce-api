package co.istad.ecommerce.feature.file;

import co.istad.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    FileUploadResponse upload(MultipartFile file);

    List<FileUploadResponse> uploadMultiple(List<MultipartFile> files);

    void deleteByName(String name);
}
