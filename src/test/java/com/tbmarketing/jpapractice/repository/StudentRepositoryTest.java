package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Guardian;
import com.tbmarketing.jpapractice.entity.Student;
import com.tbmarketing.jpapractice.loader.InfoLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    List<Student> students = new ArrayList<Student>();


    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Leonard");
        InfoLoader.printWithDelay(students, "STUDENT",2);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("no");
        InfoLoader.printWithDelay(students, "student", 3);
    }

    @Test
    public void printStudentByLastName(){
        List<Student> students = studentRepository.findByLastName("Mitchell");
        InfoLoader.printWithDelay(students,"student",3);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Mitchell");
        InfoLoader.printWithDelay(students, "student",3);
    }

    @Test
    public void printStudentLastNameNotNull(){
        List<Student> students = studentRepository.findByLastNameNotNull();
        InfoLoader.printWithDelay(students,"student",0);
    }

    @Test
    public void printAllStudent(){
        List<Student> students = studentRepository.findAll();
        InfoLoader.printWithDelay(students,"student",0);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("davis.friesen@yahoo.com");
        students.add(student);
        InfoLoader.printWithDelay(students,"student",3);
    }

    @Test
    public void printStudentNameByGuardianEmail(){
        List<String> studentNames = studentRepository.getStudentByGuardianEmail("reanna.gottlieb@gmail.com");

        for(String name : studentNames)
            System.out.println("FOUND NAME: " + name);
    }

    @Test
    public void printStudentByGuardianEmail(){
        Student student = studentRepository.getStudentByEmailAddressNative("janel.goldner@yahoo.com");
        students.add(student);
        InfoLoader.printWithDelay(students,"student",3);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("grover.dare@hotmail.com");
        students.add(student);
        InfoLoader.printWithDelay(students,"student",3);
    }

    @Test
    public void updateStudentFirstNameByEmail(){
        String email = "junior.stehr@yahoo.com";

        Student student = studentRepository.getStudentByEmailAddress(email);

        System.out.println("Name BEFORE update: " + student.getFirstName());

        int result = studentRepository.updateStudentNameByEmailId("LULU VALE CACA",email);

        student = studentRepository.getStudentByEmailAddress(email);

        System.out.println("Name AFTER update " + student.getFirstName());

    }
}
