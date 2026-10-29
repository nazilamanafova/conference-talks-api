package com.example.demo.entity;

import com.example.demo.enums.TalkStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "talks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "Talk unique identifier", example = "1")
    private Long id;

    @Schema(description = "Talk title")
    private String title;

    @Schema(description = "Talk title")
    private String room;
    private Integer durationMinutes;
    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private TalkStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

}
