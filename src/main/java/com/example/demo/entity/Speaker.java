package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "speaker")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String company;

    @OneToMany(mappedBy = "speaker", cascade = CascadeType.ALL)
    private List<Talk> talks;
}
