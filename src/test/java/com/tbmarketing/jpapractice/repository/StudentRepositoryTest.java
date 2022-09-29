package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Guardian;
import com.tbmarketing.jpapractice.entity.Student;
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

    public void printStudents(Collection<Student> students, int seconds){
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

        if(students.isEmpty()) {
            System.out.println("===============================================================================================================================================================");
            System.out.println("NO STUDENTS FOUND");
            runnable.run();
        }

        else{
            for(Student student : students){
                System.out.println("===============================================================================================================================================================");
                System.out.println("FOUND STUDENT: " + student);
                runnable.run();
            }
        }
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Leonard");
        printStudents(students,2);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("no");
        printStudents(students,3);
    }

    @Test
    public void printStudentByLastName(){
        List<Student> students = studentRepository.findByLastName("Mitchell");
        printStudents(students,3);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Mitchell");
        printStudents(students,3);
    }

    @Test
    public void printStudentLastNameNotNull(){
        List<Student> students = studentRepository.findByLastNameNotNull();
        printStudents(students,0);
    }

    @Test
    public void printAllStudent(){
        List<Student> students = studentRepository.findAll();
        printStudents(students,0);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("davis.friesen@yahoo.com");
        students.add(student);
        printStudents(students,3);
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
        printStudents(students,3);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("grover.dare@hotmail.com");
        students.add(student);
        printStudents(students,3);
    }

    @Test
    public void updateStudentFirstNameByEmail(){
        String email = "junior.stehr@yahoo.com";

        Student student = studentRepository.getStudentByEmailAddress(email);

        System.out.println("Name BEFORE update: " + student.getFirstName());

        int result = studentRepository.updateStudentNameByEmailId("MIKASA",email);

        student = studentRepository.getStudentByEmailAddress(email);

        System.out.println("Name AFTER update " + student.getFirstName());

    }
}
