package com.intern.training.catalogmanagement.repository;

import com.intern.training.catalogmanagement.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {

    List<ProductCategory> findByParentCategoryCodeAndDeletedFalse(String parentCategoryCode);

    List<ProductCategory> findByDeletedFalse();

    Optional<ProductCategory> findByCategoryCodeAndDeletedFalse(String categoryCode);

    boolean existsByCategoryCodeAndDeletedFalse(String categoryCode);
}