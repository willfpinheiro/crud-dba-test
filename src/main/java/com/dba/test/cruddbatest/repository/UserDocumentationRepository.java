package com.dba.test.cruddbatest.repository;

import com.dba.test.cruddbatest.model.UserDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDocumentationRepository extends JpaRepository<UserDocumentation, Long> {
}
