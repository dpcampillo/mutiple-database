package com.dev.multipledb;

import com.dev.multipledb.entities.client.Client;
import com.dev.multipledb.repositories.client.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void saveClient() {
        Client client = new Client();
        client.setDocument("123456");
        client.setFirstname("Dario");
        client.setLastname("Juan");
        clientRepository.save(client);
        Assertions.assertNotNull(client.getId());
    }

}
