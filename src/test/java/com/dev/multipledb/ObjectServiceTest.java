package com.dev.multipledb;

import com.dev.multipledb.entities.client.Client;
import com.dev.multipledb.entities.product.Product;
import com.dev.multipledb.exchange.ClientDTO;
import com.dev.multipledb.exchange.ProductDTO;
import com.dev.multipledb.repositories.client.ClientRepository;
import com.dev.multipledb.repositories.product.ProductRepository;
import com.dev.multipledb.services.ClientService;
import com.dev.multipledb.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ObjectServiceTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void saveClient() {
        Client client = new Client();
        client.setDocument("123456");
        client.setFirstname("Dario");
        client.setLastname("Juan");
        clientRepository.save(client);
    }

    @BeforeEach
    public void saveProduct() {
        Product product = new Product();
        product.setCode("001");
        product.setDescription("Desc");
        product.setOwner(1L);
        productRepository.save(product);
    }

    @Test
    public void testFindAllClients() {
        ModelMapper modelMapper = new ModelMapper();
        ClientService clientService = new ClientService(clientRepository, modelMapper);
        Page<ClientDTO> page = clientService.pagingRecords(PageRequest.of(0, 10));
        Assertions.assertEquals(1, page.getTotalElements());
    }

    @Test
    public void testFindProducts() {
        ModelMapper modelMapper = new ModelMapper();
        ProductService productService = new ProductService(productRepository, modelMapper);
        Page<ProductDTO> page = productService.findByOwnerPaging(1L, PageRequest.of(0, 10));
        Assertions.assertFalse(page.isEmpty());
    }

}
