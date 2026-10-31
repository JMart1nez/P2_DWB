package com.product.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.product.api.dto.in.DtoProductImageIn;
import com.product.api.entity.ProductImage;
import com.product.api.repository.RepoProductImage;
import com.product.exception.ApiException;

@Service
public class SvcProductImageImp implements SvcProductImage {

    @Autowired
    RepoProductImage repo;

    @Value("${product.images.path}")
    private String path;

    @Override
    public ResponseEntity<List<ProductImage>> getProductImages(Integer productId) {
        return new ResponseEntity<>(repo.findByProductId(productId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> uploadProductImage(DtoProductImageIn in) {
        try {
            String base64Image = in.getImage();
            if (base64Image.contains(",")) {
                base64Image = base64Image.split(",")[1];
            }

            byte[] data = Base64.getDecoder().decode(base64Image);
            String fileName = UUID.randomUUID().toString() + ".jpeg";
            String filePath = path + fileName;
            
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try (OutputStream stream = new FileOutputStream(filePath)) {
                stream.write(data);
            }

            ProductImage productImage = new ProductImage();
            productImage.setProductId(in.getProductId());
            productImage.setImage(fileName);
            productImage.setStatus(1);
            repo.save(productImage);

            return new ResponseEntity<>("La imagen ha sido registrada", HttpStatus.OK);

        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al procesar la imagen: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteProductImage(Integer productImageId) {
        if (!repo.existsById(productImageId)) {
            throw new ApiException(HttpStatus.NOT_FOUND, "El id de la imagen no existe");
        }
        repo.deleteById(productImageId);
        return new ResponseEntity<>("La imagen ha sido eliminada", HttpStatus.OK);
    }
}