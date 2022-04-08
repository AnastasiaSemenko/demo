package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Review;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements BaseService<Review, Long>{
    @Override
    public void create(Review review) {
        try (Session session = factory.openSession()) {
            session.save(review);
        }
    }

    @Override
    public List<Review> readAll() {
        List<Review> reviews = null;
        try (Session session = factory.openSession()) {
            reviews = session.createQuery("From " + Review.class.getSimpleName()).list();
        }
        return reviews;
    }

    @Override
    public Review read(Long id) {
        Review review = null;
        try (Session session = factory.openSession()){
            review = session.get(Review.class, id);
        }
        return review;
    }

    @Override
    public boolean update(Review review, Long id) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Review entity = session.get(Review.class, id);
            if (entity != null) {
                entity.setScore(review.getScore());
                entity.setComment(review.getComment());
                entity.setDistribution(review.getDistribution());
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
            Review review = session.get(Review.class, id);
            if (review != null) {
                session.createQuery("delete " + Review.class.getSimpleName() + " where id = " + id).executeUpdate();
                return true;
            }
        }
        return false;
    }
}
