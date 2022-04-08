package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Distribution;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionServiceImpl implements  BaseService<Distribution, Long>{
    @Override
    public void create(Distribution distribution) {
        try (Session session = factory.openSession()) {
            session.save(distribution);
        }
    }

    @Override
    public List<Distribution> readAll() {
        List<Distribution> distributions = null;
        try (Session session = factory.openSession()) {
            distributions = session.createQuery("From " + Distribution.class.getSimpleName()).list();
        }
        return distributions;
    }

    @Override
    public Distribution read(Long id) {
        Distribution distribution = null;
        try (Session session = factory.openSession()){
            distribution = session.get(Distribution.class, id);
        }
        return distribution;
    }

    @Override
    public boolean update(Distribution distribution, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Distribution entity = session.get(Distribution.class, id);
            if (entity != null) {
                entity.setBegin(distribution.getBegin());
                entity.setCourse(distribution.getCourse());
                entity.setStudent(distribution.getStudent());
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
            Distribution distribution = session.get(Distribution.class, id);
            if (distribution != null) {
                session.createQuery("delete " + Distribution.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }
}
