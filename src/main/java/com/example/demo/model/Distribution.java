package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "distribution")
public class Distribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "begin")
    @Temporal(TemporalType.DATE)
    private Calendar begin;

    public Distribution() {
    }

    public Distribution(Calendar begin) {
        this.begin = begin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Calendar getBegin() {
        return begin;
    }

    public void setBegin(Calendar begin) {
        this.begin = begin;
    }

    @Override
    public String toString() {
        return "Distribution{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", begin=" + begin +
                '}';
    }
}
