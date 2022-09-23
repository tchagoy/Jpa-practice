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
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Tomas");
        for(Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("oma");
        for(Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void printStudentByLastName(){
        List<Student> students = studentRepository.findByLastName("Saenz");
        for(Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Monado");
        for(Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void printStudentLastNameNotNull(){
        List<Student> students = studentRepository.findByLastNameNotNull();
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

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("emailexample@email.com");
        System.out.println(student);
    }

    @Test
    public void printStudentNameByGuardianEmail(){
        List<String> studentNames = studentRepository.getStudentByGuardianEmail("guardianEmail2@email.com");

        for(String name : studentNames)
            System.out.println(name);
    }

    @Test
    public void printStudentByGuardianEmail(){
        Student student = studentRepository.getStudentByEmailAddressNative("guardianEmail@email.com");
        System.out.println(student);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("guardianEmail@email.com");
        System.out.println(student);
    }

    @Test
    public void updateStudentFirstNameByEmail(){
        Student student = studentRepository.getStudentByEmailAddress("emailexample2@email.com");

        System.out.println("Name BEFORE update: " + student.getFirstName());

        int result = studentRepository.updateStudentNameByEmailId("Tony","emailexample2@email.com");

        student = studentRepository.getStudentByEmailAddress("emailexample2@email.com");

        System.out.println("Name AFTER update " + student.getFirstName());

        System.out.println("RESETING TO ORIGINAL NAME");
            result = studentRepository.updateStudentNameByEmailId("Tomas", "emailexample2@email.com") ;

    }
}
