package com.dev.multipledb.services;

import com.dev.multipledb.exchange.ClientDTO;
import com.dev.multipledb.repositories.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public Page<ClientDTO> pagingRecords(Pageable pageable) {
        return clientRepository.findAll(pageable).map(mapper -> modelMapper.map(mapper, ClientDTO.class));
    }

}
