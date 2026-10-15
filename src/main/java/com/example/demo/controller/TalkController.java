package com.example.demo.controller;

import com.example.demo.model.requestDto.TalkRequestDto;
import com.example.demo.model.responseDto.TalkResponseDto;
import com.example.demo.service.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/talks")
@RequiredArgsConstructor
public class TalkController {
    private final TalkService talkService;

    @PostMapping
    public ResponseEntity<TalkResponseDto> createTalk(@RequestBody TalkRequestDto dto) {

        TalkResponseDto responseDto = talkService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<TalkResponseDto>> findAll() {
        List<TalkResponseDto> responseDto = talkService.findAll();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalkResponseDto> findById(@PathVariable Long id) {
        TalkResponseDto responseDto = talkService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        talkService.deleteById(id);
    }


}
