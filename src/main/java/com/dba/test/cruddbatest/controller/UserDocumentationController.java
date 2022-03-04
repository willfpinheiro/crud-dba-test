package com.dba.test.cruddbatest.controller;


import com.dba.test.cruddbatest.assembler.UserDocumentationAssembler;
import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.model.UserDocumentation;
import com.dba.test.cruddbatest.model.dto.UserDocumentationDto;
import com.dba.test.cruddbatest.model.dto.UserDocumentationDtoSave;
import com.dba.test.cruddbatest.repository.UserDocumentationRepository;
import com.dba.test.cruddbatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/userdocumantation")
public class UserDocumentationController {
    private final UserDocumentationRepository userDocumentationReposytory;
    private UserRepository userReposytory;
    private UserDocumentationAssembler userDocumentationAssembler;


    @GetMapping
    public List<UserDocumentationDto> getAllUserDocumantations(){
        return userDocumentationAssembler.toCollectionModel(userDocumentationReposytory.findAll());
    }

    @GetMapping(value = "/{id}")
    public UserDocumentation getUserDocumentationByID(@PathVariable Long id){
        return userDocumentationReposytory.findById(id).get();
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserDocumentationDtoSave userDocumentationDtoSave) throws IOException {
        try {
            Optional<User> optionalUser = userReposytory.findById(userDocumentationDtoSave.getIdUser());
            if (!optionalUser.isPresent()) {
              throw new EntityNotFoundException("Usuario não encontrado"+ userDocumentationDtoSave.getIdUser());
//              throw new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
            }

            UserDocumentation userDocumentation = userDocumentationAssembler.toUserDocumentation(userDocumentationDtoSave);
            userDocumentation.setUser(optionalUser.get());

            userDocumentationReposytory.save(userDocumentation);
            return ResponseEntity.ok(userDocumentationReposytory.findById(userDocumentation.getId()).get());
        } catch (Exception e){
            return new ResponseEntity("Erro interno",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity putDocumentation(@PathVariable Long id, @RequestBody UserDocumentation userDocumentation) {
        try {
            updateDocumentation(id, userDocumentation);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateDocumentation(Long id, UserDocumentation userDocumentation) throws IOException {
        Optional<UserDocumentation> optionalUserDocumentation = userDocumentationReposytory.findById(id);

        if(optionalUserDocumentation.isPresent()) {
            optionalUserDocumentation.get().setDocumentType(userDocumentation.getDocumentType());
            userDocumentationReposytory.save(optionalUserDocumentation.get());
        }
    }

    @PutMapping(value = "/doc/{id}")
    public ResponseEntity responseEntity(@PathVariable Long id, @RequestPart MultipartFile multipartFile) {
        try {
            updateDoc(id, multipartFile);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateDoc(Long id, MultipartFile multipartFile) throws IOException {
        Optional<UserDocumentation> userDocumentation = userDocumentationReposytory.findById(id);

        if(userDocumentation.isPresent()) {
            userDocumentation.get().setDocument(multipartFile.getBytes());
            userDocumentationReposytory.save(userDocumentation.get());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserDocumentation(@PathVariable Long id){
        try {
            userDocumentationReposytory.deleteById(id);
            return ResponseEntity.ok("User Documentation Deleted");
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
