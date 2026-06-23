package co.istad.ecommerce.feature.Category;

import co.istad.ecommerce.feature.Category.dto.CategoryResponse;
import co.istad.ecommerce.feature.Category.dto.CreateCategoryRequest;
import co.istad.ecommerce.feature.Category.dto.UpdateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

//    @Mapping(source = "parentCategory.id", target = "parentId")
    CategoryResponse mapCategoryToCategoryResponse(Category category);

    CategoryResponse mapCategoryUpdateToCategoryResponse(UpdateCategoryRequest updateCategoryRequest);

//    void mapUpdateCategoryFromRequestToUpdateCategoryResponse(Integer id, UpdateCategoryRequest updateCategoryRequest);
}