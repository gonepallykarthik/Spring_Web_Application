package com.scaler.RESTApi.services;

import com.scaler.RESTApi.dtos.ProductResponseDto;
import com.scaler.RESTApi.exceptions.CategoryNotFoundException;
import com.scaler.RESTApi.exceptions.ProductNotFoundException;
import com.scaler.RESTApi.models.Category;
import com.scaler.RESTApi.models.Product;
import com.scaler.RESTApi.repositories.CategoryRepository;
import com.scaler.RESTApi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) throws ProductNotFoundException {
        Optional<Product> productOptional =  productRepository.findById(productId);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found Exception! ");
        }

        return productOptional.get();
    }

    public Product createProduct(String name, String desc, double price, int categoryId) throws CategoryNotFoundException {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isEmpty()) {
            throw new CategoryNotFoundException("Category not found");
        }

        Category category = categoryOptional.get();
        Product product = new Product();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product deleteProductById(int productId) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found Exception! ");
        }
        Product product = productOptional.get();
        productRepository.delete(product);
        return product;
    }

    public Product updateProduct(int productId, String name, String desc, double price) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found Exception! ");
        }

        Product product = productOptional.get();
        product.setName(name);
        product.setDescription(desc);
        product.setPrice(price);

        return productRepository.save(product);
    }
}
