package com.example.demo.controller;

import com.example.demo.model.Distribution;
import com.example.demo.service.CourseServiceImpl;
import com.example.demo.service.DistributionServiceImpl;
import com.example.demo.service.LecturerServiceImpl;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class DistributionController {
    private final DistributionServiceImpl distributionService;
    private final StudentServiceImpl studentService;
    private final CourseServiceImpl courseService;
    private final LecturerServiceImpl lecturerService;

    @Autowired
    public DistributionController(DistributionServiceImpl distributionService, StudentServiceImpl studentService, CourseServiceImpl courseService, LecturerServiceImpl lecturerService) {
        this.distributionService = distributionService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.lecturerService = lecturerService;
    }

    @PostMapping(value = "/distributions")
    public ResponseEntity<?> create(@RequestBody Distribution distribution) {
        distribution.setStudent(studentService.read(distribution.getStudent().getAccount().getId()));
        Long lecturerId = distribution.getCourse().getLecturer().getAccount().getId();
        distribution.setCourse(courseService.read(distribution.getCourse().getId()));
        distribution.getCourse().setLecturer(lecturerService.read(lecturerId));
        Calendar date = new GregorianCalendar(2022, Calendar.SEPTEMBER, 1);
        distribution.setBegin(date);
        distributionService.create(distribution);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/distributions")
    public ResponseEntity<?> read() {
        final List<Distribution> distributions = distributionService.readAll();

        return distributions != null && !distributions.isEmpty()
                ? new ResponseEntity<>(distributions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/distributions/by/student/{id}")
    public ResponseEntity<?> readByStudent(@PathVariable(name = "id") Long id) {
        final List<Distribution> distributions = distributionService.readByStudent(id);

        return distributions != null && !distributions.isEmpty()
                ? new ResponseEntity<>(distributions, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/distributions/{id}")
    public ResponseEntity<Distribution> read(@PathVariable(name = "id") Long id) {
        final Distribution distribution = distributionService.read(id);

        return distribution != null
                ? new ResponseEntity<>(distribution, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/distributions")
    public ResponseEntity<?> update(@RequestBody Distribution distribution) {
        final boolean updated = distributionService.update(distribution, distribution.getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/distributions/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = distributionService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
