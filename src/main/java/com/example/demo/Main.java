package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.enums.Education;
import com.example.demo.model.enums.Roles;
import com.example.demo.service.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        StudentServiceImpl studentService = new StudentServiceImpl();
        LecturerServiceImpl lecturerService = new LecturerServiceImpl();
        CourseServiceImpl courseService = new CourseServiceImpl();
        DistributionServiceImpl distributionService = new DistributionServiceImpl();
        ReviewServiceImpl reviewService = new ReviewServiceImpl();

        //User user = new User("login004", "password", Roles.TEACHER);
        //Lecturer lecturer = new Lecturer("name001", "surname001", "phone001", "EMAIL001", Education.DOCENT);
        //Student student = new Student("Степан", "Титович", "375(25)707-01-26", "yatallesugi-3513@gmail.com", 19);
        //Course course = new Course("NAME001", "description001", 11, 11);
        Calendar date = new GregorianCalendar(2020, Calendar.OCTOBER, 15);
//        Distribution distribution = new Distribution(date);
//        Review review = new Review(6, "normal...");


    }
}