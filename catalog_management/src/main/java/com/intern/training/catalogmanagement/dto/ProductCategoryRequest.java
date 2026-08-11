package com.intern.training.catalogmanagement.dto;

import lombok.Data;

@Data
public class ProductCategoryRequest {
    private String categoryName;
    private String parentCategoryCode;
}
