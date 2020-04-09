package com.visum.community.service;

import com.visum.community.dto.InUserDto;
import com.visum.community.entity.UserEntity;
import com.visum.community.repository.UserEntityRepository;
import java.util.Optional;
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
}
