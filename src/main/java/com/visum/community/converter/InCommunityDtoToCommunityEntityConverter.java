package com.visum.community.converter;

import com.visum.common.community.dto.InCommunityDto;
import com.visum.community.entity.CommunityEntity;
import java.sql.Timestamp;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InCommunityDtoToCommunityEntityConverter implements Converter<InCommunityDto, CommunityEntity> {

    @Override
    public CommunityEntity convert(InCommunityDto dto) {
        return CommunityEntity.builder()
            .code(UUID.randomUUID())
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .name(dto.getName())
            .description(dto.getDescription())
            .build();
    }
}
