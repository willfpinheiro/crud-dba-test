package com.dba.test.cruddbatest.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String nome;
    private String cpf;
}
