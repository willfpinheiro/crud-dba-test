package com.dba.test.cruddbatest.controller;

import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.model.dto.UserDto;
import com.dba.test.cruddbatest.repository.UserReposytory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/user")
public class UserController {

    private final UserReposytory userReposytory;
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserDto> getAllUsers(){

        return userReposytory.findAll()
                .stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
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

    public UserDto toUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

}
