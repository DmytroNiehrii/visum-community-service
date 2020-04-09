package com.visum.community.repository;

import com.visum.community.entity.CommunityMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityMemberEntityRepository extends JpaRepository<CommunityMemberEntity, Long> {
}
