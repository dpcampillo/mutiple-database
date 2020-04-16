package com.dev.multipledb;

import com.dev.multipledb.exchange.ClientDTO;
import com.dev.multipledb.exchange.ProductDTO;
import com.dev.multipledb.services.ClientService;
import com.dev.multipledb.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetClients() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        BDDMockito.given(clientService.pagingRecords(pageable)).willReturn(
               new PageImpl<>(Collections.singletonList(new ClientDTO(1L, "1051",
                       "Juan", "Dario")))
        );
        this.mockMvc.perform(MockMvcRequestBuilders.get("/clients").accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1));
    }

    @Test
    public void testGetOwnerProducts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        BDDMockito.given(productService.findByOwnerPaging(1L, pageable)).willReturn(
                new PageImpl<>(Collections.singletonList(new ProductDTO(1L, "1051",
                        "Juan", 1L))
        ));
        this.mockMvc.perform(MockMvcRequestBuilders.get("/clients/1/products").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1));
    }


}
