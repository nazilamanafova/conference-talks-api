package com.example.demo.repository;

import com.example.demo.enums.TalkStatus;
import com.example.demo.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Long> {

    long countBySpeakerId(Long speakerId);

    List<Talk> findAllBySpeakerId(Long speakerId);
    List<Talk> findByStatus(TalkStatus status);

    @Query("SELECT t.room, COUNT(t) FROM Talk t GROUP BY t.room")
    List<RoomStats> getRoomStatistics();

    interface RoomStats{
        String getRoom();
        Long getCount();
    }

}
