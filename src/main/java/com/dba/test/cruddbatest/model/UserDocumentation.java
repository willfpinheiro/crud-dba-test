package com.dba.test.cruddbatest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDocumentation {

    @Id
    @MapKey
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "document")
    private byte[] document;

    @NotNull
    @Column(name = "documentType")
    private String documentType;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;
}
