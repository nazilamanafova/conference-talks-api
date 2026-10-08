package com.example.demo.service;

import com.example.demo.entity.Speaker;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.SpeakerMapper;
import com.example.demo.mapper.TalkMapper;
import com.example.demo.model.requestDto.SpeakerRequestDto;
import com.example.demo.model.responseDto.SpeakerResponseDto;
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
public class SpeakerService {
    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;
    private final SpeakerMapper speakerMapper;
    private final TalkMapper talkMapper;

    public SpeakerResponseDto create(SpeakerRequestDto dto) {

        if (speakerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Bu email ilə speaker artıq mövcuddur: " + dto.getEmail());
        }

        Speaker speaker = speakerMapper.toEntity(dto);
        Speaker savedSpeaker = speakerRepository.save(speaker);

        return speakerMapper.toDto(savedSpeaker);
    }

    public List<SpeakerResponseDto> findAll(){
        log.info("Butun speakerlerin siyahisi sorgulanir.");
        List<Speaker>speakers = speakerRepository.findAll();

        return speakers.stream()
                .map(speakerMapper:: toDto)
                .collect(Collectors.toList());
    }
    public SpeakerResponseDto findById(Long id){
        log.info("ID-yə görə spiker sorğulanır: ID={}", id);

        Speaker speaker = speakerRepository.findById(id)
                .orElseThrow(()-> {
                    log.warn("Speaker tapilmadi. Id: " + id);
                    throw new ResourceNotFoundException("Speaker tapilmadi. Id: " + id);
                });

        return speakerMapper.toDto(speaker);
    }

    public List<TalkResponseDto> findTalksBySpeakerId(Long speakerId) {
        log.info("Spikerin çıxışları sorğulanır. Spiker ID: {}", speakerId);

        if (!speakerRepository.existsById(speakerId)) {
            log.warn("Spikerin çıxışları tapılmadı. Spiker mövcud deyil. ID: {}", speakerId);
            throw new ResourceNotFoundException("Spiker tapılmadı. ID: " + speakerId);
        }

        return talkRepository.findAllBySpeakerId(speakerId).stream()
                .map(talkMapper::toDto)
                .collect(Collectors.toList());


    }
}
