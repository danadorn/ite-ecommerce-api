package co.istad.ecommerce.feature.Product;


import co.istad.ecommerce.feature.Product.dto.CreateProductRequest;
import co.istad.ecommerce.feature.Product.dto.ProductResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createNew(CreateProductRequest createProductRequest) {
        return productService.createNew(createProductRequest);
    }

    @GetMapping
    public Page<ProductResponse> finaAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return productService.findAll(pageNumber, pageSize);
    }
}

