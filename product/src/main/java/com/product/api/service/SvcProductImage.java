package com.product.api.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;

public interface SvcProductImage {
	public ResponseEntity<List<ProductImage>> getProductImages(Integer productId);
	public ResponseEntity<String> uploadProductImage(DtoProductImageIn in);
	public ResponseEntity<String> deleteProductImage(Integer productImageId);
}