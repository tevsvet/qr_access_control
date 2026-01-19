package com.program.qraccess.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Builder
    private Member(String firstName, String lastName, String middleName) {
        this.firstName = validateName(firstName);
        this.lastName = validateName(lastName);
        this.middleName = middleName;
    }

    public void changeFirstName(String firstName) {
        this.firstName = validateName(firstName);
    }

    public void changeLastName(String lastName) {
        this.lastName = validateName(lastName);
    }

    public void changeMiddleName(String middleName) {
        this.middleName = middleName;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        return name;
    }
}
