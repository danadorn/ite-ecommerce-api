package co.istad.ecommerce.feature.file;

import co.istad.ecommerce.feature.file.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadMapper {

    @Value("${file.base-uri}")
    private String baseUri;

    public FileUploadResponse mapFileUploadToFileUploadResponse(FileUpload fileUpload){
        return FileUploadResponse.builder()
                .name(fileUpload.getName())
                .size(fileUpload.getSize())
                .mediaType(fileUpload.getMediaType())
                .extension(fileUpload.getExtension())
                .uri(baseUri + "/" + fileUpload.getName())
                .build();
    }
}
