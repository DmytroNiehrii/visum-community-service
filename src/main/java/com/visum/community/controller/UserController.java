package com.visum.community.controller;

import com.visum.common.community.dto.InUserDto;
import com.visum.common.community.dto.OutUserDto;
import com.visum.community.entity.UserEntity;
import com.visum.community.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<OutUserDto> save(@RequestBody InUserDto dto) {
        return new ResponseEntity(conversionService.convert(userService.save(dto), OutUserDto.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OutUserDto>> findAll(Pageable pageable) {
        return new ResponseEntity(userService.findAll(pageable)
            .map(source -> conversionService.convert(source, OutUserDto.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutUserDto> findById(@PathVariable Long id) {
        return new ResponseEntity(conversionService.convert(userService.findById(id), OutUserDto.class), HttpStatus.OK);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<OutUserDto> findByCode(@PathVariable UUID code) {
        return new ResponseEntity(conversionService.convert(userService.findByCode(code), OutUserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody InUserDto dto) {
        return new ResponseEntity(conversionService.convert(userService.update(id, dto), OutUserDto.class), HttpStatus.OK);
    }
}
