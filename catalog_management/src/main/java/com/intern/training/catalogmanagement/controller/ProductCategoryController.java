package com.intern.training.catalogmanagement.controller;

import com.intern.training.catalogmanagement.dto.ProductCategoryRequest;
import com.intern.training.catalogmanagement.dto.ProductCategoryResponse;
import com.intern.training.catalogmanagement.entity.ProductCategory;
import com.intern.training.catalogmanagement.mapper.ProductCategoryMapper;
import com.intern.training.catalogmanagement.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService service;

    @GetMapping
    public ResponseEntity<List<ProductCategoryResponse>> getAllCategories() {
        List<ProductCategoryResponse> response = service.getAllCategories()
                .stream()
                .map(ProductCategoryMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryCode}/breadcrumb")
    public ResponseEntity<List<ProductCategoryResponse>> getBreadcrumb(
            @PathVariable String categoryCode) {

        List<ProductCategoryResponse> response = service.getBreadcrumb(categoryCode)
                .stream()
                .map(ProductCategoryMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryCode}")
    public ResponseEntity<ProductCategoryResponse> getCategoryByCode(
            @PathVariable String categoryCode) {

        return service.getCategoryByCode(categoryCode)
                .map(ProductCategoryMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/parent/{parentCategoryCode}")
    public ResponseEntity<List<ProductCategoryResponse>> getCategoriesByParent(
            @PathVariable String parentCategoryCode) {

        List<ProductCategoryResponse> response = service
                .getCategoriesByParent(parentCategoryCode)
                .stream()
                .map(ProductCategoryMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{categoryCode}")
    public ResponseEntity<ProductCategoryResponse> createCategory(
            @PathVariable String categoryCode,
            @RequestBody ProductCategoryRequest request) {

        ProductCategory entity = ProductCategoryMapper.toEntity(request);
        entity.setCategoryCode(categoryCode);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProductCategoryMapper.toResponse(
                        service.createCategory(entity)
                ));
    }

    @PutMapping("/{categoryCode}")
    public ResponseEntity<ProductCategoryResponse> updateCategory(
            @PathVariable String categoryCode,
            @RequestBody ProductCategoryRequest request) {

        ProductCategoryResponse response = ProductCategoryMapper.toResponse(
                service.updateCategory(
                        categoryCode,
                        ProductCategoryMapper.toEntity(request)
                )
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{categoryCode}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable String categoryCode) {

        service.deleteCategory(categoryCode);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{parentCategoryCode}/children")
    public ResponseEntity<Void> deleteChildren(
            @PathVariable String parentCategoryCode) {

        service.deleteChildren(parentCategoryCode);

        return ResponseEntity.noContent().build();
    }
}