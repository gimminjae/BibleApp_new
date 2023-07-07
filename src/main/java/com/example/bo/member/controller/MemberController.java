package com.example.bo.member.controller;

import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.entity.AuthUser;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.example.bo.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/confirmUsernameDuplication")
    @PostMapping
    public ResponseEntity<Void> confirmUsernameDuplication(@RequestParam String username) {
        memberService.confirmUsernameDuplication(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/confirmEmailDuplication")
    @PostMapping
    public ResponseEntity<Void> signup(@RequestParam String email) {
        memberService.confirmEmailDuplication(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(MemberDto memberDto) {
        Map<String, String> tokensInfo = memberService.login(memberDto);
        return new ResponseEntity<>(tokensInfo, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal AuthUser authUser) {
        memberService.logout(authUser.getMemId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/regenAccessToken")
    public ResponseEntity<String> regenToken(@RequestParam String refreshToken) {
        return new ResponseEntity<>(memberService.regenAccessToken(refreshToken), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> me(@AuthenticationPrincipal AuthUser authUser) {
        return new ResponseEntity<>(Map.of("member", authUser == null ? "" : authUser), HttpStatus.OK);
    }

    @PatchMapping("/email")
    public ResponseEntity<Void> modifyEmail(@AuthenticationPrincipal AuthUser authUser, @RequestBody String newEmail) {
        memberService.modifyEmail(memberService.getByUsername(authUser.getUsername()), newEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<Void> modifyNickname(@AuthenticationPrincipal AuthUser authUser, @RequestBody String newNickname) {
        memberService.modifyNickname(memberService.getByUsername(authUser.getUsername()), newNickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> modifyPassword(@AuthenticationPrincipal AuthUser authUser, @RequestBody Map<String, String> passwordMap) {
        memberService.changePassword(
                authUser.getMemId(),
                passwordMap.get("oldPassword"),
                passwordMap.get("newPassword1"),
                passwordMap.get("newPassword2")
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/empower")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPTADMIN')")
//    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<Void> empowerDEPTSUBADMIN(@AuthenticationPrincipal AuthUser authUser, @RequestBody String memId) {
        memberService.empowerDEPTSUBADMIN(memberService.getByMemId(memId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMine(@AuthenticationPrincipal AuthUser authUser) {
        memberService.deleteMember(memberService.getByMemId(authUser.getMemId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
//    @PreAuthorize("hasAnyAuthority()")
    public ResponseEntity<Void> deleteMember(@AuthenticationPrincipal AuthUser authUser, @RequestParam String memId) {
        memberService.deleteMember(memberService.getByMemId(memId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
