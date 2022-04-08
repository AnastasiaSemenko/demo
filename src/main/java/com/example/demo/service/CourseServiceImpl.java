package com.example.demo.service;

import com.example.demo.model.Course;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements BaseService<Course, Long>{
    @Override
    public void create(Course course) {
        try (Session session = factory.openSession()) {
            session.save(course);
        }
    }

    @Override
    public List<Course> readAll() {
        List<Course> courses = null;
        try (Session session = factory.openSession()) {
            courses = session.createQuery("From " + Course.class.getSimpleName()).list();
        }
        return courses;
    }

    @Override
    public Course read(Long id) {
        Course course = null;
        try (Session session = factory.openSession()){
            course = session.get(Course.class, id);
        }
        return course;
    }

    @Override
    public boolean update(Course course, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Course entity = session.get(Course.class, id);
            if (entity != null) {
                entity.setName(course.getName());
                entity.setDescription(course.getDescription());
                entity.setWeeks(course.getWeeks());
                entity.setHours(course.getHours());
                entity.setLecturer(course.getLecturer());
                session.getTransaction().commit();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.createQuery("delete " + Course.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }
}
