package com.novus.clubregistration.service;

import com.novus.clubregistration.dto.MemberDTO;
import com.novus.clubregistration.entity.Member;
import com.novus.clubregistration.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(MemberDTO dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setDeleted(false);
        return memberRepository.save(member);
    }

    // Soft Delete Concept
    public void deleteMember(Long id) {
        // Step 1: Fetch the result from the database.
        Optional<Member> optionalMember = memberRepository.findById(id);

        // Step 2: Check if the box is empty.
        if (!optionalMember.isPresent()) {
            // Step 3: If it is empty, throw the error.
            throw new RuntimeException("Member not found");
        }

        Member member = optionalMember.get();

        // Step 4: Perform the Soft Delete.
        member.setDeleted(true);

        memberRepository.save(member);
    }

    // Get Active Members
    public List<Member> getAllActiveMembers() {
        // to return the list of members who have NOT been soft-deleted.
        return memberRepository.findAllActiveMembers();
    }
}