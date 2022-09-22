package com.tbmarketing.jpapractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/** Adding these properties to Student table, so it will not be an
 * Entity, because there won't be a Guardian table.
 * Instead make it embeddable with @Embeddable and add a Guardian property
 * to student, annotated with @Embedded.
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Use this annotation to map attributes to column names in table
 */
@AttributeOverrides(
        {
                /** One attribute at a time **/
                @AttributeOverride(
                        /** name of the property in this class to map **/
                        name = "name",
                        /** name of the table column to map to **/
                        column = @Column(name = "guardian_name")
                ),
                @AttributeOverride(
                        name = "email",
                        column = @Column(name = "guardian_email")
                ),
                @AttributeOverride(
                        name = "mobile",
                        column = @Column(name = "guardian_mobile")
                )
        }
)
public class Guardian {
    private String name;
    private String email;
    private String mobile;
}
