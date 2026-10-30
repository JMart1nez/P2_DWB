package com.product.api.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.product.api.dto.DtoCategoryIn;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

    @Autowired
    private SvcCategory svc;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() { 
        return ResponseEntity.ok(svc.findAll()); 
    }

    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive() { 
        return ResponseEntity.ok(svc.findActive()); 
    }

    @GetMapping("/{id}/childs")
    public ResponseEntity<List<Category>> findChilds(@PathVariable Integer id) { 
        return ResponseEntity.ok(svc.findChilds(id)); 
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DtoCategoryIn in) {
        svc.create(in);
        return ResponseEntity.ok("La categoría ha sido registrada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody DtoCategoryIn in) {
        svc.update(in, id);
        return ResponseEntity.ok("La categoría ha sido actualizada");
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enable(@PathVariable Integer id) {
        svc.enable(id);
        return ResponseEntity.ok("La categoría ha sido activada");
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disable(@PathVariable Integer id) {
        svc.disable(id);
        return ResponseEntity.ok("La categoría ha sido desactivada");
    }
}