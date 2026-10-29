package com.example.demo.model.requestDto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TalkRequestDto {
    @NotBlank(message = "title is required")
    @Size(min = 5, max = 150, message = "Title must be 5-150 characters")
    private String title;

    @NotBlank
    private String room;

    @NotNull
    @Min(15)
    @Max(90)
    private Integer durationMinutes;

    @NotNull(message = "Speaker id is required")
    @Positive(message = "Id must be positive")
    private Long speakerId;

    @NotNull
    @Future(message = "Talk must be scheduled in the future")
    private LocalDateTime scheduledAt;
}
