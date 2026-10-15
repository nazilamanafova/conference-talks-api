package com.example.demo.model.responseDto;

import com.example.demo.enums.TalkStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TalkResponseDto {
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private Long speakerId;
    private String speakerName;
    private LocalDateTime scheduledAt;
    private TalkStatus status;

}
