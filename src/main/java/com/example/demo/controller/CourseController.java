package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CourseController {
    private final CourseServiceImpl courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/courses")
    public ResponseEntity<?> create(@RequestBody Course course) {
        courseService.create(course);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/courses")
    public ResponseEntity<?> read() {
        final List<Course> courses = courseService.readAll();

        return courses != null && !courses.isEmpty()
                ? new ResponseEntity<>(courses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/courses/{id}")
    public ResponseEntity<Course> read(@PathVariable(name = "id") Long id) {
        final Course course = courseService.read(id);

        return course != null
                ? new ResponseEntity<>(course, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/courses")
    public ResponseEntity<?> update(@RequestBody Course course) {
        final boolean updated = courseService.update(course, course.getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/courses/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = courseService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
