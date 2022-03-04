package com.dba.test.cruddbatest.assembler;

import com.dba.test.cruddbatest.model.UserDocumentation;
import com.dba.test.cruddbatest.model.dto.UserDocumentationDto;
import com.dba.test.cruddbatest.model.dto.UserDocumentationDtoSave;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserDocumentationAssembler {
    private ModelMapper modelMapper;

    public UserDocumentationDto toUserDocumentationDto(UserDocumentation userDocumentation){
        return modelMapper.map(userDocumentation, UserDocumentationDto.class);
    }

    public UserDocumentation toUserDocumentation(UserDocumentationDtoSave userDocumentationDtoSave){
        return modelMapper.map(userDocumentationDtoSave, UserDocumentation.class);
    }

    public List<UserDocumentationDto> toCollectionModel(List<UserDocumentation> userDocumentations){
        return userDocumentations.stream()
                .map(this::toUserDocumentationDto)
                .collect(Collectors.toList());
    }
}
