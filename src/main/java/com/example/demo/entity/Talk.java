package com.example.demo.entity;

import com.example.demo.enums.TalkStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "talk")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Talk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String room;
    private Integer durationMinutes;
    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private TalkStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

}
