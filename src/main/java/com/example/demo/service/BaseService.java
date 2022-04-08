package com.example.demo.service;

import com.example.demo.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public interface BaseService<T, PK> {
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Lecturer.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Distribution.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();

    void create(T entity);
    List<T> readAll();
    T read(PK primaryKey);
    boolean update(T entity, PK primaryKey);
    boolean delete(PK primaryKey);
}
