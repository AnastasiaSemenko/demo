package com.example.demo.controller;

import com.example.demo.model.Distribution;
import com.example.demo.model.Student;
import com.example.demo.service.DistributionServiceImpl;
import com.example.demo.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class DistributionController {
    private final DistributionServiceImpl distributionService;

    @Autowired
    public DistributionController(DistributionServiceImpl distributionService) {
        this.distributionService = distributionService;
    }

    @PostMapping(value = "/distributions")
    public ResponseEntity<?> create(@RequestBody Distribution distribution) {
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
