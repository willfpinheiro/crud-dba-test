package com.dba.test.cruddbatest.model.dto;

import com.dba.test.cruddbatest.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
public class UserDocumentationDto {
    private Long id;
    private byte[] document;
    private String documentType;
    private Long user_id;
}
