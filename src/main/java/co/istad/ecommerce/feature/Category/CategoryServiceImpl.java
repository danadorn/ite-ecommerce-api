package co.istad.ecommerce.feature.Category;


import co.istad.ecommerce.feature.Category.dto.CategoryResponse;
import co.istad.ecommerce.feature.Category.dto.CreateCategoryRequest;
import co.istad.ecommerce.feature.Category.dto.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;




    @Override
    public CategoryResponse createNewCategory(CreateCategoryRequest createCategoryRequest) {

        log.info("Create New Category : {}",createCategoryRequest);

        //validate category name
        Boolean isExisting = categoryRepository.existsByName(createCategoryRequest.name());

        if (isExisting){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Category has already been used"
            );
        }
        //validate parent category
        Category parentCategory = null;
        if (createCategoryRequest.parentCategoryId() != null){
            parentCategory = categoryRepository.findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Parent Category has not been found"));
        }

        Category category = categoryMapper.mapCreateCategoryRequestToCategory(createCategoryRequest);
        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);
        category = categoryRepository.save(category);

        return  categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getCategoryByPagination(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Category>categories=  categoryRepository.findByIsDeletedFalse(pageable);
//
//        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.DESC,"id"));
//        Page<Category> categoryPage = categoryRepository.findAllByIsDeleted(false,pageable);
//
//        return categoryPage.map(categoryMapper::mapCategoryToCategoryResponse);
        return  categories.map(categoryMapper::mapCategoryToCategoryResponse);
//
//        categoryRepository.findByIsDeleteFalse(pageable) //take data from database
//                .map(categoryMapper::mapCategoryToCategoryResponse);
    }


    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category has not been found"));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public Page<CategoryResponse> getSubcategoryByParentCategoryId(Integer parentCategoryId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> cate = categoryRepository.findCategoriesByParentCategoryId(parentCategoryId,pageable);
        return cate.map(categoryMapper::mapCategoryToCategoryResponse);
    }

    @Override
    public void hardDeleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category has not been found"));
        categoryRepository.delete(category);
    }

    @Override
    public void softDeleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Category has not been found"));
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, UpdateCategoryRequest updateCategoryRequest) {

        Category category = categoryRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Category not found with this id."));
        if(category.getIsDeleted()==true){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This category is already deleted.");
        }
        if(updateCategoryRequest.name() !=null){
            category.setName(updateCategoryRequest.name());
        }
        if(updateCategoryRequest.description()!=null){
            category.setDescription(updateCategoryRequest.description());
        }
        if(updateCategoryRequest.icon()!=null){
            category.setIcon(updateCategoryRequest.icon());
        }
//        categoryMapper.mapCategoryUpdateToCategoryResponse( updateCategoryRequest);
        return categoryMapper.mapCategoryToCategoryResponse(categoryRepository.save(category));    }


}