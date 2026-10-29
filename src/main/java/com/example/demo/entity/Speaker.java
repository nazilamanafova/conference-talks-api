package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "speakers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "Speaker unique identifier", example = "1")
    private Long id;

    @Schema(description = "Product name")
    private String fullName;

    @Schema(description = "Speaker email")
    private String email;

    @Schema(description = "Speaker company")
    private String company;

    @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL)
    private List<Talk> talks;
}
