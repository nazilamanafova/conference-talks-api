package com.example.demo.mapper;

import com.example.demo.entity.Speaker;
import com.example.demo.model.requestDto.SpeakerRequestDto;
import com.example.demo.model.responseDto.SpeakerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SpeakerMapper {
    SpeakerResponseDto toDto(Speaker speaker);

    List<SpeakerResponseDto> toDtoList(List<Speaker> speakers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "talks", ignore = true)
    Speaker toEntity(SpeakerRequestDto dto);
}
