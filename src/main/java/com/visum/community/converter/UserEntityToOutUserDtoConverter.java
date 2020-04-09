package com.visum.community.converter;

import com.visum.community.dto.OutUserDto;
import com.visum.community.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToOutUserDtoConverter implements Converter<UserEntity, OutUserDto> {

    @Override
    public OutUserDto convert(UserEntity entity) {
        return OutUserDto.builder()
            .id(entity.getId())
            .code(entity.getCode())
            .createdAt(entity.getCreatedAt())
            .lastUpdateAt(entity.getLastUpdateAt())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .build();
    }
}
