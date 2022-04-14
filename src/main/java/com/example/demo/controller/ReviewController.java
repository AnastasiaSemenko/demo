package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ReviewController {
    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/reviews")
    public ResponseEntity<?> create(@RequestBody Review review) {
        reviewService.create(review);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/reviews")
    public ResponseEntity<?> read() {
        final List<Review> reviews = reviewService.readAll();

        return reviews != null && !reviews.isEmpty()
                ? new ResponseEntity<>(reviews, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/reviews/{id}")
    public ResponseEntity<Review> read(@PathVariable(name = "id") Long id) {
        final Review review = reviewService.read(id);

        return review != null
                ? new ResponseEntity<>(review, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/reviews")
    public ResponseEntity<?> update(@RequestBody Review review) {
        final boolean updated = reviewService.update(review, review.getId());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/reviews/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = reviewService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
