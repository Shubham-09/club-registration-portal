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

// TODO: Mark this class as a Controller that automatically serializes returns to JSON
@RestController
// TODO: Map all endpoints in this controller to start with the base URL "/api"
@RequestMapping("/api")
public class MemberController {

    // TODO: Inject the MemberService using constructor injection
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // TODO: Handle HTTP POST requests mapped to "/register"
    @PostMapping("/register")
    // TODO: Trigger validation on the incoming data AND bind the JSON body to the MemberDTO object
    // Hint: You need two annotations here before MemberDTO
    public ResponseEntity<?> register(@Valid @RequestBody MemberDTO memberDTO, BindingResult result) {

        // 1. Check for validation errors (e.g., blank name, bad email)
        // Check if the 'result' object caught any validation errors
        if (result.hasErrors()) {
            // Return the default error message along with a 400 Bad Request HTTP status
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        // 2. If validation passes, proceed to register the member and wrap it in a 200 OK response
        try {
            return ResponseEntity.ok(memberService.registerMember(memberDTO));
        } catch (RuntimeException e) {
            // TODO: If the service throws an error (like "Email already registered"), return a 400 Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Handle HTTP DELETE requests. Map it so it expects an ID in the URL path (e.g., /api/1)
    @DeleteMapping("/{id}")
    // TODO: Extract the 'id' value from the URL path and bind it to the method parameter
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            // TODO: Call the deleteMember method from the service with the extracted ID
            memberService.deleteMember(id);

            // If successful, return a success message with a 200 OK status
            return ResponseEntity.ok("Member deleted successfully.");
        } catch (RuntimeException e) {
            // TODO: If the member is not found, return a 404 Not Found HTTP status
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // TODO: Handle HTTP GET requests to fetch the list of active members
    @GetMapping
    public ResponseEntity<List<Member>> getActiveMembers() {
        // TODO: Call the correct method from memberService and wrap it in a 200 OK response
        return ResponseEntity.ok(memberService.getAllActiveMembers());
    }
}
