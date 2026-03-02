package com.novus.clubregistration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    // TODO: Ensure the name is not null and not empty (e.g., just whitespace)
    // Hint: Provide the custom message -> (message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be empty")
    // TODO: Enforce a minimum length of 3 characters for the name
    // Hint: Provide the custom message -> (message = "Name must be at least 3 characters")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    // TODO: Ensure the email is not null and not empty
    // Hint: Provide the custom message -> (message = "Email is required")
    @NotBlank(message = "Email is required")
    // TODO: Validate that the string is a properly formatted email address
    // Hint: Provide the custom message -> (message = "Invalid email format")
    @Email(message = "Invalid email format")
    private String email;

    // TODO: Write the Getters and Setters for all fields
    // You have 2 options:
    // 1. Manually write the getters and setters
    // 2. Use Lombok's @Getter and @Setter annotations to automatically generate them
}
