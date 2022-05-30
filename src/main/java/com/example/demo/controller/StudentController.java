package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentServiceImpl;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class StudentController {
    private final StudentServiceImpl studentService;
    private final UserServiceImpl userService;

    @Autowired
    public StudentController(StudentServiceImpl studentService, UserServiceImpl userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @PostMapping(value = "/students")
    public ResponseEntity<?> create(@RequestBody Student student) {
        userService.create(student.getAccount());
        studentService.create(student);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/students")
    public ResponseEntity<?> read() {
        final List<Student> students = studentService.readAll();

        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> read(@PathVariable(name = "id") Long id) {
        final Student student = studentService.read(id);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
        final boolean updated = studentService.update(student, student.getAccount().getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/students/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = studentService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
