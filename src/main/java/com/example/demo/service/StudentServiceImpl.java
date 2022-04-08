package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements BaseService<Student, Long>{
    @Override
    public void create(Student student) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Student> readAll() {
        List<Student> students = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            students = session.createQuery("From " + Student.class.getSimpleName()).list();
            session.getTransaction().commit();
        }
        return students;
    }

    @Override
    public Student read(Long id) {
        Student student = null;
        try (Session session = factory.openSession()){
            student = session.get(Student.class, id);
        }
        return student;
    }

    @Override
    public boolean update(Student student, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Student entity = session.get(Student.class, id);
            if (entity != null) {
                entity.setName(student.getName());
                entity.setSurname(student.getSurname());
                entity.setPhone(student.getPhone());
                entity.setEmail(student.getEmail());
                entity.setAge(student.getAge());
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
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.createQuery("delete " + Student.class.getSimpleName() + " where id = " + id).executeUpdate();
                session.createQuery("delete " + User.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }
}
