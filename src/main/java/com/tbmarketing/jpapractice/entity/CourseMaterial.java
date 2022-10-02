package com.tbmarketing.jpapractice.entity;

import lombok.*;

import javax.persistence.*;

@Data/** Try not to use @Data, issues with Equals/Hashmap, this is done for example purpose only**/
@NoArgsConstructor
@AllArgsConstructor
@Builder
/** Do NOT include Course in toString method
 * This is done to avoid the LazyInitializationException
 * Can also use FetchType.EAGER property instead of FetchType.LAZY **/
@ToString(exclude = "course")
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

    @OneToOne( /** mapping CourseMaterial CONSISTS of a Course. CourseMaterial CANNOT exist without a Course **/
            /** Cascade gives this entity permission by parent (CourseMaterial) to use the same
             * permissions parent has. There are different types of cascade, look as needed
            */
            cascade = CascadeType.ALL,
            /** FETCH
             * EAGER -> Bring the Course data too
             * LAZY -> Only bring data for this entity **/
            fetch = FetchType.LAZY,
            /** OPTIONAL
             * Whenever a Course is saved, CourseMaterial is required.
            */
            optional = false
    )
    @JoinColumn(/** Which particular Column in Course Table will be designated as the Foreign Key for THIS Course **/
    /** In other words, which column will join Course and Course Material tables **/
            name = "course_id", //column name in db table
            referencedColumnName = "courseId" //attribute name in Course class
    )
    /** This Entity table will have an EXTRA column for the courseId of Course. **/
    private Course course;
}
