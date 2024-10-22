package com.jt.security_database_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jt.security_database_rest.model.Student;
import com.jt.security_database_rest.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {

    @Autowired
    public StudentService service;

    @GetMapping("/")
    public ResponseEntity<?> getDetails(HttpServletRequest http) {
        String id = http.getSession().getId();
        return new ResponseEntity<>("Hello Welcome to Sravan ." + id, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student student2 = service.addStudent(student);
        return new ResponseEntity<>(student2, HttpStatus.CREATED);
    }
}
