package com.intern.training.catalogmanagement.service;

import com.intern.training.catalogmanagement.entity.ProductCategory;
import com.intern.training.catalogmanagement.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository repository;

    public List<ProductCategory> getAllCategories() {
        return repository.findByDeletedFalse();
    }

    public Optional<ProductCategory> getCategoryByCode(String categoryCode) {
        return repository.findByCategoryCodeAndDeletedFalse(categoryCode);
    }

    public List<ProductCategory> getCategoriesByParent(String parentCategoryCode) {
        return repository.findByParentCategoryCodeAndDeletedFalse(parentCategoryCode);
    }

    public ProductCategory createCategory(ProductCategory category) {

        if (repository.existsByCategoryCodeAndDeletedFalse(category.getCategoryCode())) {
            throw new IllegalArgumentException(
                    "Category with code '" + category.getCategoryCode() + "' already exists"
            );
        }

        String parentCategoryCode = category.getParentCategoryCode();

        if (parentCategoryCode != null
                && !parentCategoryCode.isBlank()
                && !repository.existsByCategoryCodeAndDeletedFalse(parentCategoryCode)) {

            throw new IllegalArgumentException(
                    "Parent category with code '" + parentCategoryCode + "' does not exist"
            );
        }

        category.setDeleted(false);

        return repository.save(category);
    }

    public ProductCategory updateCategory(
            String categoryCode,
            ProductCategory updatedCategory) {

        ProductCategory existing = repository.findByCategoryCodeAndDeletedFalse(categoryCode)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Category not found: " + categoryCode
                        )
                );

        existing.setCategoryName(updatedCategory.getCategoryName());
        existing.setParentCategoryCode(
                updatedCategory.getParentCategoryCode()
        );

        return repository.save(existing);
    }

    public void deleteCategory(String categoryCode) {

        ProductCategory existing =
                repository.findByCategoryCodeAndDeletedFalse(categoryCode)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Category not found: " + categoryCode
                                )
                        );

        List<ProductCategory> children =
                repository.findByParentCategoryCodeAndDeletedFalse(categoryCode);

        if (!children.isEmpty()) {
            throw new IllegalArgumentException(
                    "Category cannot be deleted because it has children: "
                            + categoryCode
            );
        }

        existing.setDeleted(true);
        repository.save(existing);
    }

    public List<ProductCategory> getBreadcrumb(String categoryCode) {

        List<ProductCategory> breadcrumb = new ArrayList<>();

        ProductCategory current =
                repository.findByCategoryCodeAndDeletedFalse(categoryCode)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Category not found: " + categoryCode
                                )
                        );

        while (current != null) {

            breadcrumb.add(0, current);

            String parentCode = current.getParentCategoryCode();

            if (parentCode == null || parentCode.isBlank()) {
                break;
            }

            current = repository
                    .findByCategoryCodeAndDeletedFalse(parentCode)
                    .orElse(null);
        }

        return breadcrumb;
    }

    public void deleteChildren(String parentCategoryCode) {

        List<ProductCategory> children =
                repository.findByParentCategoryCodeAndDeletedFalse(parentCategoryCode);

        for (ProductCategory child : children) {
            child.setDeleted(true);
        }

        repository.saveAll(children);
    }
}