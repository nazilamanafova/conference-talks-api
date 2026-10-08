package com.example.demo.mapper;

import com.example.demo.entity.Talk;
import com.example.demo.model.requestDto.TalkRequestDto;
import com.example.demo.model.responseDto.TalkResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TalkMapper {
    @Mapping(source = "speaker.id", target = "speakerId")
    @Mapping(source = "speaker.fullName", target = "speakerName")
    TalkResponseDto toDto(Talk talk);

    List<TalkResponseDto> toDtoList(List<Talk> talks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "speaker", ignore = true)
    Talk toEntity(TalkRequestDto dto);

}
