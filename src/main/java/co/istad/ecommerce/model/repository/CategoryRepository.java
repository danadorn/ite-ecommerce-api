package co.istad.ecommerce.model.repository;

import co.istad.ecommerce.dto.CategoryResponse;
import co.istad.ecommerce.model.domain.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> , JpaSpecificationExecutor {

    boolean existsByName(String name);

    Page<Category> findAllByIsDeleted(boolean b, Pageable pageable);

    CategoryResponse getCategoryById(Integer id);

    Page<Category> findByIsDeletedFalse(Pageable pageable);

    Page<Category> findCategoriesByParentCategoryId(Integer parentCategoryId, Pageable page);

//    @Modifying
    @Transactional
    @Query("""
    UPDATE Category c
    SET c.name = :name,
        c.description = :description,
        c.icon = :icon
    WHERE c.id = :id
""")
    void updateCategory(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("icon") String icon
    );
}
