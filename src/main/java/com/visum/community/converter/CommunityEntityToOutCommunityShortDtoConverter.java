package com.visum.community.converter;

import com.visum.common.community.dto.OutCommunityShortDto;
import com.visum.community.entity.CommunityEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommunityEntityToOutCommunityShortDtoConverter implements Converter<CommunityEntity, OutCommunityShortDto> {

    @Override
    public OutCommunityShortDto convert(CommunityEntity entity) {
        return OutCommunityShortDto.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .createdAt(entity.getCreatedAt())
            .lastUpdateAt(entity.getLastUpdateAt())
            .name(entity.getName())
            .description(entity.getDescription())
            .build();
    }
}
