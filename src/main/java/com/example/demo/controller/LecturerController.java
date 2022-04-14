package com.example.demo.controller;

import com.example.demo.model.Lecturer;
import com.example.demo.model.Student;
import com.example.demo.service.LecturerServiceImpl;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class LecturerController {
    private final LecturerServiceImpl lecturerService;

    @Autowired
    public LecturerController(LecturerServiceImpl lecturerService) {
        this.lecturerService = lecturerService;
    }

    @PostMapping(value = "/lecturers")
    public ResponseEntity<?> create(@RequestBody Lecturer lecturer) {
        lecturerService.create(lecturer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/lecturers")
    public ResponseEntity<?> read() {
        final List<Lecturer> lecturers = lecturerService.readAll();

        return lecturers != null && !lecturers.isEmpty()
                ? new ResponseEntity<>(lecturers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/lecturers/{id}")
    public ResponseEntity<Lecturer> read(@PathVariable(name = "id") Long id) {
        final Lecturer lecturer = lecturerService.read(id);

        return lecturer != null
                ? new ResponseEntity<>(lecturer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/lecturers")
    public ResponseEntity<?> update(@RequestBody Lecturer lecturer) {
        final boolean updated = lecturerService.update(lecturer, lecturer.getAccount().getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/lecturers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = lecturerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
