package co.istad.ecommerce.feature.file;

import co.istad.ecommerce.feature.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUploadResponse upload(@RequestPart MultipartFile file) {
        return fileUploadService.upload(file);
    }

    @PostMapping("/multiple")
    public List<FileUploadResponse> uploadMultiple(@RequestParam("files") List<MultipartFile> files) {
        return fileUploadService.uploadMultiple(files);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName (@PathVariable String name) {
        fileUploadService.deleteByName(name);
    }

}
