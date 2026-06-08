package co.istad.ecommerce.model.service;


import co.istad.ecommerce.dto.CategoryResponse;
import co.istad.ecommerce.dto.CreateCategoryRequest;
import co.istad.ecommerce.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {

    CategoryResponse createNewCategory(CreateCategoryRequest createCategoryRequest);

    Page<CategoryResponse> getCategoryByPagination(Integer pageNumber, Integer pageSize);

    Page<CategoryResponse> getSubcategoryByParentCategoryId(Integer parentCategoryId, Integer pageNumber, Integer pageSize);

    CategoryResponse getCategoryById(Integer id);

    void hardDeleteCategoryById(Integer id);

    void softDeleteCategoryById(Integer id);

    CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest);
}