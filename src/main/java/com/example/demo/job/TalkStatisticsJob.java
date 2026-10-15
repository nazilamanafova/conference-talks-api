package com.example.demo.job;

import com.example.demo.repository.SpeakerRepository;
import com.example.demo.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class TalkStatisticsJob {
    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;

    @Scheduled(fixedRateString = "${conference.report.rate-ms}", initialDelay = 10000)
    public void reportStatistics() {
        try{
            log.debug("Statistics job started");

            long speakerCount = speakerRepository.count();
            long talkCount = talkRepository.count();

            log.info("Umumi say; Spiker = {}, Çıxış = {}", speakerCount, talkCount);

            List<TalkRepository.RoomStats> roomStatsList = talkRepository.getRoomStatistics();

            for (TalkRepository.RoomStats stats : roomStatsList) {
                String roomName = stats.getRoom() == null ? "teyin edilmemis otaqlar" : stats.getRoom();

                log.info("Otaq statistikası: Otaq = '{}', Çıxış sayısı = {}", roomName, stats.getCount());

            }

        } catch (Exception exception) {
            log.error("Statistics job failed", exception);
        }


    }


}
