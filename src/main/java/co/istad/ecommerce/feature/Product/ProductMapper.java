package co.istad.ecommerce.feature.Product;

import co.istad.ecommerce.feature.Product.dto.CreateProductRequest;
import co.istad.ecommerce.feature.Product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest);

    ProductResponse mapProductToProductResponse(Product product);

}
