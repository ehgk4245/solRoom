package com.solRoom.solspring.repository.mallRepository;

import com.solRoom.solspring.domain.mallDomain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByCategory(String category);
    List<Products> findByNameContainingIgnoreCase(String name);
}
