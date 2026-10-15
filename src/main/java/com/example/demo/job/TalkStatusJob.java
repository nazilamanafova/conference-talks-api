package com.example.demo.job;

import com.example.demo.enums.TalkStatus;
import com.example.demo.entity.Talk;
import com.example.demo.repository.TalkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TalkStatusJob {
    private final TalkRepository talkRepository;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Baku")
    @Transactional
    public void checkAndFinishTalks() {
        List<Talk> scheduledTalks = talkRepository.findByStatus(TalkStatus.SCHEDULED);
        LocalDateTime now = LocalDateTime.now();
        int updatedCount = 0;

        for (Talk talk : scheduledTalks) {
            if (talk.getScheduledAt() != null && talk.getDurationMinutes() != null) {
                LocalDateTime endTime = talk.getScheduledAt().plusMinutes(talk.getDurationMinutes());

                if (endTime.isBefore(now)) {
                    talk.setStatus(TalkStatus.FINISHED);
                    updatedCount++;
                }
            }
        }
        if (updatedCount > 0) {
            log.info("{} talks marked as finished", updatedCount);
        } else {
            log.debug("Hec bir talk statusu deyisdiirlmedi.");
        }

    }


}
