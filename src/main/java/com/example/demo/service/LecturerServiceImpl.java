package com.example.demo.service;

import com.example.demo.model.Lecturer;
import com.example.demo.model.Student;
import com.example.demo.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImpl implements BaseService<Lecturer, Long> {
    @Override
    public void create(Lecturer lecturer) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(lecturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Lecturer> readAll() {
        List<Lecturer> lecturers = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            lecturers = session.createQuery("From " + Lecturer.class.getSimpleName()).list();
            session.getTransaction().commit();
        }
        return lecturers;
    }

    @Override
    public Lecturer read(Long id) {
        Lecturer lecturer = null;
        try (Session session = factory.openSession()){
            lecturer = session.get(Lecturer.class, id);
        }
        return lecturer;
    }

    @Override
    public boolean update(Lecturer lecturer, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Lecturer entity = session.get(Lecturer.class, id);
            if (entity != null) {
                entity.getAccount().setLogin(lecturer.getAccount().getLogin());
                entity.getAccount().setPassword(lecturer.getAccount().getPassword());
                entity.setName(lecturer.getName());
                entity.setSurname(lecturer.getSurname());
                entity.setPhone(lecturer.getPhone());
                entity.setEmail(lecturer.getEmail());
                entity.setEducation(lecturer.getEducation());
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
            Lecturer lecturer = session.get(Lecturer.class, id);
            if (lecturer != null) {
                session.createQuery("delete " + Lecturer.class.getSimpleName() + " where id = " + id).executeUpdate();
                session.createQuery("delete " + User.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }
}
