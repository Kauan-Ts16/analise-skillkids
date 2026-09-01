package com.kauanrodrigues.backend.controller.user;

import com.kauanrodrigues.backend.dto.user.admin.UserPasswordPatchDto;
import com.kauanrodrigues.backend.dto.user.admin.UserPatchDto;
import com.kauanrodrigues.backend.dto.user.admin.UserResponseDto;
import com.kauanrodrigues.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/skillkids-platform/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService service;


    @GetMapping
    public ResponseEntity<UserResponseDto> findCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return ResponseEntity.status(HttpStatus.OK).body(service.findActiveById(userId));
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateCurrentUser(@AuthenticationPrincipal Jwt jwt, @RequestBody UserPatchDto dto) {
        UUID userId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.status(HttpStatus.OK).body(service.update(userId, dto));
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@AuthenticationPrincipal Jwt jwt, @RequestBody UserPasswordPatchDto dto) {
        UUID userId = UUID.fromString(jwt.getSubject());

        service.changePassword(userId, dto);

        return ResponseEntity.noContent().build();
    }

}
