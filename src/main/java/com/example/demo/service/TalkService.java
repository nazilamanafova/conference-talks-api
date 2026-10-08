package com.example.demo.service;

import com.example.demo.entity.Speaker;
import com.example.demo.entity.Talk;
import com.example.demo.exception.BusinessRuleException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.TalkMapper;
import com.example.demo.model.requestDto.TalkRequestDto;
import com.example.demo.model.responseDto.TalkResponseDto;
import com.example.demo.repository.SpeakerRepository;
import com.example.demo.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TalkService {
    private final TalkRepository talkRepository;
    private final SpeakerRepository speakerRepository;
    private final TalkMapper talkMapper;

    public TalkResponseDto create(TalkRequestDto dto) {

        log.info("Yeni talk yaradilmasi sorgusu qebul edildi. title = {} ", dto.getTitle());

        if (dto.getDurationMinutes() == null || dto.getDurationMinutes() > 180) {
            log.warn("Talk yaradıla bilmedi. Talk muddeti cox uzundur: {} deqiqe", dto.getDurationMinutes());
            throw new BusinessRuleException("Talk muddeti max 180 deqiqe ola biler.");
        }

        Speaker speaker = speakerRepository.findById(dto.getSpeakerId())
                .orElseThrow(() -> {
                    log.warn("Talk Yaradila bilmedi.Speaker tapilmadi, Id:" + dto.getSpeakerId());

                    return new ResourceNotFoundException("Speaker tapilmadi," + dto.getSpeakerId());
                });
        long talkCount = talkRepository.findAll().stream()
                .filter(t -> t.getSpeaker() != null && t.getSpeaker().getId().equals(speaker.getId()))
                .count();

        if (talkCount >= 3) {
            log.warn("Talk yaradıla bilmedi. Speaker (ID: {}) artıq max sayda (3) cixis edib.", speaker.getId());
            throw new BusinessRuleException("Bir speaker maksimum 3 cixis edə biler.");
        }
        Talk talk = talkMapper.toEntity(dto);
        talk.setSpeaker(speaker);

        Talk savedTalk = talkRepository.save(talk);
        log.info("Çıxış uğurla yaradıldı. Çıxış ID: {}, Spiker ID: {}", savedTalk.getId(), speaker.getId());
        return talkMapper.toDto(savedTalk);
    }

    public List<TalkResponseDto> findAll(){
        log.debug("Bütün talklarin siyahısı databasede oxunur (findAll)\"");

        List<Talk> talks = talkRepository.findAll();
        return talks.stream()
                .map(talkMapper::toDto)
                .collect(Collectors.toList());
    }
    public TalkResponseDto findById(Long id) {
        log.debug("Id-e gore bazadan oxunur: Id= {}" + id);

        Talk talk = talkRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Talk tapilmadi: Id= {}" + id);
                    return new ResourceNotFoundException("Talk tapilmadi: Id= {}" + id);
                });

                return talkMapper.toDto(talk);

    }

    public void deleteById(Long id){
        log.info("Cixisin silinmesi sorgusu qebul edildi, Id: "+ id);

        if (!talkRepository.existsById(id)) {
            log.warn("Çıxış silinə bilmədi. Çıxış tapılmadı. ID: {}", id);
            throw new ResourceNotFoundException("Çıxış tapılmadı. ID: " + id);
        }

        talkRepository.deleteById(id);
        log.info("Çıxış uğurla silindi. ID: {}", id);
    }


}
