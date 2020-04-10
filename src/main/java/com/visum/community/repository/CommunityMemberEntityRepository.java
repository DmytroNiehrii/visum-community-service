package com.visum.community.repository;

import com.visum.community.entity.CommunityEntity;
import com.visum.community.entity.CommunityMemberEntity;
import com.visum.community.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityMemberEntityRepository extends JpaRepository<CommunityMemberEntity, Long> {

    List<CommunityMemberEntity> findByCommunityAndUser(CommunityEntity communityEntity, UserEntity userEntity);
    long deleteByCommunityAndUser(CommunityEntity communityEntity, UserEntity userEntity);
}
