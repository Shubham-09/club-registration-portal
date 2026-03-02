package com.novus.clubregistration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// TODO: Mark this class as a JPA Entity
@Entity
// TODO: Map this entity to a table named "members" in the database
@Table(name="members")
@Getter
@Setter
public class Member {

    // TODO: Define the Primary Key for this entity
    @Id
    // TODO: Set the generation strategy to IDENTITY (Auto-Increment)
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

    // TODO: Create a constructor that takes 'name' and 'email' as parameters
    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
