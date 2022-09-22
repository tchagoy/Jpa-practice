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

    /** Match results of student containing the given sequence of characters in thei firsName attribute **/
    public List<Student> findByFirstNameContaining(String sequence);

    /** Match results of student by lastName **/
    public List<Student> findByLastName(String lastName);

    /** Match results of students where last name is not null (last name must exist) **/
    public List<Student> findByLastNameNotNull();

    /** This one is is a bit more obtuse; find by guardian name. Student has an object of type
     * Guardian, and guardian has an attribute/property named 'name', guessing Spring Data
     * does this: Student -> Guardian -> Name derived from the method declaration below
     */
    public List<Student> findByGuardianName(String guardianName);
}
