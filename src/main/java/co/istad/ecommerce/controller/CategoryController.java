package co.istad.ecommerce.controller;

import co.istad.ecommerce.auditing.BookRepository;
import co.istad.ecommerce.dto.CategoryResponse;
import co.istad.ecommerce.dto.CreateCategoryRequest;
import co.istad.ecommerce.dto.UpdateCategoryRequest;
import co.istad.ecommerce.model.repository.CategoryRepository;
import co.istad.ecommerce.model.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNewCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createNewCategory(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> getCategoryByPagination(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize) {
        return categoryService.getCategoryByPagination(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/subcategories")
    public Page<CategoryResponse> getSubcategoryByParentCategoryId(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "25") Integer pageSize) {
        return categoryService.getSubcategoryByParentCategoryId(id, pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteCategoryById(@PathVariable Integer id) {
        categoryService.hardDeleteCategoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteCategoryById(@PathVariable Integer id) {
        categoryService.softDeleteCategoryById(id);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(
            @PathVariable Integer id,
            @RequestBody UpdateCategoryRequest request) {
        return categoryService.updateCategoryById(id, request);
    }

    @GetMapping("/test")
    public String getTest
            (){
        bookRepository.updateBook(1, "yooo", "1234");
        return "test";
    }

}