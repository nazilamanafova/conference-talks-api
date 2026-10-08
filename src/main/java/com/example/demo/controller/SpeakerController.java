package com.example.demo.controller;

import com.example.demo.model.requestDto.SpeakerRequestDto;
import com.example.demo.model.responseDto.SpeakerResponseDto;
import com.example.demo.model.responseDto.TalkResponseDto;
import com.example.demo.service.SpeakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/speakers")
@RequiredArgsConstructor
public class SpeakerController {

    private final SpeakerService speakerService;

    @PostMapping
    public ResponseEntity<SpeakerResponseDto> createSpeaker(@RequestBody SpeakerRequestDto dto){

        SpeakerResponseDto response = speakerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SpeakerResponseDto>> findAll(){
        List<SpeakerResponseDto> response = speakerService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpeakerResponseDto> findById(@PathVariable Long id){
        SpeakerResponseDto response = speakerService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/talks")
    public ResponseEntity<List<TalkResponseDto>> getTalksBySpeakerId(@PathVariable Long id) {
        List<TalkResponseDto> response = speakerService.findTalksBySpeakerId(id);
        return ResponseEntity.ok(response);
    }




}
