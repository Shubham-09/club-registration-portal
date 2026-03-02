package com.novus.clubregistration.service;

import com.novus.clubregistration.dto.MemberDTO;
import com.novus.clubregistration.entity.Member;
import com.novus.clubregistration.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// TODO: Mark this class as a Spring Service component to indicate that it can be injected into other components.
@Service
public class MemberService {

    // TODO: Inject the MemberRepository to interact with the database
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(MemberDTO dto) {
        // TODO: Use the repository to check if a member with this email already exists.
        // Hint: Call the derived query method you created in the repository and check if it is present.
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            // TODO: Throw a standard RuntimeException with the exact message "Email already registered!"
            throw new RuntimeException("Email already registered!");
        }

        // TODO: Create a new Member entity using the data from the DTO.
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setDeleted(false);

        // TODO: Save the newly created member to the database and return the saved entity.
        return memberRepository.save(member);
    }

    // Soft Delete Concept
    public void deleteMember(Long id) {
        // Step 1: Fetch the result from the database.
        // TODO: Use the repository's buil-in method to find an entity by its primary key.
        Optional<Member> optionalMember = memberRepository.findById(id);

        // Step 2: Check if the box is empty.
        // TODO: Check if the optionalMember does NOT contain a value.
        if (!optionalMember.isPresent()) {
            // Step 3: If it is empty, throw the error.
            throw new RuntimeException("Member not found");
        }

        // TODO: If the member exists, retrieve the actual Member object from the Optional.
        Member member = optionalMember.get();

        // Step 4: Perform the Soft Delete.
        // TODO: Set the 'isDeleted' flag to true. Do NOT actually delete the row from the database!
        member.setDeleted(true);

        // TODO: Save the updated member back to the database to persist the change.
        memberRepository.save(member);
    }

    // Get Active Members
    public List<Member> getAllActiveMembers() {
        // TODO: Call the custom JPQL query method you defined in the repository
        // to return the list of members who have NOT been soft-deleted.
        return memberRepository.findAllActiveMembers();
    }
}