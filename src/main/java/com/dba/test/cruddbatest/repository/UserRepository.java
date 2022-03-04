package com.dba.test.cruddbatest.repository;

import com.dba.test.cruddbatest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
