package com.example.demo.controller;

import com.example.demo.model.Lecturer;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.model.enums.Education;
import com.example.demo.model.enums.Roles;
import com.example.demo.service.LecturerServiceImpl;
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
public class UserController {
    private final UserServiceImpl userService;
    private final StudentServiceImpl studentService;
    private final LecturerServiceImpl lecturerService;

    @Autowired
    public UserController(UserServiceImpl userService, StudentServiceImpl studentService, LecturerServiceImpl lecturerService) {
        this.userService = userService;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        switch (user.getRole()) {
            case STUDENT:
                Student student = new Student();
                student.setAccount(user);
                student.setAge(0);
                student.setEmail("def");
                student.setPhone("def");
                student.setSurname("def");
                student.setName("def");
                studentService.create(student);
                break;
            case TEACHER:
                Lecturer lecturer = new Lecturer();
                lecturer.setAccount(user);
                lecturer.setEducation(Education.ASSISTANT);
                lecturer.setEmail("def");
                lecturer.setPhone("def");
                lecturer.setSurname("def");
                lecturer.setName("def");
                lecturerService.create(lecturer);
                break;
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<?> read() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<?> read(@RequestParam("login") String login, @RequestParam("password") String password) {
        User user = userService.readByLoginAndPassword(login, password);
        Student student;
        Lecturer lecturer;
        if (user != null) {
            switch (user.getRole()) {
                case STUDENT:
                    student = studentService.read(user.getId());
                    return new ResponseEntity<>(student, HttpStatus.OK);
                case TEACHER:
                    lecturer = lecturerService.read(user.getId());
                    return new ResponseEntity<>(lecturer, HttpStatus.OK);
                case ADMIN:
                    return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") Long id) {
        final User user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping(value = "/users")
    public ResponseEntity<?> update(@RequestBody User user) {
        final boolean updated = userService.update(user, user.getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
