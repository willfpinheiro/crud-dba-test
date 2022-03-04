package com.dba.test.cruddbatest.controller;

import com.dba.test.cruddbatest.assembler.UserAssembler;
import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.model.dto.UserDto;
import com.dba.test.cruddbatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/user")
public class UserController {

    private final UserRepository userReposytory;
    private UserAssembler userAssembler;

    @GetMapping
    public List<UserDto> getAllUsers(){
        return userAssembler.ToCollletionModel(userReposytory.findAll());
    }

    @GetMapping(value = "/{id}")
    public User getUserByID(@PathVariable Long id){
        return userReposytory.findById(id).get();
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        try {
            return userReposytory.save(user);
        } catch (Exception e){
            throw e;
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id){
        try {
            userReposytory.deleteById(id);
        } catch (Exception e){
            throw e;
        }
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity putUser(@PathVariable Long id, @RequestBody User user) {
        try {
            updateUser(id, user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void updateUser(Long id, User user) throws IOException {
        Optional<User> optionalUser = userReposytory.findById(id);
        if(optionalUser.isPresent()) {
            optionalUser.get().setNome(user.getNome());
            optionalUser.get().setCpf(user.getCpf());
            userReposytory.save(optionalUser.get());
        }
    }




}
