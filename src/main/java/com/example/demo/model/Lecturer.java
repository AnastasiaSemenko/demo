package com.example.demo.model;

import com.example.demo.model.enums.Education;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lecturers")
public class Lecturer implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private User account;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "education")
    private Education education;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            mappedBy = "lecturer")
    private List<Course> courses;

    public Lecturer() {
    }

    public Lecturer(String name, String surname, String phone, String email, Education education) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.education = education;
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setLecturer(this);
    }

    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "Account=" + account +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", education=" + education +
                '}';
    }
}
