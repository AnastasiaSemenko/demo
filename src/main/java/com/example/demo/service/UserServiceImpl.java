package com.example.demo.service;

import com.example.demo.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements BaseService<User, Long>{
    @Override
    public void create(User user) {
        try (Session session = factory.openSession()) {
            session.save(user);

        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = null;
        try (Session session = factory.openSession()) {
            users = session.createQuery("From " + User.class.getSimpleName()).list();
        }
        return users;
    }

    @Override
    public User read(Long id) {
        User user = null;
        try (Session session = factory.openSession()){
            user = session.get(User.class, id);
        }
        return user;
    }

    @Override
    public boolean update(User user, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            User entity = session.get(User.class, id);
            if (entity != null) {
                entity.setLogin(user.getLogin());
                entity.setPassword(user.getPassword());
                entity.setRole(user.getRole());
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
            User user = session.get(User.class, id);
            if (user != null) {
                session.createQuery("delete " + User.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }

    public User readByLoginAndPassword(String login, String password) {
        User user = null;
        List<User> users = null;

        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("From " + User.class.getSimpleName() + " where login like \'" + login + "\' and password like \'" + password + "\'").list();
            if (!users.isEmpty()) {
                user = users.get(0);
            }
        }
        return user;
    }

}
