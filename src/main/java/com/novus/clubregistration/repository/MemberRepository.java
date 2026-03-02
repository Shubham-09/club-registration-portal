package com.novus.clubregistration.repository;

import com.novus.clubregistration.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// TODO: Extend the correct Spring Data interface to get standard CRUD operations.
// Hint: It takes two generic types: the Entity class and the type of its Primary Key.
public interface MemberRepository extends JpaRepository<Member, Long> {

    // TODO: Create a Derived Query Method to find a member by their email address.
    // It should return an Optional to handle cases where the email doesn't exist.
    // Hint: Spring Data JPA will write the SQL automatically based on the method name.
    Optional<Member> findByEmail(String email);

    // TODO: Write a custom JPQL Query to fetch only active members.
    // The query should select all members where the 'isDeleted' flag is false.
    // Hint: Use the @Query annotation and write JPQL (which uses Java entity names, not SQL table names).
    @Query("SELECT m FROM Member m WHERE m.isDeleted = false")
    List<Member> findAllActiveMembers();
}



