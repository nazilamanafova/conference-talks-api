package com.example.demo.repository;

import com.example.demo.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<Talk,Long> {
    List<Talk> findBySpeakerId(Long speakerId);

    long countBySpeakerId(Long speakerId);
    List<Talk> findAllBySpeakerId(Long speakerId);
}
