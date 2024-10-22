package com.scaler.RESTApi.controllers;

import com.scaler.RESTApi.dtos.ProductRequestDto;
import com.scaler.RESTApi.dtos.ProductResponseDto;
import com.scaler.RESTApi.dtos.ProductUpdateRequestDto;
import com.scaler.RESTApi.dtos.ResponseStatus;
import com.scaler.RESTApi.models.Product;
import com.scaler.RESTApi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> ListALlProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto request) {
        ProductResponseDto response = new ProductResponseDto();

        try {
            Product product = productService.createProduct(request.getProductName(), request.getProductDescription(), request.getProductPrice(), request.getCategoryId());
            response.setProduct(product);
            response.setMessage("successfully created product");
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setProduct(null);
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable int id) {
        ProductResponseDto response = new ProductResponseDto();

        try {
            Product product = productService.getProductById(id);
            response.setProduct(product);
            response.setMessage("successfully retrieved product");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setProduct(null);
            response.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> deleteProductById(@PathVariable int id) {

        ProductResponseDto response = new ProductResponseDto();

        try {
            Product prod = productService.deleteProductById(id);
            response.setProduct(prod);
            response.setMessage("successfully deleted product");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/product")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductUpdateRequestDto request) {
        ProductResponseDto response = new ProductResponseDto();
        try {
            Product prod = productService.updateProduct(request.getId(), request.getName(), request.getDescription(), request.getPrice());
            response.setProduct(prod);
            response.setMessage("successfully updated product");
            response.setResponseStatus(ResponseStatus.SUCCESS);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
