package com.product.api.controller;

import com.product.api.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CtrlProduct {

    @GetMapping("/category")
    public Category[] getCategories() {
        Category[] categories = new Category[2];
        
        categories[0] = new Category(1, "Ropa", "RP", null, 1);
        categories[1] = new Category(2, "Calzado", "CLZD", 1, 1);
        
        return categories;
    }
}