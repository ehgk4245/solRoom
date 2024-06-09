package com.solRoom.solspring.service.mallService;

import com.solRoom.solspring.domain.mallDomain.Product;
import com.solRoom.solspring.repository.mallRepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
