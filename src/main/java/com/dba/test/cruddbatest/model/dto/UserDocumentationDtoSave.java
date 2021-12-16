package com.dba.test.cruddbatest.model.dto;

import com.dba.test.cruddbatest.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDocumentationDtoSave {
    private Long id;
    private byte[] document;
    private String documentType;
    private Long idUser;
    private User user;
}
