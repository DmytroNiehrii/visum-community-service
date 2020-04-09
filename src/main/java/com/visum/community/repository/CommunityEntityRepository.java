package com.visum.community.repository;

import com.visum.community.entity.CommunityEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityEntityRepository extends JpaRepository<CommunityEntity, Long> {
    Optional<CommunityEntity> findByCode(UUID code);
}
