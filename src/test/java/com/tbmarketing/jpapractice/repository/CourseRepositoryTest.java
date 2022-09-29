package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Course;
import com.tbmarketing.jpapractice.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;


@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    public void printCourses(Collection<Course> courses, int seconds){
        Runnable runnable = new Runnable() {

            int milliseconds = seconds * 1000;
            @Override
            public void run() {
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };

        if(courses.isEmpty()) {
            System.out.println("===============================================================================================================================================================");
            System.out.println("NO COURSE(S) FOUND");
            runnable.run();
        }

        else{
            for(Course course: courses){
                System.out.println("===============================================================================================================================================================");
                System.out.println("FOUND COURSE: " + course);
                runnable.run();
            }
        }
    }
    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        printCourses(courses,1);
    }
}