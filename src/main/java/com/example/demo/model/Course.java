package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weeks")
    private int weeks;

    @Column(name = "hours")
    private int hours;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            mappedBy = "course")
    private List<Distribution> distributions;

    public Course() {
    }

    public Course(String name, String description, int weeks, int hours) {
        this.name = name;
        this.description = description;
        this.weeks = weeks;
        this.hours = hours;
    }

    public void addDistribution(Distribution distribution){
        if (distributions == null) {
            distributions = new ArrayList<>();
        }
        distributions.add(distribution);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Distribution> getDistributions() {
        return distributions;
    }

    public void setDistribution(List<Distribution> distributions) {
        this.distributions = distributions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", lecturer=" + lecturer +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weeks=" + weeks +
                ", hours=" + hours +
                '}';
    }
}
