package com.jt.security_database_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jt.security_database_rest.model.Student;
import com.jt.security_database_rest.repository.StudentRepo;
import com.jt.security_database_rest.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public Student addStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

}
