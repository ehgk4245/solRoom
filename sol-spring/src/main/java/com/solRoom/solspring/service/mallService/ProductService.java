package com.solRoom.solspring.service.mallService;

import com.solRoom.solspring.domain.mallDomain.Products;
import com.solRoom.solspring.repository.mallRepository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Products> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
