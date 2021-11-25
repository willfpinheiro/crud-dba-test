package com.dba.test.cruddbatest.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @MapKey
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "cpf")
    private String cpf;

}
