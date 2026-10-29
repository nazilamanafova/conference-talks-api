package com.example.demo.model.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SpeakerRequestDto {

    @NotBlank(message = "Fullname is required")
    @Size(min = 2, max = 100, message = "Fullname must be 2–100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @Size(max = 150)
    private String email;

    @Size(max = 100, message = "Company must be at most 100 characters")
    private String company;
}
