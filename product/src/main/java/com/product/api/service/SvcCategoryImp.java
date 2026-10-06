package com.product.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class SvcCategoryImp implements SvcCategory {

    @Autowired
    private RepoCategory repo;

    @Override
    public List<Category> findAll() { 
        return repo.findAll(); 
    }

    @Override
    public List<Category> findActive() { 
        return repo.findByStatus(1); 
    }

    @Override
    public List<Category> findChilds(Integer id) { 
        return repo.findByParentCategoryId(id); 
    }

    @Override
    public void create(DtoCategoryIn in) {
        validateUnique(in.getCategory(), in.getTag(), null);
        validateParent(in.getParentCategoryId(), null);
        
        Category category = new Category();
        category.setCategory(in.getCategory());
        category.setTag(in.getTag());
        category.setParentCategoryId(in.getParentCategoryId());
        category.setStatus(1);
        repo.save(category);
    }

    @Override
    public void update(DtoCategoryIn in, Integer id) {
        Category category = repo.findById(id)
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "La categoría no existe"));
            
        validateUnique(in.getCategory(), in.getTag(), id);
        validateParent(in.getParentCategoryId(), id);

        category.setCategory(in.getCategory());
        category.setTag(in.getTag());
        category.setParentCategoryId(in.getParentCategoryId());
        repo.save(category);
    }

    @Override
    public void enable(Integer id) {
        Category category = repo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "No existe"));
        category.setStatus(1);
        repo.save(category);
    }

    @Override
    public void disable(Integer id) {
        Category category = repo.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "No existe"));
        if (!repo.findByParentCategoryId(id).isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "No es posible eliminar una categoría si tiene categorías hijas.");
        }
        category.setStatus(0);
        repo.save(category);
    }

    private void validateUnique(String categoryName, String tag, Integer currentId) {
        repo.findByCategory(categoryName).ifPresent(c -> {
            if (currentId == null || !c.getCategoryId().equals(currentId))
                throw new ApiException(HttpStatus.CONFLICT, "El nombre de la categoría ya está registrado");
        });
        repo.findByTag(tag).ifPresent(c -> {
            if (currentId == null || !c.getCategoryId().equals(currentId))
                throw new ApiException(HttpStatus.CONFLICT, "El tag de la categoría ya está registrado");
        });
    }

    private void validateParent(Integer parentId, Integer currentId) {
        if (parentId != null) {
            if (parentId.equals(currentId)) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Una categoría no puede ser padre de sí misma.");
            }
            Category parent = repo.findById(parentId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "La categoría padre no existe"));
            if (parent.getStatus() != 1) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "La categoría padre no está activa.");
            }
        }
    }
}