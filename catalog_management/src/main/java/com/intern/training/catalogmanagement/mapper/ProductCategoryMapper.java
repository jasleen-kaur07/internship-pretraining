package com.intern.training.catalogmanagement.mapper;

import com.intern.training.catalogmanagement.dto.ProductCategoryRequest;
import com.intern.training.catalogmanagement.dto.ProductCategoryResponse;
import com.intern.training.catalogmanagement.entity.ProductCategory;

public class ProductCategoryMapper {

    public static ProductCategory toEntity(ProductCategoryRequest request) {
        return ProductCategory.builder()
                .categoryName(request.getCategoryName())
                .parentCategoryCode(request.getParentCategoryCode())
                .build();
    }

    public static ProductCategoryResponse toResponse(ProductCategory entity) {
        return ProductCategoryResponse.builder()
                .categoryCode(entity.getCategoryCode())
                .categoryName(entity.getCategoryName())
                .parentCategoryCode(entity.getParentCategoryCode())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    private ProductCategoryMapper() {}
}
