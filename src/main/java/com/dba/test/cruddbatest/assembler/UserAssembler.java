package com.dba.test.cruddbatest.assembler;

import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.model.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserAssembler {
    private ModelMapper modelMapper;

    public UserDto toUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> ToCollletionModel(List<User> users){
        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}
