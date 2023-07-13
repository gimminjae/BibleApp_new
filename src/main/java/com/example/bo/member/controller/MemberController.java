package com.example.bo.member.controller;

import com.example.bo.base.exception.ValidationUtil;
import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.entity.AuthUser;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.bo.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/confirmNicknameDuplication")
    public ResponseEntity<Void> confirmNicknameDuplication(@RequestParam String nickname) {
        memberService.confirmNicknameDuplication(nickname);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/confirmEmail/{email}")
    public ResponseEntity<Void> confirmEmail(@PathVariable String email) {
        memberService.confirmEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/confirmEmailCode")
    public ResponseEntity<Void> confirmEmailCode(@RequestBody Map<String, String> emailMapAuthCode) {
        memberService.confirmEmailCode(emailMapAuthCode.get("email"), emailMapAuthCode.get("authCode"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid MemberDto memberDto, BindingResult bindingResult) {
        ValidationUtil.returnValidError(bindingResult);
        memberService.signUp(memberDto);
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

    @PatchMapping("/email/{newEmail}")
    public ResponseEntity<Void> modifyEmail(@AuthenticationPrincipal AuthUser authUser, @PathVariable String newEmail) {
        memberService.modifyEmail(memberService.getByMemId(authUser.getMemId()), newEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/nickname/{newNickname}")
    public ResponseEntity<Void> modifyNickname(@AuthenticationPrincipal AuthUser authUser, @PathVariable String newNickname) {
        memberService.modifyNickname(memberService.getByMemId(authUser.getMemId()), newNickname);
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
