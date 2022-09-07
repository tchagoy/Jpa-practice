package com.tbmarketing.jpapractice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data /** Try not to use @Data, issues with Equals/Hashmap, this is done for example purpose only**/
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    private Long studentId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String guardianName;
    private String guardianEmail;
    private String guardianMobile;
}
