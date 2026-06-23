package co.istad.ecommerce.feature.Category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(

        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDeleted,
        CategoryResponse parentCategory
) {
}
