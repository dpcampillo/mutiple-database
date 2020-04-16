package com.dev.multipledb.exchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String document;
    private String firstname;
    private String lastname;
}
