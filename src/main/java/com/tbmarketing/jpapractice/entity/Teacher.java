package com.tbmarketing.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private long teacherId;
    private String firstName;
    private String lastName;

    /**
     * It is highly recommended that @ManyToOne is used instead of
     * @OneToMany annotation.
     *
    @OneToMany(
            cascade = CascadeType.ALL
            //fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "teacher_id", //name of NEW EXTRA column in course table.
            referencedColumnName = "id" //Attribute used from Teacher to join on Course table
    )

    private List<Course> courses;
     **/
}
