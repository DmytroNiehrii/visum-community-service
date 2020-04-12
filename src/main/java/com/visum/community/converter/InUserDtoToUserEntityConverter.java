package com.visum.community.converter;

import com.visum.common.community.dto.InUserDto;
import com.visum.community.entity.UserEntity;
import java.sql.Timestamp;
import java.util.UUID;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InUserDtoToUserEntityConverter implements Converter<InUserDto, UserEntity> {

    @Override
    public UserEntity convert(InUserDto dto) {
        return UserEntity.builder()
            .code(UUID.randomUUID())
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .phone(dto.getPhone())
            .build();
    }
}
