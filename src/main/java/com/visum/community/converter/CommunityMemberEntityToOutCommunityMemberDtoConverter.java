package com.visum.community.converter;

import com.visum.community.dto.OutCommunityMemberDto;
import com.visum.community.dto.OutUserDto;
import com.visum.community.entity.CommunityMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommunityMemberEntityToOutCommunityMemberDtoConverter implements Converter<CommunityMemberEntity, OutCommunityMemberDto> {

    @Autowired @Lazy
    private ConversionService conversionService;

    @Override
    public OutCommunityMemberDto convert(CommunityMemberEntity entity) {
        return OutCommunityMemberDto.builder()
            .id(entity.getId())
            .createdAt(entity.getCreatedAt())
            .lastUpdateAt(entity.getLastUpdateAt())
            .user(conversionService.convert(entity.getUser(), OutUserDto.class))
            .build();
    }
}
