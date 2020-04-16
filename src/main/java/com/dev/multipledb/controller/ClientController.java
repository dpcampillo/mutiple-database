package com.dev.multipledb.controller;

import com.dev.multipledb.exchange.ClientDTO;
import com.dev.multipledb.exchange.ProductDTO;
import com.dev.multipledb.services.ClientService;
import com.dev.multipledb.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ProductService productService;

    @GetMapping
    public Page<ClientDTO> paging(@RequestParam(name = "size", defaultValue = "10") Integer size,
                                  @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return clientService.pagingRecords(PageRequest.of(page, size));
    }

    @GetMapping("/{clientId}/products")
    public Page<ProductDTO> pagingByOwner(@PathVariable(name = "clientId") Long clientId,
                                          @RequestParam(name = "size", defaultValue = "10") Integer size,
                                          @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return productService.findByOwnerPaging(clientId, PageRequest.of(page, size));
    }

}
