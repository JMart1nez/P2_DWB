package com.product.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.dto.out.DtoProductOut;
import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT new com.product.api.dto.out.DtoProductOut("
        + "p.product_id, p.gtin, p.product, p.description, p.price, p.stock, c.category, pi.image, p.status) "
        + "FROM Product p "
        + "INNER JOIN Category c ON c.categoryId = p.category_id "
        + "LEFT JOIN ProductImage pi ON pi.productId = p.product_id "
        + "WHERE p.product_id = :product_id", nativeQuery = false)
DtoProductOut getProductDetail(@Param("product_id") Integer product_id);
}