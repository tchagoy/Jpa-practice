package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Course;
import com.tbmarketing.jpapractice.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourseMaterial(){
        /** Need a course since CourseMaterial is JOINED by Course **/
        Course course = Course.builder()
                .title("Calculus")
                .credit(3)
                .build();

        /** There is a need to save course first, otherwise it will become a
         * transient entity. Rules applied on JOIN requires Course entity to be
         * added to course table in DB.
         * USED CASCADING, REFER TO CourseMaterial CLASS.
        //courseRepository.save(course);

        /** Create CourseMaterial entity, use above course, and save entity to
         *  course_material table
         */
        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                        .url("university/mathdepartment/calculus.edu")
                        .course(course)
                        .build();

        courseMaterialRepository.save(courseMaterial);
    }

}