package com.tbmarketing.jpapractice.entity;

import lombok.*;

import javax.persistence.*;

@Data /** Try not to use @Data, issues with Equals/Hashmap, this is done for example purpose only**/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( /** will map to table; if not found will create table **/
        name = "tbl_student",
        /** create constraint(s) for table **/
        uniqueConstraints = @UniqueConstraint(
                /** name of created constraint **/
                name = "emailid_unique",
                /** mapped to column **/
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequencer",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;

    private String firstName;
    private String lastName;
    @Column( /** email_address will be name of column created/added (or reference that column if already exists) instead of emailId **/
            name = "email_address",
            /** field should not be null **/
            nullable = false
    )
    private String emailId;
    private String guardianName;
    private String guardianEmail;
    private String guardianMobile;
}
