package com.novus.clubregistration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="members")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // Soft Delete Concept: true if deleted, false otherwise
    private boolean isDeleted = false;

    // CONSTRUCTORS:
    // No-args constructor is required by JPA
    public Member() {

    }
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}