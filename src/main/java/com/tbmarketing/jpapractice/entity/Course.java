package com.tbmarketing.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data /** Try not to use @Data, issues with Equals/Hashmap, this is done for example purpose only**/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private long courseId;
    private String title;
    private Integer credit;

    @OneToOne(
            /** Since CourseMaterial is already referencing the relationship, just
             * indicate which Entity ATTRIBUTE is doing the work (in this case, the attribute
             * is "course" from CourseMaterial class).
            */
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    /** Instead of having Teacher Entity have a List<Course> they teach, it is more recommened to change
     * the relationship from:
     * Teacher teaches Courses  (@OneToMany annotation)  TO
     * Course is taught by Teacher (@ManyToOne annotation)
     * Refer to Teacher class for commented version of @OneToMany **/

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            /** Name of new table **/
            name = "student_course_map",
            /** First column in new table, for course_id, referencing
             * the Course Entity **/
            joinColumns = @JoinColumn(
                    /** name of column in table (in database) **/
                    name = "course_id",
                    /** Property/Attribute that will be used to join the columns**/
                    referencedColumnName = "courseId"
            ),
            /** Second column in new table, for student_id, referencing
             * the Student Entity **/
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )

    )
    private List<Student> students;

    /** For testing purposes. Leave all business logic in the Service layer. **/
    public void addStudent(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }

}
