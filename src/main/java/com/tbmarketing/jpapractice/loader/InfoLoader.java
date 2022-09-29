package com.tbmarketing.jpapractice.loader;

import com.github.javafaker.Faker;
import com.tbmarketing.jpapractice.entity.Guardian;
import com.tbmarketing.jpapractice.entity.Student;
import com.tbmarketing.jpapractice.repository.CourseMaterialRepository;
import com.tbmarketing.jpapractice.repository.CourseRepository;
import com.tbmarketing.jpapractice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Random;

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
}
