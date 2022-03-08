package com.dba.test.cruddbatest.service;

import com.dba.test.cruddbatest.model.UserDocumentation;
import com.dba.test.cruddbatest.repository.UserDocumentationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateDocumentation {

    private UserDocumentationRepository userDocumentationRepository;
    public void update(Long id, UserDocumentation userDocumentation) throws IOException {
        Optional<UserDocumentation> optionalUserDocumentation = userDocumentationRepository.findById(id);

        if(optionalUserDocumentation.isPresent()) {
            optionalUserDocumentation.get().setDocumentType(userDocumentation.getDocumentType());
            userDocumentationRepository.save(optionalUserDocumentation.get());
        }
    }

    public void updateDoc(Long id, MultipartFile multipartFile) throws IOException {
        Optional<UserDocumentation> userDocumentation = userDocumentationRepository.findById(id);

        if(userDocumentation.isPresent()) {
            userDocumentation.get().setDocument(multipartFile.getBytes());
            userDocumentationRepository.save(userDocumentation.get());
        }
    }
}
