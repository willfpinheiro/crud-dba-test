package com.dba.test.cruddbatest.service;

import com.dba.test.cruddbatest.model.User;
import com.dba.test.cruddbatest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UpdateUserServie {

    private UserRepository userRepository;

    public void updateUser(Long id, User user) throws IOException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()) {
            optionalUser.get().setNome(user.getNome());
            optionalUser.get().setCpf(user.getCpf());
            userRepository.save(optionalUser.get());
        }
    }
}
