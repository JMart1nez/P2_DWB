package com.product.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.service.SvcProductImage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class CtrlProductImage {

	@Autowired
	SvcProductImage svc;

	@GetMapping("/{id}/image")
	public ResponseEntity<List<ProductImage>> getProductImages(@PathVariable("id") Integer id) {
		return svc.getProductImages(id);
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<String> createProductImage(@PathVariable("id") Integer id, @Valid @RequestBody DtoProductImageIn in) {
		in.setProductId(id);
		return svc.uploadProductImage(in);
	}

	@DeleteMapping("/{id}/image/{product_image_id}")
	public ResponseEntity<String> deleteProductImage(@PathVariable("id") Integer id, @PathVariable("product_image_id") Integer productImageId) {
		return svc.deleteProductImage(productImageId);
	}
}