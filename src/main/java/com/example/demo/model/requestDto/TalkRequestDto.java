package com.example.demo.model.requestDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TalkRequestDto {
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;
    private LocalDateTime scheduledAt;
}
