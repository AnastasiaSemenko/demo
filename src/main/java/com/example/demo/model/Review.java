package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "distribution_id")
    private Distribution distribution;

    @Column(name = "score")
    private int score;

    @Column(name = "comment")
    private String comment;

    public Review() {
    }

    public Review(int score, String comment) {
        this.score = score;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", distribution=" + distribution +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
