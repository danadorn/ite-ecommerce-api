package co.istad.ecommerce.feature.Product;

import co.istad.ecommerce.feature.Product.dto.CreateProductRequest;
import co.istad.ecommerce.feature.Product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public abstract class ProductService {

    /**
     * Create a new product
     *
     * @param createProductRequest - is requesting data for creating product
     */

    public abstract ProductResponse createNew(CreateProductRequest createProductRequest);

    public abstract Page<ProductResponse> findAll(int pageNumber, int pageSize);
}
