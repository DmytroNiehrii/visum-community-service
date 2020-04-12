package com.visum.community.service;

import com.visum.common.community.dto.InUserDto;
import com.visum.community.entity.UserEntity;
import com.visum.community.repository.UserEntityRepository;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserEntityRepository userRepository;
    @Autowired
    private ConversionService conversionService;

    @Transactional
    public UserEntity save(InUserDto dto) {
        return userRepository.save(conversionService.convert(dto, UserEntity.class));
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(String.format("No user with id = %s", id));
        }
        return entity.get();
    }

    public UserEntity findByCode(UUID code) {
        Optional<UserEntity> entity = userRepository.findByCode(code);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(String.format("No user with code = %s", code));
        }
        return entity.get();
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.delete(findById(id));
    }

    @Transactional
    public UserEntity update(Long id, InUserDto dto) {
        UserEntity entity = findById(id);
        entity.setLastUpdateAt(new Timestamp(System.currentTimeMillis()));
        entity.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : entity.getFirstName());
        entity.setLastName(dto.getLastName() != null ? dto.getLastName() : entity.getLastName());
        entity.setPhone(dto.getPhone() != null ? dto.getPhone() : entity.getPhone());
        return entity;
    }
}
