package com.dev.multipledb.repositories.product;

import com.dev.multipledb.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByOwner(Long owner, Pageable pageable);
}
