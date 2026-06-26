package co.istad.ecommerce.feature.Product;

import co.istad.ecommerce.feature.Product.dto.CreateProductRequest;
import co.istad.ecommerce.feature.Product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    /**
     * Create a new product
     *
     * @param createProductRequest - is requesting data for creating product
     */

    ProductResponse createNew(CreateProductRequest createProductRequest);

    Page<ProductResponse> findAll(int pageNumber, int pageSize);
}
