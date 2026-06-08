package co.istad.ecommerce.model.repository;

import aj.org.objectweb.asm.commons.Remapper;
import co.istad.ecommerce.dto.CategoryResponse;
import co.istad.ecommerce.model.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);

    Page<Category> findAllByIsDeleted(boolean b, Pageable pageable);

    CategoryResponse getCategoryById(Integer id);

    Page<Category> findByIsDeletedFalse(Pageable pageable);

    Page<Category> findCategoriesByParentCategoryId(Integer parentCategoryId, Pageable page);
}
