package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Guardian;
import com.tbmarketing.jpapractice.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Guardian guardian = Guardian.builder()
                .email("guardianEmail2@email.com")
                .name("guardianName")
                .mobile("18008888")
                .build();

        Student student = Student.builder()
                .emailId("emailexample2@email.com")
                .firstName("firstName")
                .lastName("lastName")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentsByFirstName(){
        List<Student> students = studentRepository.findByFirstName("firstName");
        for(Student student : students){
            System.out.println(student);
        }
    }
    @Test
    public void printAllStudent(){
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }


}
