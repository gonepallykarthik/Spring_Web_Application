package com.scaler.RESTApi.dtos;

import com.scaler.RESTApi.models.Category;
import lombok.Data;

@Data
public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private double productPrice;
    private int categoryId;
}
