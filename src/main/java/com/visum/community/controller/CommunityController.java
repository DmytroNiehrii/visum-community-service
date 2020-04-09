package com.visum.community.controller;

import com.visum.community.dto.InAddCommunityMemberDto;
import com.visum.community.dto.InCommunityDto;
import com.visum.community.dto.OutCommunityDto;
import com.visum.community.entity.CommunityEntity;
import com.visum.community.service.CommunityService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private CommunityService communityService;

    @PostMapping
    public ResponseEntity<OutCommunityDto> save(@RequestBody InCommunityDto dto) {
        return new ResponseEntity(conversionService.convert(communityService.save(dto), OutCommunityDto.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OutCommunityDto>> findAll(Pageable pageable) {
        return new ResponseEntity(communityService.findAll(pageable)
            .map(source -> conversionService.convert(source, OutCommunityDto.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutCommunityDto> findById(@PathVariable Long id) {
        return new ResponseEntity(conversionService.convert(communityService.findById(id), OutCommunityDto.class), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OutCommunityDto> findByCode(@PathVariable UUID code) {
        return new ResponseEntity(conversionService.convert(communityService.findByCode(code), OutCommunityDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        communityService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityEntity> update(@PathVariable Long id, @RequestBody InCommunityDto dto) {
        return new ResponseEntity(conversionService.convert(communityService.update(id, dto), OutCommunityDto.class), HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<OutCommunityDto> addCommunityMember(@RequestBody InAddCommunityMemberDto dto) {
        return new ResponseEntity(conversionService.convert(communityService.addCommunityMember(dto), OutCommunityDto.class), HttpStatus.OK);
    }
}
