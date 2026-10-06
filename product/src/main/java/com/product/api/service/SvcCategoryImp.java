package com.product.api.service;

import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {

    @Autowired
    private RepoCategory repo;

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        try {
            List<Category> categories = repo.getCategories(); // o el método que utilices para consultar las categorías
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (DataAccessException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar la base de datos: " + e.getMostSpecificCause().getMessage());
        }
    }
}