package com.dev.multipledb.services;

import com.dev.multipledb.exchange.ProductDTO;
import com.dev.multipledb.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public Page<ProductDTO> findByOwnerPaging(Long owner, Pageable pageable) {
        return productRepository.findByOwner(owner, pageable).map(mapper -> modelMapper.map(mapper, ProductDTO.class));
    }

}
