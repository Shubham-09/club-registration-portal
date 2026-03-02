package com.novus.clubregistration.controller;

import com.novus.clubregistration.dto.MemberDTO;
import com.novus.clubregistration.entity.Member;
import com.novus.clubregistration.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody MemberDTO memberDTO, BindingResult result) {

        // 1. Check for validation errors (e.g., blank name, bad email)
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        // 2. If validation passes, proceed to register the member and wrap it in a 200 OK response
        try {
            return ResponseEntity.ok(memberService.registerMember(memberDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            memberService.deleteMember(id);

            // If successful, return a success message with a 200 OK status
            return ResponseEntity.ok("Member deleted successfully.");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Member>> getActiveMembers() {
        return ResponseEntity.ok(memberService.getAllActiveMembers());
    }
}