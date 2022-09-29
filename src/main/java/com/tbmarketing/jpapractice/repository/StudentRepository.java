package com.tbmarketing.jpapractice.repository;

import com.tbmarketing.jpapractice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    /** ================ FETCHING DATA ========================== **/


    /** create a custom repository method, will find all students by
     * attribute "firstName" of Student Entity. Spring Data will analyse
     * the method names and try to generate queries automatically (with some limitations)
     * More customized queries can be added using the @Query annotation
     * Read more about Supported keywords inside method names here:
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
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

    /** Multiple parameters **/
    Student findByFirstNameAndLastName(String firstName, String lastName);

    /** From this point forward using the @Query annotation for
     * JPQL queries (based on attributes in the Entity class) **/

    /** JPQL EXAMPLES **/
    @Query("SELECT s FROM Student s WHERE s.emailId = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("SELECT s.firstName FROM Student s WHERE s.guardian.email = ?1")
    List<String> getStudentByGuardianEmail(String email);

    /** NATIVE QUERIES
     * For very complex queries, us native queries instead **/
    @Query(
            value = "SELECT * FROM tbl_student s WHERE s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);

    /** NAMED PARAMETERS EXAMPLE **/
    /** same as above, but with multiple named parameters **/
    @Query(
            /** Name the paramer with :paramName **/
            value = "SELECT * FROM tbl_student s WHERE s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            /** annotate with @Param("paramName") **/
            @Param("emailId") String email
            /** add more parameters as needed in the same fashion **/
    );

    /** ===================== UPDATING DATA ======================= **/

    /** make sure to add the @Modifying annotation for any UPDATE/DELETE operations (ONLY ON REPOSITORY LEVEL) **/
    @Modifying
    /** the @Transactional annotation is used here, but should be used at the SERVICE LAYER, specially when
     * multiple transactions involving multiple repositories are being made
     */
    @Transactional
    @Query(
            value = "UPDATE tbl_student SET first_name = :name " +
                    "WHERE email_address = :email",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(
            @Param("name")String name,
            @Param("email")String email
    );

}
