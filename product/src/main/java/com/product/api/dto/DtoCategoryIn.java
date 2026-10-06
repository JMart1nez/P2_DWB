package com.product.api.dto;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DtoCategoryIn {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    @JsonProperty("category")
    private String category;

    @NotBlank(message = "El tag es obligatorio")
    @JsonProperty("tag")
    private String tag;

    @JsonProperty("parentCategoryId")
    private Integer parentCategoryId;

    public String getCategory() { 
        return category; 
    }
    public void setCategory(String category) {
        this.category = category; 
        }
    public String getTag() { 
        return tag; 
    }
    public void setTag(String tag) { 
        this.tag = tag; 
    }
    public Integer getParentCategoryId() { 
        return parentCategoryId; 
    }
    public void setParentCategoryId(Integer parentCategoryId) { 
        this.parentCategoryId = parentCategoryId; 
    }
}