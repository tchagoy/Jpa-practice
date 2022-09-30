package com.tbmarketing.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(
    )
    private Teacher teacher;

}
