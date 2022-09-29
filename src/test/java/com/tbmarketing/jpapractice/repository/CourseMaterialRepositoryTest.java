package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Course;
import com.tbmarketing.jpapractice.entity.CourseMaterial;
import com.tbmarketing.jpapractice.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    public void printCourseMaterial(Collection<CourseMaterial> materials, int seconds){
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

        if(materials.isEmpty()) {
            System.out.println("===============================================================================================================================================================");
            System.out.println("NO COURSE MATERIAL(S) FOUND");
            runnable.run();
        }

        else{
            for(CourseMaterial material : materials){
                System.out.println("===============================================================================================================================================================");
                System.out.println("FOUND COURSE MATERIAL: " + material);
                runnable.run();
            }
        }
    }

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
        printCourseMaterial(courseMaterials,1);
    }

}