package com.visum.community.converter;

import com.visum.community.dto.InAddCommunityMemberDto;
import com.visum.community.entity.CommunityMemberEntity;
import com.visum.community.service.CommunityService;
import com.visum.community.service.UserService;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InAddCommunityMemberDtoToCommunityMemberEntityConverter implements Converter<InAddCommunityMemberDto, CommunityMemberEntity> {

    @Autowired @Lazy
    private CommunityService communityService;
    @Autowired @Lazy
    private UserService userService;

    @Override
    public CommunityMemberEntity convert(InAddCommunityMemberDto dto) {
        return CommunityMemberEntity.builder()
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .community(communityService.findById(dto.getCommunityId()))
            .user(userService.findById(dto.getUserId()))
            .build();
    }
}
