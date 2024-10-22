package com.scaler.RESTApi.dtos;

import lombok.Data;

@Data

public class ProductUpdateRequestDto {
    private int id;
    private String name;
    private String description;
    private int price;
}
