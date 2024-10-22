package com.jt.security_database_rest.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jt.security_database_rest.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    Optional<Student> findByUsername(String username);
}
