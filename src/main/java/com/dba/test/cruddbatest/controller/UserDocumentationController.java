package com.dba.test.cruddbatest.controller;


import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.model.UserDocumentation;
import com.dba.test.cruddbatest.model.dto.UserDocumentationDto;
import com.dba.test.cruddbatest.repository.UserDocumantationReposytory;
import com.dba.test.cruddbatest.repository.UserReposytory;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.Repository;
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
    private final UserDocumantationReposytory userDocumantationReposytory;
    private UserReposytory userReposytory;
    private ModelMapper modelMapper;


    @GetMapping
    public List<UserDocumentationDto> getAllUserDocumantations(){

        return userDocumantationReposytory.findAll()
                .stream()
                .map(this::toUserDocumentationDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public UserDocumentation getUserDocumentationByID(@PathVariable Long id){
        return userDocumantationReposytory.findById(id).get();
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserDocumentationDto userDocumentationDto) throws IOException {
        try {
            Optional<User> optionalUser = userReposytory.findById(userDocumentationDto.getIdUser());
            if (!optionalUser.isPresent()) {
              throw new EntityNotFoundException("Error aqui");
            }
            UserDocumentation userDocumentation = toUserDocumentation(userDocumentationDto);
            userDocumentation.setUser(optionalUser.get());

            userDocumantationReposytory.save(userDocumentation);
            return ResponseEntity.ok(userDocumantationReposytory.findById(userDocumentation.getId()).get());
        } catch (Exception e){
            throw e;
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity putDocumantation(@PathVariable Long id, @RequestBody UserDocumentation userDocumentation) {
        try {
            updateDocumantation(id, userDocumentation);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateDocumantation(Long id, UserDocumentation userDocumentation) throws IOException {
        Optional<UserDocumentation> optionalUserDocumentation = userDocumantationReposytory.findById(id);

        if(optionalUserDocumentation.isPresent()) {
            optionalUserDocumentation.get().setDocumentType(userDocumentation.getDocumentType());
            userDocumantationReposytory.save(optionalUserDocumentation.get());
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
        Optional<UserDocumentation> userDocumentation = userDocumantationReposytory.findById(id);

        if(userDocumentation.isPresent()) {
            userDocumentation.get().setDocument(multipartFile.getBytes());
            userDocumantationReposytory.save(userDocumentation.get());
        }
    }

    @DeleteMapping
    public void deleteUserDocumentation(@PathVariable Long id){
        try {
            userDocumantationReposytory.deleteById(id);
        } catch (Exception e){
            throw e;
        }
    }


    private UserDocumentationDto toUserDocumentationDto(UserDocumentation userDocumentation){
        return modelMapper.map(userDocumentation, UserDocumentationDto.class);
    }

    private UserDocumentation toUserDocumentation(UserDocumentationDto userDocumentationDto){
        return modelMapper.map(userDocumentationDto, UserDocumentation.class);
    }
}
