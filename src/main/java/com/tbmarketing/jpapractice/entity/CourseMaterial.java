package com.tbmarketing.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data/** Try not to use @Data, issues with Equals/Hashmap, this is done for example purpose only**/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne /** mapping CourseMaterial CONSISTS of a Course. CourseMaterial CANNOT exists without a Course **/
    @JoinColumn(/** Which particular Column in Course Table will be designated as the Foreign Key for THIS Course **/
    /** In other words, which column will join Course and Course Material tables **/
            name = "course_id", //column name in db table
            referencedColumnName = "courseId" //attribute name in Course class
    )
    /** This Entity table will have an EXTRA column for the courseId of Course. **/
    private Course course;
}
