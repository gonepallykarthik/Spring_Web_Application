package com.scaler.RESTApi.dtos;

import com.scaler.RESTApi.models.Product;
import lombok.Data;


@Data
public class ProductResponseDto {
    private Product product;
    private String message;
    private ResponseStatus responseStatus;
}
