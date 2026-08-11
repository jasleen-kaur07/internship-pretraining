package com.intern.training.catalogmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductCategoryResponse {
    private String categoryCode;
    private String categoryName;
    private String parentCategoryCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
