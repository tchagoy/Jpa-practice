package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /** create a custom repository method, will find all students by
     * attribute "firstName" of Student Entity. Spring Data will analyse
     * the method names and try to generate queries automatically (with some limitations)
     * More customized queries can be added using the @Query annotation
     */
    public List<Student> findByFirstName(String firstName);
}
