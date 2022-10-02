package com.tbmarketing.jpapractice.loader;

import com.github.javafaker.Faker;
import com.tbmarketing.jpapractice.entity.*;
import com.tbmarketing.jpapractice.repository.CourseMaterialRepository;
import com.tbmarketing.jpapractice.repository.CourseRepository;
import com.tbmarketing.jpapractice.repository.StudentRepository;
import com.tbmarketing.jpapractice.repository.TeacherRepository;

import java.util.*;

public class InfoLoader {

    public static void fillWithStudents(StudentRepository studentRepository, long seed, int quantity) {

        Random random = new Random(seed);

        Faker faker = new Faker(random);

        for (int i = 0; i < quantity; i++) {

            String gName = faker.name().firstName();
            String mobile = faker.phoneNumber().cellPhone().toLowerCase(Locale.ROOT);
            String gEmail = faker.internet().emailAddress();

            Guardian guardian = Guardian.builder()
                    .name(gName)
                    .email(gEmail)
                    .mobile(mobile)
                    .build();

            String name = faker.name().firstName();
            String email = faker.internet().emailAddress();
            String last = faker.name().lastName();

            Student student = Student.builder()
                    .firstName(name)
                    .lastName(last)
                    .emailId(email)
                    .guardian(guardian)
                    .build();

            studentRepository.save(student);
        }
    }

    public static void fillWithCourses(CourseMaterialRepository courseMaterialRepository, TeacherRepository teacherRepository, long seed){
        Random random = new Random(seed);
        Faker faker = new Faker(random);

        String[] courseNames = {
                "Calculus",
                "Physics",
                "Biochemistry",
                "Physical Education",
                "Boxing",
                "Sculpting",
                "Statistics",
                "Programming",
                "Painting"
        };

        for(int i = 0; i < courseNames.length; i++){
            int credit = random.nextInt(2) + 3;
            String title = courseNames[i];

            Teacher teacher = createTeacher(seed + i);

            Course course = Course.builder()
                    .credit(credit)
                    .title(title)
                    .teacher(teacher)
                    .build();

            String url = faker.internet().url();
            CourseMaterial courseMaterial = CourseMaterial.builder()
                    .url(url)
                    .course(course)
                    .build();

            courseMaterialRepository.save(courseMaterial);
        }
    }

    private static Teacher createTeacher(long seed){
        Random random = new Random(seed);
        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        Teacher teacher = Teacher.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

        return teacher;
    }


    public static void printWithDelay(Collection collection, String message, int seconds){
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

        if(collection.isEmpty()) {
            System.out.println("===============================================================================================================================================================");
            System.out.println("NO "+ message.toUpperCase() + "(S) FOUND");
            runnable.run();
        }

        else{
            for(Object object: collection){
                System.out.println("===============================================================================================================================================================");
                System.out.println("FOUND " + message.toUpperCase() + ": " + object);
                runnable.run();
            }
        }
    }
}
