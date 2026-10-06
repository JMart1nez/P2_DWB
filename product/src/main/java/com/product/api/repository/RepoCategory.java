package com.product.api.repository;

import com.product.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoCategory extends JpaRepository<Category, Integer> {
    
    // Método que consulta las categorías usando JPA
    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<Category> findByStatus(Integer status);
    List<Category> findByParentCategoryId(Integer parentCategoryId);
    Optional<Category> findByCategory(String category);
    Optional<Category> findByTag(String tag);
}