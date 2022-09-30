package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Course;
import com.tbmarketing.jpapractice.entity.CourseMaterial;
import com.tbmarketing.jpapractice.entity.Student;
import com.tbmarketing.jpapractice.loader.InfoLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

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

    @Test
    public void printAllCourseMaterial(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        InfoLoader.printWithDelay(courseMaterials, "course material",3);
    }

}