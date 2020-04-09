package com.visum.community.service;

import com.visum.community.dto.InAddCommunityMemberDto;
import com.visum.community.dto.InCommunityDto;
import com.visum.community.entity.CommunityEntity;
import com.visum.community.entity.CommunityMemberEntity;
import com.visum.community.repository.CommunityEntityRepository;
import com.visum.community.repository.CommunityMemberEntityRepository;
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
public class CommunityService {

    @Autowired
    private CommunityEntityRepository communityRepository;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private CommunityMemberEntityRepository communityMemberEntityRepository;

    @Transactional
    public CommunityEntity save(InCommunityDto dto) {
        return communityRepository.save(conversionService.convert(dto, CommunityEntity.class));
    }

    public Page<CommunityEntity> findAll(Pageable pageable) {
        return communityRepository.findAll(pageable);
    }

    public CommunityEntity findById(Long id) {
        Optional<CommunityEntity> entity = communityRepository.findById(id);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(String.format("No community with id = %s", id));
        }
        return entity.get();
    }

    public CommunityEntity findByCode(UUID code) {
        Optional<CommunityEntity> entity = communityRepository.findByCode(code);
        if (!entity.isPresent()) {
            throw new EntityNotFoundException(String.format("No community with code = %s", code));
        }
        return entity.get();
    }

    @Transactional
    public void deleteById(Long id) {
        communityRepository.delete(findById(id));
    }

    @Transactional
    public CommunityEntity update(Long id, InCommunityDto dto) {
        CommunityEntity entity = findById(id);
        entity.setLastUpdateAt(new Timestamp(System.currentTimeMillis()));
        entity.setName(dto.getName() != null ? dto.getName() : entity.getName());
        entity.setDescription(dto.getDescription() != null ? dto.getDescription() : entity.getDescription());
        return entity;
    }

    @Transactional
    public CommunityEntity addCommunityMember(InAddCommunityMemberDto dto) {
        return communityMemberEntityRepository.save(conversionService.convert(dto, CommunityMemberEntity.class)).getCommunity();
    }
}
