package com.dba.test.cruddbatest.model.dto;

import com.dba.test.cruddbatest.model.DocumentType;
import com.dba.test.cruddbatest.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDocumentationDto {
    private Long id;
    private byte[] document;
    private String documentType;
    private User user;
}
