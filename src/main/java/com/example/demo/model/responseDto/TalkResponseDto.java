package com.example.demo.model.responseDto;

import lombok.Data;

@Data
public class TalkResponseDto {
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;
    private String speakerName;
}
