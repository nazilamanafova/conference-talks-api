package com.example.demo.model.requestDto;

import lombok.Data;

@Data
public class TalkRequestDto {
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;

}
