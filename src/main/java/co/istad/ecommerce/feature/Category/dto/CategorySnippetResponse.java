package co.istad.ecommerce.feature.Category.dto;


import lombok.Builder;

@Builder
public record CategorySnippetResponse(
        Integer id,
        String name
) {
}
