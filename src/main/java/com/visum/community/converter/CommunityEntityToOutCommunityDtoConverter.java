package com.visum.community.converter;

import com.visum.community.dto.OutCommunityDto;
import com.visum.community.dto.OutCommunityMemberDto;
import com.visum.community.entity.CommunityEntity;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommunityEntityToOutCommunityDtoConverter implements Converter<CommunityEntity, OutCommunityDto> {

    @Autowired @Lazy
    private ConversionService conversionService;

    @Override
    public OutCommunityDto convert(CommunityEntity entity) {
        return OutCommunityDto.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .createdAt(entity.getCreatedAt())
            .lastUpdateAt(entity.getLastUpdateAt())
            .name(entity.getName())
            .description(entity.getDescription())
            .members(entity.getMembers() == null ? Collections.EMPTY_LIST : entity.getMembers().stream()
                .map(communityMemberEntity -> conversionService.convert(communityMemberEntity, OutCommunityMemberDto.class))
                .collect(Collectors.toList())
            )
            .build();
    }
}
